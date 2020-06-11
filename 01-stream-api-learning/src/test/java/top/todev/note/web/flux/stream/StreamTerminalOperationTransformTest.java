package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>终端操作-转换测试类</p>
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
     *
     */
    @Test
    public void testReduce3() {
        // 求单词长度之和
        Integer lengthSum = Stream.of("I", "love", "you", "too")
                .parallel()
                .reduce(0,// 初始值　// (1)
                        (sum, str) -> sum + str.length(), // 累加器 // (2)
                        Integer::sum);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        log.info("ILoveYouToo的长度为：{}", lengthSum);
    }

    /**
     * reduce的使用4
     */
    @Test
    public void testReduce4() {
        // 下方方法同步执行时，能出现正确结果
        // 并行执行时，将出现意想不到的结果
        // 多线程执行时，append导致初始值identity发生了变化，而多线程又导致了数据重复添加
        StringBuffer word = Stream.of("I", "love", "you", "too")
                .parallel()                 // 同步执行注释该步骤
                .reduce(new StringBuffer(),// 初始值　// (1)
                        StringBuffer::append, // 累加器 // (2)
                        StringBuffer::append);// 部分和组合器，并行执行时才会用到 // (3)
        log.info("拼接字符串为:{}", word);

        // 此处如果使用字符串concat，导致性能降低，不停创建字符串常量
        String word2 = Stream.of("I", "love", "you", "too")
                .parallel()                 // 同步执行注释该步骤
                .reduce("",// 初始值　// (1)
                        String::concat, // 累加器 // (2)
                        String::concat);// 部分和组合器，并行执行时才会用到 // (3)
        log.info("拼接字符串为:{}", word2);

        // 下面方法并行执行时，虽然能达到正确的结果，但是并未满足reduce的要求
        List<Integer> accResult = Stream.of(1, 2, 3, 4)
                .parallel()
                .reduce(Collections.synchronizedList(new ArrayList<>()),
                        (acc, item) -> {
                            List<Integer> list = new ArrayList<>();
                            list.add(item);
                            System.out.println("item BiFunction : " + item);
                            System.out.println("acc+ BiFunction: " + list);
                            return list;
                        }, (accs, items) -> {
                            accs.addAll(items);
                            System.out.println("item BinaryOperator: " + items);
                            System.out.println("acc+ BinaryOperator: " + accs);
                            return accs;
                        });
        log.info("accResult: {}", accResult);
    }
}
