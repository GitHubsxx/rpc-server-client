package com.sxx.server;

import com.sxx.dto.ServerDto;
import com.sxx.server.proxy.ServerProxy;
import com.sxx.util.FileUtil;

/**
 * @Author sxx
 * @Description 服务提供者
 * @Date 15:56 2019/9/23
 **/
public class ServerApp {
    public static void main(String[] args){
        //1.Socket绑定本地端口
        ServerDto dto = FileUtil.readProperties();
        new ServerProxy().publish(dto.getPort());
        System.out.print("Server start..........");
    }
}
