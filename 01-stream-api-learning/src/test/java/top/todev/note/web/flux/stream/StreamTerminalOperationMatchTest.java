package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>终端操作-匹配测试类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-09 15:08
 * @since 1.0.0
 **/
@Slf4j
public class StreamTerminalOperationMatchTest {

    /**
     * anyMatch的使用
     */
    @Test
    public void testAnyMatch() {
        // 是否存在匹配的元素，true
        log.info("[1, 2, 3, 4, 5, 6]存在偶数否：{}",
                Stream.of(1, 2, 3, 4, 5, 6).anyMatch(n -> n % 2 == 0));
    }

    /**
     * allMatch的使用
     */
    @Test
    public void testAllMatch() {
        // 全部元素是否都匹配，false
        log.info("[1, 2, 3, 4, 5, 6]全部都是偶数否：{}",
                Stream.of(1, 2, 3, 4, 5, 6).allMatch(n -> n % 2 == 0));
    }

    /**
     * noneMatch的使用
     */
    @Test
    public void testNoneMatch() {
        // 全部元素是否都不匹配，false
        log.info("[1, 2, 3, 4, 5, 6]全部都不是偶数否：{}",
                Stream.of(1, 2, 3, 4, 5, 6).noneMatch(n -> n % 2 == 0));
    }

    /**
     * 空流匹配测试
     */
    @Test
    public void testEmptyStreamMatch() {
        // 空流中不存在任何匹配元素，所以返回false
        log.info("空流是否AnyMatch：{}", Stream.empty().anyMatch(Objects::isNull));
        // 空流中不存在不匹配的，即全部匹配，所以返回true
        log.info("空流是否AllMatch：{}", Stream.empty().allMatch(Objects::isNull));
        // 空流中全部都不匹配，所以返回true
        log.info("空流是否NoneMatch：{}", Stream.empty().noneMatch(Objects::isNull));
    }
}
