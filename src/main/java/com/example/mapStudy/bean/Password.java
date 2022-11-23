package com.example.mapStudy.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * password
 *
 * @author
 */
@Data
public class Password implements Serializable {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 检索关键词
     */
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
    private String user;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 查询频率
     */
    private int index;

    private static final long serialVersionUID = 1L;
}