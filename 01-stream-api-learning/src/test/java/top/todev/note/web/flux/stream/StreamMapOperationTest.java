package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>Stream映射操作测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-08 16:09
 * @since 1.0.0
 **/
@Slf4j
public class StreamMapOperationTest {

    /**
     * map的使用
     */
    @Test
    public void testStreamMap() {
        log.info("1-20的整数每个增加1543后为:");
        IntStream.range(1, 21)
                .map(n -> n + 1543)
                .forEach(System.out::println);
    }

    /**
     * flatMap的使用1
     */
    @Test
    public void testStreamFlatMap1() {
        // 方式1： 将一个元素变为零个或多个元素组成的Stream
        // 需求1： 输出1-20中每个奇数的前后及其本身，并求和
        log.info("1-20的中奇数前后元素及求和为:{}",
                IntStream.range(1, 21)
                .flatMap(n -> n % 2 == 0 ? IntStream.empty() : IntStream.of(n - 1, n, n +1))
                .peek(System.out::println)
                .sum()
        );
    }

    /**
     * flatMap的使用2
     */
    @Test
    public void testStreamFlatMap2() {
        // 方式2：将一个元素变为多个元素组成的Stream
        // 需求2：
        // 生命不止，奋斗不息。(卡莱尔)
        String sentence1 = "Cease to struggle and you cease to live.(Thomas Carlyle).";
        // 过一种高尚而诚实的生活。当你年老时回想起过去，你就能再一次享受人生。
        String sentence2 = "Live a noble and honest life. Reviving past times in your old age will help you to enjoy your life again.";
        // 充实今朝，昨日已成过去，明天充满神奇。
        String sentence3 = "Enrich your life today. Yesterday is history，and tomorrow is mystery.";
        log.info("语句[{}\n{}\n{}\n]使用的字母个数为：{}", sentence1, sentence2, sentence3,
                Stream.of(sentence1, sentence2, sentence3)
                        .flatMap(str -> Arrays.stream(str.toLowerCase().split("")))
                        .filter(str -> str.matches("\\p{Alpha}"))
                        .distinct()
                        .peek(System.out::println)
                        .count()
        );
    }
}
