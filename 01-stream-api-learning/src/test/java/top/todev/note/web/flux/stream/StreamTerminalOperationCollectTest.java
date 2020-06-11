package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>终端操作-收集转换测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-09 17:33
 * @since 1.0.0
 **/
@Slf4j
public class StreamTerminalOperationCollectTest {

    /**
     * collect的使用1
     */
    @Test
    public void testCollect1() {
        // 使用collect方法实现字符串连接
        log.info("拼接字符串为：{}",
                Stream.of("I", "love", "you", "too")
                        .parallel()
                        .collect(StringBuilder::new, (b1, b2) -> {
                            log.info("累加执行：{} + {}", b1, b2);
                            b1.append(b2);
                        }, (b1, b2) -> {
                            log.info("组合执行：{} ++ {}", b1, b2);
                            b1.append(b2);
                        })
                        .toString());
    }

    /**
     * collect的使用2
     */
    @Test
    public void testCollect2() {
        // 使用collect方法实现集合连接
        log.info("拼接集合为：{}",
                Stream.of("I", "love", "you", "too")
                        .parallel()
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
                        .toString());
    }

    /**
     * Collector接口实现
     */
    @Test
    public void testCollectorImpl() {
        // 使用collector实现求ping均值
        log.info("[1, 2, 3, 4, 5, 6]的平均值：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .parallel()
                        .collect(new Collector<Integer, long[], Double>() {
                                     @Override
                                     public Supplier<long[]> supplier() {
                                         return () -> new long[2];
                                     }

                                     @Override
                                     public BiConsumer<long[], Integer> accumulator() {
                                         return (a, t) -> {
                                             log.info("{}累加{}", a, t);
                                             a[0] += t;
                                             a[1]++;
                                         };
                                     }

                                     @Override
                                     public BinaryOperator<long[]> combiner() {
                                         return (a, b) -> {
                                             log.info("{}组合{}", a, b);
                                             a[0] += b[0];
                                             a[1] += b[1];
                                             return a;
                                         };
                                     }

                                     @Override
                                     public Function<long[], Double> finisher() {
                                         return (a) -> a[1] == 0 ? 0 : new Long(a[0]).doubleValue() / a[1];
                                     }

                                     @Override
                                     public Set<Characteristics> characteristics() {
                                         Set<Characteristics> set = new HashSet<>();
                                         set.add(Characteristics.CONCURRENT);
                                         return set;
                                     }
                                 }
                        )
        );
    }

    /**
     * 统计平均值 averagingXxx 的使用
     */
    @Test
    public void testAveragingInt() {
        // 使用collector获取求均值
        log.info("[1, 2, 3, 4, 5, 6]的平均值：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                .collect(Collectors.averagingInt(n -> n))
        );
    }

    /**
     * 统计元素个数 counting 的使用
     */
    @Test
    public void testCounting() {
        // 使用collector获取元素数量
        log.info("[1, 2, 3, 4, 5, 6]的个数：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.counting())
        );
    }

    /**
     * 统计总和 summingXxx 的使用
     */
    @Test
    public void testSummingInt() {
        // 使用collector获取总和
        log.info("[1, 2, 3, 4, 5, 6]的总和：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.summingInt(n -> n))
        );
    }

    /**
     * 统计最小元素 minBy 的使用
     */
    @Test
    public void testMinBy() {
        // 使用collector获取最小元素
        log.info("[1, 2, 3, 4, 5, 6]的最小值：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.minBy(Integer::min))
                        .get()
        );
    }

    /**
     * 统计最大元素 maxBy 的使用
     */
    @Test
    public void testMaxBy() {
        // 使用collector获取最da元素
        log.info("[1, 2, 3, 4, 5, 6]的最大值：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.maxBy(Integer::max))
                        .get()
        );
    }

    /**
     * 统计累加处理 reducing 的使用
     */
    @Test
    public void testReducing() {
        // 使用collector实现求均值
        log.info("[1, 2, 3, 4, 5, 6]的求和：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.reducing(0, Integer::sum))
        );
    }

    /**
     * 转换映射 mapping 的使用
     */
    @Test
    public void testMapping() {
        log.info("[1, 2, 3, 4, 5, 6]每个增加20后的平均值：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.mapping(n -> n + 20, Collectors.averagingInt(n -> n)))
        );
    }

    /**
     * 转换连接 joining 的使用
     */
    @Test
    public void testJoining() {
        // 使用collector连接字符串
        log.info("连接字符串为：{}",
                Stream.of("I", "love", "you", "too")
                        .collect(Collectors.joining(" ", "Java, ", "!"))
        );
    }

    /**
     * 转换为集合 toList 的使用
     */
    @Test
    public void testToList() {
        log.info("[1, 2, 3, 4, 5, 6, 5, 3, 6]转换为集合：{}",
                Stream.of(1, 2, 3, 4, 5, 6, 5, 3, 6)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 转换为Map toMap 的使用
     */
    @Test
    public void testToMap() {
        log.info("[1, 2, 3, 4, 5, 6, 5, 3, 6]转换为Map：{}",
                Stream.of(1, 2, 3, 4, 5, 6, 5, 3, 6)
                        .collect(Collectors.toMap(Object::toString, n -> n, Integer::sum))
        );
    }

    /**
     * 转换为Set toSet 的使用
     */
    @Test
    public void testToSet() {
        log.info("[1, 2, 3, 4, 5, 6, 5, 3, 6]的转换为Set：{}",
                Stream.of(1, 2, 3, 4, 5, 6, 5, 3, 6)
                        .collect(Collectors.toSet())
        );
    }

    /**
     * 转换为分组 groupingBy 的使用
     */
    @Test
    public void testGroupingBy() {
        log.info("[1, 2, 3, 4, 5, 6, 5, 3, 6]的分组数据：{}",
                Stream.of(1, 2, 3, 4, 5, 6, 5, 3, 6)
                        .collect(Collectors.groupingBy(n -> n))
        );
    }

    /**
     * 转换为分区 partitioningBy 的使用
     */
    @Test
    public void testPartitioningBy() {
        log.info("[1, 2, 3, 4, 5, 6]的奇偶分区数据：{}",
                Stream.of(1, 2, 3, 4, 5, 6)
                        .collect(Collectors.partitioningBy(n -> n %2 == 0))
        );
    }

}
