package top.todev.note.web.flux.stream;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>创建Stream测试</p>
 *
 * @author hk
 * @version 1.0.0
 * @date 2020-06-05 15:43
 * @since 1.0.0
 **/
@Slf4j
public class CreateStreamTest {


    /**
     * 函数创建Stream
     */
    @Test
    public void testFunctionCreateStream() {
        AtomicInteger index = new AtomicInteger(0);
        Stream<Integer> generateStream = Stream.generate(RandomUtil::randomInt);
        /*log.info("一串随机数中最大值：{}", generateStream
                .mapToInt(num -> {
                    log.info("数组【{}】的值为：{}", index.incrementAndGet(), num);
                    return num;
                }).max().getAsInt());*/
    }

    /**
     * 值创建Stream
     */
    @Test
    public void testValueCreateStream() {
        // 构建Integer类型的Stream
        IntStream stream = IntStream.of(14, 2, 31, 47, 5, 6, 9, 1, 33, 2, 6);
        // 构建String类型的Stream
        Stream<String> stringStream = Stream.of("Hello, Stream Api.");
        log.info("一串数中最大值：{}", stream.distinct().max().getAsInt());
        log.info("字符串出现的字母数(去重)：{}", stringStream
                // 去除非字母，转换为小写，分隔为单个字符
                .flatMap(str -> Stream.of(
                        str.replaceAll("[^\\p{Alpha}]", "")
                                .toLowerCase()
                                .split("")))
                .distinct()
                .count());
    }

    /**
     * 数组创建Stream
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
     * 集合获取Stream
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
}
