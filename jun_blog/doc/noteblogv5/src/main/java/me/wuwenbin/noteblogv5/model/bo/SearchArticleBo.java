package me.wuwenbin.noteblogv5.model.bo;

import lombok.*;
import me.wuwenbin.noteblogv5.model.entity.Article;

/**
 * @author wuwen
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class SearchArticleBo extends Article {

    private String type;
    private String resContent;
}
