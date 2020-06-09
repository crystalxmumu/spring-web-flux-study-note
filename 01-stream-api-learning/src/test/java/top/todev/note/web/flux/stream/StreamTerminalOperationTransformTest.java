package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * <p>终端操作-匹配测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-09 17:33
 * @since 1.0.0
 **/
@Slf4j
public class StreamTerminalOperationTransformTest {

    /**
     * reduce的使用1
     */
    @Test
    public void testReduce1() {
        // 使用reduce方式实现查找最小值
        log.info("[1, 2, 3, 4, 5, 6]的最小值：{}",
                Stream.of(1, 2, 3, 4, 5, 6).reduce(Integer::min).get());
    }

    /**
     * reduce的使用2
     */
    @Test
    public void testReduce2() {
        // 使用reduce方式实现求和
        log.info("[1, 2, 3, 4, 5, 6]的求和：{}",
                Stream.of(1, 2, 3, 4, 5, 6).reduce(0, Integer::sum));
    }

    /**
     * reduce的使用3
     */
    @Test
    public void testReduce3() {

        List<Integer> accResult_ = Stream.of(1, 2, 3, 4).parallel()
                .reduce(Collections.synchronizedList(new ArrayList<>()),
                        (acc, item) -> {
                            List<Integer> list = new ArrayList<>();
                            list.add(item);
                            System.out.println("item BiFunction : " + item);
                            System.out.println("acc+ BiFunction: " + list);
                            return list;
                        }, (acc, item) -> {
                            acc.addAll(item);
                            System.out.println("item BinaryOperator: " + item);
                            System.out.println("acc+ BinaryOperator: " + acc);
                            return acc;
                        });
        System.out.println("accResult_: " + accResult_);
    }

    @Test
    public void test4() {
        // 求单词长度之和
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.parallel().reduce(0,// 初始值　// (1)
                (sum, str) -> sum + str.length(), // 累加器 // (2)
                Integer::sum);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }
}
