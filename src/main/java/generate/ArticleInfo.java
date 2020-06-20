package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * article_info
 * @author 
 */
@Data
public class ArticleInfo implements Serializable {
    private Integer artId;

    private String title;

    private String filePath;

    /**
     * 文号
     */
    private String artNo;

    /**
     * 发布日期
     */
    private Date publishDate;

    private Date implDate;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}