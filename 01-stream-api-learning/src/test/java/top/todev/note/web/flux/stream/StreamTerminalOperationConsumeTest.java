package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * <p>终端操作-消费测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-09 17:08
 * @since 1.0.0
 **/
@Slf4j
public class StreamTerminalOperationConsumeTest {

    /**
     * findFirst的使用
     */
    @Test
    public void testFindFirst() {
        log.info("[1, 2, 3, 4, 5, 6]的首个值：{}",
                Stream.of(1, 2, 3, 4, 5, 6).parallel().findFirst().get());
    }

    /**
     * findAny的使用
     */
    @Test
    public void testFindAny() {
        log.info("[1, 2, 3, 4, 5, 6]的任意值：{}",
                Stream.of(1, 2, 3, 4, 5, 6).parallel().findAny().get());
    }

    /**
     * forEach的使用
     */
    @Test
    public void testForEach() {
        // 并行后顺序随机，输出不保证顺序
        log.info("[1, 2, 3, 4, 5, 6]的并行后循环输出：");
        Stream.of(1, 2, 3, 4, 5, 6).parallel().forEach(System.out::println);
    }

    /**
     * forEachOrdered的使用(使用Comparator比较)
     */
    @Test
    public void testForEachOrdered() {
        // 无论是否并行，始终按照流定义的顺序或排序后的结果输出
        log.info("[1, 2, 5, 6, 3, 4]的并行后顺序循环输出：");
        Stream.of(1, 2, 5, 6, 3, 4).sorted().parallel().forEachOrdered(System.out::println);
    }

}
