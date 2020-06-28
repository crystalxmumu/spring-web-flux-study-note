package top.todev.note.web.flux.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * <p>Reactor 转换类操作符（1）测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-20 10:56
 * @since 1.0.0
 **/
public class ReactorTransformOperator01Test {

    @Test
    public void testAs() {
        Flux.range(3, 8)
                .as(Mono::from)
                .subscribe(System.out::println);
    }

    @Test
    public void testCast() {
        Flux.range(1, 3)
                .cast(Number.class)
                .subscribe(System.out::println);
    }

    @Test
    public void testCollect() {
        Flux.range(1, 5)
                .collect(Collectors.toList())
                .subscribe(System.out::println);
    }

    @Test
    public void testCollectList() {
        Flux.range(1, 5)
                .collectList()
                .subscribe(System.out::println);
    }

    @Test
    public void testCollectMap() {
        Flux.just(1, 2, 3, 4, 5, 3, 1)
                .collectMap(n -> n, n -> n + 100)
                .subscribe(System.out::println);
    }

    @Test
    public void testCollectMultimap() {
        Flux.just(1, 2, 3, 4, 5, 3, 1)
                .collectMultimap(n -> n, n -> n + 100)
                .subscribe(System.out::println);
    }

    @Test
    public void testCollectSortedList() {
        Flux.just(1, 3, 5, 3, 2, 5, 1, 4)
                .collectSortedList()
                .subscribe(System.out::println);
    }

}
