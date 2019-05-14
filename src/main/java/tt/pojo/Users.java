package tt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Mapper层
 *
 * @author Jerry
 * @date 2019-05-10
 */
@Data
@TableName("sys_users")
public class Users implements Serializable {
    @TableId(value = "newsId", type = IdType.AUTO)
    private Integer newsId;
    @TableField("newsName")
    private String newsName;
    @TableField("newsAuthor")
    private String newsAuthor;
    @TableField("abstractX")
    private String abstractX;
    @TableField("newsStatus")
    private String newsStatus;
    @TableField("newsImg")
    private String newsImg;
    @TableField("newsLook")
    private String newsLook;
    @TableField("newsTop")
    private String newsTop;
    @TableField("newsTime")
    private String newsTime;
    @TableField("content")
    private String content;
}
