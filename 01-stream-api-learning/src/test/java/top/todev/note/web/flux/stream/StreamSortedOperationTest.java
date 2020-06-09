package top.todev.note.web.flux.stream;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Comparator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <p>Stream排序操作测试</p>
 *
 * @author hk
 * @version 1.0.0
 * @date 2020-06-08 17:27
 * @since 1.0.0
 **/
@Slf4j
public class StreamSortedOperationTest {

    /**
     * sorted的使用
     */
    @Test
    public void testStreamSorted() {
        log.info("随机生成3-50之间的20个正整数，排序如下：");
        // forEach为终端操作，代表循环消费流中元素
        LongStream.generate(() -> RandomUtil.randomLong(3, 50))
                .limit(20)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * sorted的使用(使用Comparator比较)
     */
    @Test
    public void testStreamSortedComparator() {
        log.info("随机生成3-50之间的20个正整数，倒序排序如下：");
        // forEach为终端操作，代表循环消费流中元素
        Stream.generate(() -> RandomUtil.randomInt(3, 50))
                .limit(20)
                .distinct()
                .sorted(Comparator.comparingInt(o -> -o))
                .forEach(System.out::println);
    }
}
