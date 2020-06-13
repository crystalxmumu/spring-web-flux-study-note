package top.todev.note.web.flux.stream.film;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>演员信息</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-13 17:12
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ActorInfo implements Serializable {

    @NonNull
    private String actorName;

    private List<FilmInfo> list = new ArrayList<>();

    public ActorInfo(HundredFlowersAwards awards) {
        this.actorName = awards.getActorName();
        this.list.add(new FilmInfo(awards.getYear(), awards.getFileName()));
    }

    public ActorInfo addFilmInfo(FilmInfo info) {
        this.list.add(info);
        return this;
    }

    public ActorInfo addFilmInfos(ActorInfo info) {
        this.list.addAll(info.getList());
        return this;
    }


}
