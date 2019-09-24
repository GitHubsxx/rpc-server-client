package com.sxx.server.proxy;

import com.sxx.server.task.RpcTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author sxx
 * @Description 服务端代理类
 * @Date 15:57 2019/9/23
 **/
public class ServerProxy {
  private ExecutorService exec = Executors.newFixedThreadPool(5);

  public void publish(int port){
      try {
          ServerSocket socket = new ServerSocket(port);
          exec.submit(new RpcTask(socket));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
