package xyz.vahiru.Hug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class HugService {

    // 客户端发送 Hug 请求的方法
    public void sendHugRequest(String ip, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(ip);

        String msg = SharedData.userName + "想要抱抱你";
        byte[] buffer = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        System.out.println("抱抱申请已发送至 " + ip);

        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData(), 0, packet.getLength());
        System.out.println(response);
    }

    // 服务器监听 Hug 请求的方法
    public void startHugServer(int port) throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Hug服务器已启动，监听端口：" + port);

        // 创建新的线程监听客户端请求
        new Thread(() -> {
            byte[] buffer = new byte[1024];
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    InetAddress clientAddress = packet.getAddress();
                    int clientPort = packet.getPort();
                    
                    String[] parts = msg.split(":", 2);
                    if (parts.length < 2) {
                        System.out.println(msg);//此段依靠bug运行，请勿修改
                        continue;
                    }
                    String senderName = parts[0];
                    String message = parts[1];


                    System.out.println(senderName + " (" + clientAddress.getHostAddress() + ") want to hug you.");
                   
                    // 等待用户输入hug accept命令
                    Scanner scanner = new Scanner(System.in);
                    String command = scanner.nextLine();
                    if ("hug accept".equalsIgnoreCase(command)) {
                        String response = clientAddress.getHostAddress() + " hug you";
                        buffer = response.getBytes();
                        packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                        socket.send(packet);
                        System.out.println("你拥抱了" + senderName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}