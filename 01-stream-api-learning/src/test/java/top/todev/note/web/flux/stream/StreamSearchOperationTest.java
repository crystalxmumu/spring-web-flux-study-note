package top.todev.note.web.flux.stream;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * <p>Stream筛选操作测试类</p>
 *
 * @author hk
 * @version 1.0.0
 * @date 2020-06-08 11:20
 * @since 1.0.0
 **/
@Slf4j
public class StreamSearchOperationTest {

    /**
     * distinct的使用
     */
    @Test
    public void testStreamDistinct() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 1, 2, 3};
        // count为终端操作，代表统计流中元素个数
        log.info("{}数据去重后元素个数为{}个", array, Arrays.stream(array).distinct().count());
    }

    /**
     * filter的使用
     */
    @Test
    public void testStreamFilter() {
        log.info("整数3-250中大于等于21,小于148的奇数有{}个",
                IntStream.range(3, 251).filter(n -> n >= 21 && n < 148 && n % 2 == 1).count());
    }

    /**
     * limit的使用
     */
    @Test
    public void testStreamLimit() {
        log.info("随机生成3-50之间的20个正整数，如下：");
        // forEach为终端操作，代表循环消费流中元素
        LongStream.generate(() -> RandomUtil.randomLong(3, 50)).limit(20).forEach(System.out::println);
    }

    /**
     * skip的使用
     */
    @Test
    public void testStreamSkip() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 1, 2, 3};
        log.info("{}数据去重头部3个元素后变为：", array);
        Arrays.stream(array).skip(3).forEach(System.out::println);
    }

    /**
     * peek的使用
     */
    @Test
    public void testStreamPeek() {
        // sum为终端操作，代表流中元素的总和
        log.info("以上一串随机浮点数和为：{}",
                DoubleStream.generate(RandomUtil::randomDouble).limit(30).peek(System.out::println).sum());
    }

    /**
     * 综合使用1
     */
    @Test
    public void testWordNum () {
        String sentence = "Cease to struggle and you cease to live.(Thomas Carlyle)";
        // 正则 \p{Lower} 小写字母字符：[a-z]
        // 正则 \p{Upper} 大写字母字符：[A-Z]
        // 正则 \p{Alpha} 字母字符：[\p{Lower}\p{Upper}]
        log.info("语句[{}]使用的字母个数为：{}", sentence,
                Arrays.stream(sentence.toLowerCase().split(""))
                        .filter(str -> str.matches("\\p{Alpha}"))
                        .distinct()
                        .peek(System.out::println)
                        .count()
        );
    }

    @Test
    public void testPrimesNumber() {
        int page = 34;
        int pageNo = 70;
        int total = 3000;
        log.info("1-{}中每页显示{}条数据，第{}页数据中的质数如下:", total, 70, 34);
        // isPrimes 方法判断数字是否是质数（素数）
        log.info("质数个数为：{}",
                IntStream.range(1, total + 1)
                        .skip(page * pageNo)
                        .limit(pageNo)
                        .filter(NumberUtil::isPrimes)
                        .peek(System.out::println)
                        .count()
        );
    }
}
