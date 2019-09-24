package com.sxx.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserDto implements Serializable{
    private static final Long serialVersionUID = 1L;

    private String userName;
    private Integer age;
    private String sex;
}
