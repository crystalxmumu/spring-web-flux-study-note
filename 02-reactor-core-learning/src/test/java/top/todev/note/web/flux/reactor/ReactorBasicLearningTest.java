package top.todev.note.web.flux.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>Reactor 基础知识测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-19 15:04
 * @since 1.0.0
 **/
public class ReactorBasicLearningTest {

    /**
     * 测试 Just
     */
    @Test
    public void testJust() {
        String str = null;
        Optional<String> optional = Optional.of("hello, mono");
        Mono.just("hello, world").subscribe(System.out::println);
        Mono.justOrEmpty(str).subscribe(System.out::println);
        Mono.justOrEmpty(optional).subscribe(System.out::println);

        Flux.just("hello", "world").subscribe(System.out::println);
        Flux.just("hello").subscribe(System.out::println);
    }

    /**
     * 测试 From
     */
    @Test
    public void testFrom() {
        String[] array = new String[]{"hello", "reactor", "flux"};
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");

        Flux.fromArray(array).subscribe(System.out::println);
        Flux.fromIterable(iterable).subscribe(System.out::println);
        Flux.fromStream(Arrays.stream(array)).subscribe(System.out::println);
    }

    /**
     * 测试 range
     */
    @Test
    public void testRange() {
        Flux.range(3, 5).subscribe(System.out::println);
    }

    /**
     * 测试 interval
     *
     * @throws InterruptedException
     */
    @Test
    public void testInterval() throws InterruptedException {
        Flux.interval(Duration.ofMillis(30), Duration.ofMillis(500)).subscribe(System.out::println);
        Thread.sleep(5000);
    }

    /**
     * 测试 empty
     */
    @Test
    public void testEmpty() {
        Flux.empty().subscribe(System.out::println, System.out::println, () -> System.out.println("结束"));
    }

    /**
     * 测试 never
     */
    @Test
    public void testNever() {
        Flux.never().subscribe(System.out::println, System.out::println, () -> System.out.println("结束"));
    }

    /**
     * 测试错误
     */
    @Test
    public void testError() {
        Flux.error(new IllegalStateException(), true)
                .log()
                .subscribe(System.out::println, System.err::println);
    }

    /**
     * 测试 Subscribe
     */
    @Test
    public void testSubscribe() {
        Flux.range(1, 4)
                .log()
                .subscribe(System.out::println,
                        error -> System.err.println("发生错误：" + error),
                        () -> System.out.println("完成"),
                        sub -> {
                            System.out.println("已订阅");
                            // 理解背压
                            // 尝试修改 request 中成小于4的值，看看有啥变化
                            sub.request(10);
                            // sub.cancel();
                        });
    }

    /**
     * 测试 log
     */
    @Test
    public void testLog() {
        // 尝试交换下 take 和 log 的顺序，看看有啥变化
        Flux.range(1, 10)
                // .log()
                .take(3)
                .log()
                .subscribe();
    }
}
