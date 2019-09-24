package com.sxx.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ServerDto implements Serializable{
    private static final Long serialVersionUID = 1L;

    private String host;
    private int port;
    private String interfaceFullName;
    private String implFullName;
}
