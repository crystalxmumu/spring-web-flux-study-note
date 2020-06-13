package top.todev.note.web.flux.stream.film;

import java.util.Comparator;

/**
 * <p>演员信息排序接口实现</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-13 17:24
 * @since 1.0.0
 **/
public class ActorComparator implements Comparator<ActorInfo> {

    @Override
    public int compare(ActorInfo o1, ActorInfo o2) {
        // TODO 此处忽略o1 o2为空的情况，待完善
        int num = o2.getList().size() - o1.getList().size();
        return num == 0 ? o1.getList().stream().findFirst().get().getYear() - o2.getList().stream().findFirst().get().getYear() : num;
    }
}
