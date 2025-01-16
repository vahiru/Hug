# HugProject

HugProject是一个使用Java构建的项目，旨在通过互联网传递温暖，帮助减少精神疾病的发生。该项目允许用户通过简单的命令行操作发送和接收“hug”消息，从而感受到他人的温暖关怀。

## 功能

- 在指定端口（默认8520）上监听和接收hug消息。
- 向目标IP地址发送hug消息。
- 显示发送端和接收端的IP地址。
- 支持接收端通过命令行回复hug消息。

## 使用方法

### 1. 启动服务器

运行HugProgram.java，服务器将监听端口8520并等待接收hug消息。

```bash
java -jar HugProject.jar
```

### 2. 发送hug消息

在运行服务器的同时，输入目标IP地址，程序将向目标发送hug消息。

### 3. 接收和回复hug消息

当服务器接收到hug消息时，会显示发送方的IP地址并提示“want to hug you”。用户可以在命令行输入 `hug accept` 来回复hug消息，发送方将收到回复并显示“hug you”。

## 示例

```plaintext
$ java HugProject.jar
Hug服务器已启动，监听端口：8520
请输入目标IP地址：192.168.1.2
Hug消息已发送至 192.168.1.2

（接收方）
192.168.1.1 want to hug you.
hug accept
Hug消息已发送至 192.168.1.1
```

## 项目背景

在现代社会中，人们越来越多地通过互联网进行交流，但往往缺乏面对面的互动。HugProject的目标是通过简洁的命令行互动，帮助用户感受到他人的温暖，从而减少孤独感和精神疾病的发生。

## 贡献

欢迎对HugProject做出贡献！您可以通过提交Pull Request或报告问题来帮助改进这个项目。

## 构建

`mvn clean install`