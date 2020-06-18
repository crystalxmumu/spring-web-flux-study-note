package top.todev.note.web.flux.reactor;

import cn.hutool.core.util.StrUtil;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.tools.agent.ReactorDebugAgent;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Reactor 初体验</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-18 10:56
 * @since 1.0.0
 **/
public class ReactorFirstExperienceTest {

    private final static String[] WORDS = new String[]{
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    };


    /**
     * Stream 的索引获取
     */
    @Test
    public void testStreamWordsAddIndex() {
        AtomicInteger index = new AtomicInteger(1);
        Arrays.stream(WORDS)
                .map(word -> StrUtil.format("{}. {}", index.getAndIncrement(), word))
                .forEach(System.out::println);
    }

    /**
     * Reactor 的索引获取
     */
    @Test
    public void testReactorWordsAddIndex() {
        Flux.fromArray(WORDS)
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (word, index) -> StrUtil.format("{}. {}", index, word))
                .subscribe(System.out::println);
    }

    /**
     * 使用 StepVerifier 测试
     */
    @Test
    public void testAppendBoomError() {
        // 创建 Flux 响应流
        Flux<String> source = Flux.just("foo", "bar");
        // 使用 concatWith 操作符连接 2 个响应流
        Flux<String> boom = source.concatWith(Mono.error(new IllegalArgumentException("boom")));
        // 创建一个 StepVerifier 构造器来包装和校验一个 Flux。
        StepVerifier.create(boom)
                .expectNext("foo")          // 第一个我们期望的信号是 onNext，它的值为 foo
                .expectNext("bar")          // 第二个我们期望的信号是 onNext，它的值为 bar
                .expectErrorMessage("boom") // 最后我们期望的是一个终止信号 onError，异常内容应该为 boom
                .verify();                  // 使用 verify() 触发测试。
    }

    @Before
    public void before() {
        ReactorDebugAgent.init();
        ReactorDebugAgent.processExistingClasses();
    }

    /**
     * Reactor 的调试
     */
    @Test
    public void testReactorDebug() throws InterruptedException {
        // Hooks.onOperatorDebug();
        Flux.range(1, 3)
                .flatMap(n -> Mono.just(n + 100))
                .single()
                .map(n -> n * 2)
                //.checkpoint()
                //.checkpoint("hello")
                //.checkpoint("hello", true)
                .subscribe(System.out::println);

        Thread.sleep(5000L);
    }

}
