package top.todev.note.web.flux.stream;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>数字相加任务类</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-12 16:01
 * @since 1.0.0
 **/
public class NumberAddTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10_0000;
    private final int begin;
    private final int end;

    public NumberAddTask(int begin, int end) {
        super();
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - begin <= THRESHOLD) {
            long sum = 0;
            for(int i = begin; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        int mid = (begin + end) /2;
        NumberAddTask t1 = new NumberAddTask(begin, mid);
        NumberAddTask t2 = new NumberAddTask(mid + 1,  end);
        ForkJoinTask.invokeAll(t1, t2);
        return t1.join() + t2.join();
    }
}
