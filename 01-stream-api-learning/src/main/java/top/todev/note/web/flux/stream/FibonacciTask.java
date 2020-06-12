package top.todev.note.web.flux.stream;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>斐波那契数列任务</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-12 11:20
 * @since 1.0.0
 **/
public class FibonacciTask extends RecursiveTask<Long> {

    private final Integer n;
    public FibonacciTask(int n) {
        super();
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (this.n <= 1) {
            return this.n.longValue();
        }
        FibonacciTask t1 = new FibonacciTask(this.n - 1);
        FibonacciTask t2 = new FibonacciTask(this.n - 2);
        ForkJoinTask.invokeAll(t1, t2);
        return t1.join() + t2.join();
    }
}
