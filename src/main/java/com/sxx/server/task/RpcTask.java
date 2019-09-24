package com.sxx.server.task;

import com.sxx.dto.RpcConverter;
import com.sxx.service.UserService;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcTask implements Runnable {
    private ServerSocket socket;

    public RpcTask(ServerSocket socket){
        this.socket = socket;
    }
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            //1.监听端口
            while (true){
                Socket accept = socket.accept();
                is = accept.getInputStream();
                ois = new ObjectInputStream(is);
                RpcConverter tr = (RpcConverter) ois.readObject();
                String fullClassName = tr.getFullClassName();
                String methodName = tr.getMethodName();
                Class[] paramsTypes = tr.getParamsTypes();
                Object[] params = tr.getParams();
                //2.服务注册，找到具体的实现类（dubbo是通过zookeeper服务注册实现）
                Class<?> aClass = Class.forName(fullClassName);
                Object o = aClass.newInstance();//实例化该对象
                Method method = aClass.getMethod(methodName, paramsTypes);
                Object result = method.invoke(o, params);
                os = accept.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(result);
                oos.flush();

            }
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
    }
}
