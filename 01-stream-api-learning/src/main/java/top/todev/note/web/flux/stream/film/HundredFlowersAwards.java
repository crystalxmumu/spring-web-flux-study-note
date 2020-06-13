package top.todev.note.web.flux.stream.film;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>百花奖最佳男主角</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-13 17:00
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HundredFlowersAwards implements Serializable {

    private int year;

    private String actorName;

    private String fileName;
}
