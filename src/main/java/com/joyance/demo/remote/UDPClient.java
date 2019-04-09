package com.joyance.demo.remote;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPClient {

    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        String str = "client";
        byte[] buf = str.getBytes();
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        int port = 9000;
        DatagramPacket spacket = new DatagramPacket(buf, buf.length, addr, port);
        client.send(spacket);
        byte[] rBuf = new byte[100];
        DatagramPacket rpacket = new DatagramPacket(rBuf, rBuf.length);
        client.receive(rpacket);
        str = new String(rpacket.getData(), 0, rpacket.getLength());
        System.out.println("收到:" + str);
        client.close();
    }
}
