package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
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
        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4).parallel()
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {

                                acc.add(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("BiFunction");
                                return acc;
                            }
                        }, new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                System.out.println("BinaryOperator");
                                acc.addAll(item);
                                System.out.println("item: " + item);
                                System.out.println("acc+ : " + acc);
                                System.out.println("--------");
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);
    }

}
