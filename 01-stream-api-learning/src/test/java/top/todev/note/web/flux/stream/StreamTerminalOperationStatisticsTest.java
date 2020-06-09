package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>终端操作-统计测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-09 16:08
 * @since 1.0.0
 **/
@Slf4j
public class StreamTerminalOperationStatisticsTest {

    /**
     * count的使用
     */
    @Test
    public void testCount() {
        log.info("[1, 2, 3, 4, 5, 6]元素个数：{}",
                Stream.of(1, 2, 3, 4, 5, 6).count());
    }

    /**
     * min的使用(使用Comparator比较)
     */
    @Test
    public void testMinComparator() {
        log.info("[1, 2, 3, 4, 5, 6]的最大值：{}",
                Stream.of(1, 2, 3, 4, 5, 6).min(Comparator.comparingInt(n -> n)).get());
    }

    /**
     * max的使用(使用Comparator比较)
     */
    @Test
    public void testMaxComparator() {
        log.info("[1, 2, 3, 4, 5, 6]的最小值：{}",
                Stream.of(1, 2, 3, 4, 5, 6).max(Comparator.comparingInt(n -> n)).get());
    }

    /**
     * min的使用
     */
    @Test
    public void testMin() {
        log.info("[1, 2, 3, 4, 5, 6]的最小值：{}",
                IntStream.of(1, 2, 3, 4, 5, 6).min().getAsInt());
    }

    /**
     * max的使用
     */
    @Test
    public void testMax() {
        log.info("[1, 2, 3, 4, 5, 6]的最大值：{}",
                IntStream.of(1, 2, 3, 4, 5, 6).max().getAsInt());
    }

    /**
     * sum的使用
     */
    @Test
    public void testSum() {
        log.info("[1, 2, 3, 4, 5, 6]的求和：{}",
                IntStream.of(1, 2, 3, 4, 5, 6).sum());
    }

    /**
     * average的使用
     */
    @Test
    public void testAverage() {
        log.info("[1, 2, 3, 4, 5, 6]的平均值：{}",
                IntStream.of(1, 2, 3, 4, 5, 6).average().getAsDouble());
    }

    /**
     * summaryStatistics的使用
     */
    @Test
    public void testSummaryStatistics() {
        IntSummaryStatistics summaryStatistics = IntStream.of(1, 2, 3, 4, 5, 6).summaryStatistics();
        log.info("[1, 2, 3, 4, 5, 6]的统计对象：{}", summaryStatistics);
        summaryStatistics.accept(7);
        log.info("添加7后，统计对象变为：{}", summaryStatistics);
        IntSummaryStatistics summaryStatistics2 = IntStream.of(8, 9).summaryStatistics();
        summaryStatistics.combine(summaryStatistics2);
        log.info("合并[8, 9]后，统计对象变为：{}", summaryStatistics);
    }

}
