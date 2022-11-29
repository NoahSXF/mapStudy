package com.example.mapStudy.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * password
 *
 * @author
 */
@Data
public class Password implements Serializable {
    private List<Map> list;
    /**
     * 账号
     */
    @Length(min = 0, max = 30, groups = {Save.class, Update.class})
    private String account;

    /**
     * 密码
     */
    @Length(min = 0, max = 30, groups = {Save.class, Update.class})
    private String password;

    /**
     * ip地址
     */
    @Length(min = 0, max = 16, groups = {Save.class, Update.class})
    private String ip;

    /**
     * 端口号
     */
    @Length(min = 0, max = 5, groups = {Save.class, Update.class})
    private String port;

    /**
     * 检索关键词
     */
    @Length(min = 0, max = 512, groups = {Save.class, Update.class})
    private String key;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最近的修改时间
     */
    private Date updateTime;

    /**
     * 所属用户
     */
    @NotNull(groups = {Save.class, Update.class})
    private String user;

    /**
     * 唯一标识
     */
    @NotNull(groups = {Update.class})
    private String id;

    /**
     * 查询频率
     */
    private int index;

    /**
     * 保存的时候校验分组
     */
    public interface Save {
    }

    /**
     * 更新的时候校验分组
     */
    public interface Update {
    }

    private static final long serialVersionUID = 1L;
}