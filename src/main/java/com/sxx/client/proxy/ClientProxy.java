package com.sxx.client.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author sxx
 * @Description 客户端代理，生成相应的接口
 * @Date 13:08 2019/9/24
 **/
public class ClientProxy {
    private String host;
    private int port;

    public ClientProxy(String host,int port){
        this.host = host;
        this.port = port;
    }

    public Object getService(Class interfaceName,String fullClassName){
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { interfaceName },
                new ClientProxyHandler(host, port, fullClassName));
        return proxy;

    }
}
