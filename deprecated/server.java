package xyz.vahiru.Hug;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Hugserver {

    public static void main(String[] args) throws IOException {
        int port = 8520;
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
                    System.out.println(clientAddress.getHostAddress() + " want to hug you.");

                    // 等待用户输入hug accept命令
                    Scanner scanner = new Scanner(System.in);
                    String command = scanner.nextLine();
                    if ("hug accept".equalsIgnoreCase(command)) {
                        String response = clientAddress.getHostAddress() + " hug you";
                        buffer = response.getBytes();
                        packet = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                        socket.send(packet);
                        System.out.println("Hug消息已发送至 " + clientAddress.getHostAddress());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}