package com.jilinwula.security.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint(10)  comment '主键'")
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(10) not null default '' comment '账号'")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(10) not null default '' comment '密码'")
    private String password;

}
