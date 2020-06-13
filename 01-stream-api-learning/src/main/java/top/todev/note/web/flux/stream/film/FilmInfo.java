package top.todev.note.web.flux.stream.film;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>电影信息</p>
 *
 * @author 小飞猪
 * @version 1.0.0
 * @date 2020-06-13 17:13
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmInfo implements Serializable {

    private int year;

    private String name;
}
