package top.todev.note.web.flux.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * <p>Reactor 转换类操作符（2）测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-28 10:56
 * @since 1.0.0
 **/
public class ReactorTransformOperator02Test {

    @Test
    public void testConcatMap() {
        Flux.range(3, 8)
                .concatMap(n -> Flux.just(n - 10, n, n + 10), 3)
                .subscribe(System.out::println);
    }

    @Test
    public void testConcatMapError() {
        Flux.range(3, 8)
                .concatMap(n -> {
                    if (n == 4) {
                        return Flux.error(new NullPointerException());
                    }
                    return Flux.just(n - 10, n, n + 10);
                }, 1)
                .subscribe(System.out::println, System.err::println);
    }

    @Test
    public void testConcatMapDelayError() {
        Flux.range(3, 8)
                .concatMapDelayError(n -> {
                    if (n == 4) {
                        return Flux.error(new NullPointerException());
                    }
                    return Flux.just(n - 10, n, n + 10);
                })
                .subscribe(System.out::println, System.err::println);
    }

    @Test
    public void testConcatIterable() {
        Flux.range(3, 8)
                .publishOn(Schedulers.single())
                .concatMapIterable(n -> {
                    if (n == 4) {
                        throw new NullPointerException();
                    }
                    return Arrays.asList(n - 10, n, n + 10);
                })
                .onErrorContinue((e, n) -> System.err.println("数据：" + n + ",发生错误：" + e))
                .subscribe(System.out::println);
    }

    @Test
    public void testElapsed() throws InterruptedException {
        Flux.interval(Duration.ofMillis(300))
                .take(20)
                .elapsed(Schedulers.parallel())
                .subscribe(System.out::println);
        Thread.sleep(7000);
    }

    @Test
    public void testExpand() {
        Flux.just(16, 18, 20)
                .expand(n -> {
                    if (n % 2 == 0) {
                        return Flux.just(n / 2);
                    } else {
                        return Flux.empty();
                    }
                })
                .subscribe(System.out::println);
    }

    @Test
    public void testExpandDeep() {
        Flux.just(16, 18, 20)
                .expandDeep(n -> {
                    if (n % 2 == 0) {
                        return Flux.just(n / 2);
                    } else {
                        return Flux.empty();
                    }
                })
                .subscribe(System.out::println);
    }

}
