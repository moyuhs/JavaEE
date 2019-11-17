package com.java.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String address;
    private String qq;
    private String email;
    private String username;
    private String password;
}
