package xyz.vahiru.Hug;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HugService hugService = new HugService();
        Scanner scanner = new Scanner(System.in);
        
        // 询问用户的名字
        System.out.print("请输入您的名字：");
        SharedData.userName = scanner.nextLine();

        System.out.print("选择模式（client/server）：");
        String mode = scanner.nextLine();

        if ("client".equalsIgnoreCase(mode)) {
            System.out.print("要抱抱谁（输入IP地址）：");
            String ip = scanner.nextLine();
            int port = 8520; // 可以根据需要修改端口号

            try {
                hugService.sendHugRequest(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if ("server".equalsIgnoreCase(mode)) {
            int port = 8520; // 可以根据需要修改端口号

            try {
                hugService.startHugServer(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("无效的模式选择。");
        }
    }
}