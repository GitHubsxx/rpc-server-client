package com.sxx.client.proxy;

import com.sxx.dto.RpcConverter;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class ClientProxyHandler implements InvocationHandler {
    private String host;
    private int port;
    private String fullClassName;

    public ClientProxyHandler(String host, int port, String fullClassName) {
        this.host = host;
        this.port = port;
        this.fullClassName = fullClassName;
    }
    public Object invoke(Object proxy, Method method, Object[] args){
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Object result = null;
        try {
            socket = new Socket("localhost",8080);
            RpcConverter converter = new RpcConverter();
            converter.setFullClassName(fullClassName);
            converter.setMethodName(method.getName());
            converter.setParams(args);
            Class[] paramsTypes = new Class[args.length];
            for(int i = 0; i < args.length; i++){
                Class clazz = args[i].getClass();
                paramsTypes[i] = clazz;
            }
            converter.setParamsTypes(paramsTypes);

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(converter);

            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            result = ois.readObject();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null){
                    is.close();
                }
                if(ois !=null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
