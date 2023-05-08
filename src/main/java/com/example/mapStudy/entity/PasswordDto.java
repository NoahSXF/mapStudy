package com.example.mapStudy.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * password
 *
 * @author
 */
@Data
public class PasswordDto implements Serializable {
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
    @Length(max = 16, groups = {Save.class, Update.class})
    private String ip;

    /**
     * 端口号
     */
    @Length(max = 5, groups = {Save.class, Update.class})
    private String port;

    /**
     * 检索关键词
     */
    @Length(min = 0, max = 512, groups = {Save.class, Update.class})
    @Pattern(regexp = "[1-9]")
    @Email
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