package com.sxx.client;

import com.sxx.client.proxy.ClientProxy;
import com.sxx.dto.ServerDto;
import com.sxx.dto.UserDto;
import com.sxx.service.UserService;
import com.sxx.util.FileUtil;

/**
 * @Author sxx
 * @Description 服务调用者
 * @Date 13:02 2019/9/24
 **/
public class ClientApp {
    public static void main(String[] args){
        ServerDto dto = FileUtil.readProperties();
        ServerDto dto1 = FileUtil.readXMLByDom4j();
        ClientProxy clientProxy = new ClientProxy(dto.getHost(), dto.getPort());
        Class<?> interClass = null;
        try {
            interClass = Class.forName(dto1.getInterfaceFullName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserService service = (UserService) clientProxy.getService(interClass,dto1.getImplFullName());
        UserDto user = service.getUser(0L);
        System.out.print("name:"+user.getUserName()+","+"sex:"+user.getSex()+","+"age:"+user.getAge());
    }
}
