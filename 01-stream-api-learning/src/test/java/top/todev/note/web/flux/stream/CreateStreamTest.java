package top.todev.note.web.flux.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
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
