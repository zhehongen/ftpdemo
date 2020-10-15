package com.example.ftpdemo.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "ftp_user")
public class FtpUser {
    @Id
    @Column(columnDefinition = "varchar(64) COMMENT '用户ID'")
    private String userid;

    @Column(columnDefinition = "varchar(64) COMMENT '密码'")
    private String userpassword;

    @Column(columnDefinition = "varchar(512) COMMENT '用户根目录'")
    private String homedirectory;

    @Column(columnDefinition = "tinyint(1) default 1 COMMENT '是否启用'")
    private Integer enableflag;

    @Column(columnDefinition = "tinyint(1) default 1 COMMENT '是否有写权限'")
    private Integer writepermission;


    @Column(columnDefinition = "int default 0 COMMENT '空闲时间'")
    private Integer idletime;

    @Column(columnDefinition = "int default 0 COMMENT '上传速率'")
    private Integer uploadrate;

    @Column(columnDefinition = "int default 0 COMMENT '下载速率'")
    private Integer downloadrate;

    @Column(columnDefinition = "int default 0 COMMENT '最大登录数'")
    private Integer maxloginnumber;

    @Column(columnDefinition = "int default 0 COMMENT '每个ip最大登录数'")
    private Integer maxloginperip;

    @Column(columnDefinition = "datetime COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "varchar(20) COMMENT '创建人ID'")
    private String creator;

    @Column(columnDefinition = "datetime COMMENT '更改时间'")
    private Date updateTime;

    @Column(columnDefinition = "varchar(20) COMMENT '更改人ID'")
    private String updater;
}
