package com.sxx.dto;

import lombok.Data;

import java.io.Serializable;
/**
 * @Author sxx
 * @Description //TODO 对象转换器
 * @Date 9:47 2019/9/24
 **/
@Data
public class RpcConverter implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String fullClassName;//类全名
    private String methodName;
    private Class[] paramsTypes;
    private Object[] params;
}
