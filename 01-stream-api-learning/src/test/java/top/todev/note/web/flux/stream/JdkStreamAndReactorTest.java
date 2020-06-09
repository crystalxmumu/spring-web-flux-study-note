package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;

/**
 * <p>JDK的Stream和Reactor书写比较</p>
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-05 11:32
 * @since 1.0.0
 **/
@Slf4j
public class JdkStreamAndReactorTest {

    private final static Integer[] ARRAY = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Test
    public void testJdkStream() {
        log.info("测试Stream方法");
        // JDK Stream实现
        Arrays.stream(ARRAY)
                .filter(num -> num % 2 == 0)
                .map(num -> num + 100)
                .forEach(System.out::println);
    }

    @Test
    public void testReactor() {
        log.info("测试Reactor方法");
        // Reactor实现
        Flux.fromArray(ARRAY)
                .filter(num -> num % 2 == 0)
                .map(num -> num + 100)
                .subscribe(System.out::println);
    }
}
