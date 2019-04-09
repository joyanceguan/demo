package com.joyance.demo.remote;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class TCPClient {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        try {
            String ip = "121.42.143.166";
            Socket socket = new Socket(ip, 9000);
            // 向服务器端发送数据
            OutputStream os = socket.getOutputStream();
            DataOutputStream bos = new DataOutputStream(os);
            bos.writeUTF("Connect");
            bos.flush();

            // 接收服务器端数据
            InputStream is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            System.out.println(dis.readUTF());
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
