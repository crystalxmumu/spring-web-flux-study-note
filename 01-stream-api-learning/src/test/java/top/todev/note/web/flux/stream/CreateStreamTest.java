package top.todev.note.web.flux.stream;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>创建Stream测试</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-05 15:43
 * @since 1.0.0
 **/
@Slf4j
public class CreateStreamTest {

    /**
     * 1. 集合获取Stream
     */
    @Test
    public void testCollectionCreateStream() {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(3);
        collection.add(5);
        collection.add(2);
        collection.add(4);
        collection.add(6);

        // 返回以此集合作为源的顺序 Stream
        Stream<Integer> stream = collection.stream();
        // 返回以此集合作为其来源可能并行的 Stream
        Stream<Integer> parallelStream = collection.parallelStream();
        int sum = stream.mapToInt(n -> n).sum();
        log.info("总和：{}", sum);
        Assert.assertEquals(sum, 21);
        log.info("总和2：{}", parallelStream.mapToInt(n -> n).sum());
    }

    /**
     * 2. 数组创建Stream
     */
    @Test
    public void testArrayCreateStream() {
        String[] strArray = new String[]{"hello", "stream", "api"};
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6};
        double[] doubleArray = new double[]{1, 2, 3, 4, 5, 6};
        // 创建数组Stream
        Stream<String> stream = Arrays.stream(strArray);
        IntStream intStream = Arrays.stream(intArray);
        // 数组Stream的重载
        DoubleStream doubleStream = Arrays.stream(doubleArray);
        IntStream intStream2 = Arrays.stream(intArray, 1, 3);
        log.info("字符串数组中字符数：{}", stream.mapToInt(String::length).sum());
        // stream 只能使用一次，如下报错
        // log.info("整形数组数量：{}, 求和：{}", intStream.count(), intStream.sum());
        log.info("整形数组数量：{}, 求和：{}", Arrays.stream(intArray).count(), intStream.sum());
        log.info("整形数组2求和：{}", intStream2.sum());
        log.info("浮点型数组求和：{}", doubleStream.sum());
    }

    /**
     * 3. 值创建Stream
     */
    @Test
    public void testValueCreateStream() {
        // 创建Integer类型的Stream
        IntStream stream = IntStream.of(14, 2, 31, 47, 5, 6, 9, 1, 33, 2, 6);
        log.info("一串数中最大值：{}", stream.distinct().max().getAsInt());

        // 创建String类型的Stream
        Stream<String> stringStream = Stream.of("Hello, Stream Api.");
        log.info("字符串中出现的字母数(去重)：{}", stringStream
                // 去除非字母，转换为小写，分隔为单个字符
                .flatMap(str -> Stream.of(
                        str.replaceAll("[^\\p{Alpha}]", "")
                                .toLowerCase()
                                .split("")))
                .distinct()
                .count());
    }

    /**
     * 4. 函数创建Stream
     */
    @Test
    public void testFunctionCreateStream() {
        AtomicInteger index = new AtomicInteger(0);
        // 方式1：使用generate方式创建一个新的无限无序Stream流
        Stream<Integer> generateStream = Stream.generate(RandomUtil::randomInt);
        int num = generateStream
                .mapToInt(n -> {
                    log.info("数组[{}]的值为：{}", index.getAndIncrement(), n);
                    return n;
                }).limit(50).max().getAsInt();
        log.info("一串随机数组中最大值：{}", num);

        // 方式2：使用iterate方式创建一个新的无限有序Stream流
        IntStream iterateStream = IntStream.iterate(1, n -> n + 1);
        log.info("1-500的和为：{}", iterateStream.limit(500).sum());
    }

    /**
     * 5. 其他方式创建Stream
     */
    @Test
    public void testOtherCreateStream() {
        // 方式1：创建空的顺序流
        Stream<Object> emptyStream = Stream.empty();
        log.info("空的顺序流的元素个数为：{}", emptyStream.count());

        // 创建数组Stream
        IntStream intStream1 = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6});
        // 创建Integer类型的Stream
        IntStream intStream2 = IntStream.of(14, 2, 31, 47, 5, 6, 9, 1, 33, 2, 6);
        // 方式2：使用两个Stream创建组合Stream
        IntStream concatStream = IntStream.concat(intStream1, intStream2);
        IntSummaryStatistics statistics = concatStream.summaryStatistics();
        log.info("组合整形数据流中元数个数：{}\n总和：{}\n最大值：{}\n最小值：{}\n平均值：{}",
                statistics.getCount(), statistics.getSum(), statistics.getMax(), statistics.getMin(), statistics.getAverage());

        // 方式3：创建begin至end逐渐加1的整形Stream
        IntStream rangeStream = IntStream.range(1, 501);
        log.info("1-500的和为：{}", rangeStream.sum());

        // 方式4：使用建造者模式创建Stream
        Stream.Builder<Integer> builder = Stream.builder();
        builder.add(RandomUtil.randomInt())
                .add(RandomUtil.randomInt())
                .add(RandomUtil.randomInt())
                .add(RandomUtil.randomInt())
                .add(RandomUtil.randomInt())
                .add(RandomUtil.randomInt());
        Stream<Integer> buildStream = builder.build();
        log.info("构建了{}个元素", buildStream.peek(System.out::println).count());

        // 方式5：使用StreamSupport创建Stream
        // 代码忽略，具体使用请看API...
    }
}
