package jp.momonnga.rcon;

import jline.console.ConsoleReader;
import jline.console.UserInterruptException;
import jp.momonnga.rcon.packet.*;

import java.io.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RCON {

    public static void main(String[] args) throws IOException{
        ConsoleReader console = new ConsoleReader();
        Console cs = System.console();
        if (cs == null) {
            System.out.println("コンソールの取得に失敗しました");
            return;
        }

        System.out.println("SpigotRCONを起動しました");

        app: while (true) {
            String host = console.readLine("ホストIPを入力してください >> ");

            int port = 25575;
            String text = "接続ポートを入力してください 入力のない場合は25575になります >> ";
            while (true) {
                String portStr = console.readLine(text);
                try {
                    if (portStr.equals("")) break;
                    port = Integer.parseInt(portStr);
                    break;
                } catch (ClassCastException e) {
                    text = "正しいポートを入力してください 入力のない場合は25575になります >> ";
                }
            }

            String password = console.readLine("認証パスワードを入力してください >> ");

            System.out.println("ホストに接続中");
            PacketSocket pSocket = new PacketSocket(host, port);

            ServerDataAuth auth = new ServerDataAuth();
            auth.setPassword(password);

            System.out.println("認証中");
            ServerDataAuthResponse authResponse = (ServerDataAuthResponse) pSocket.sendPacket(auth);

            if (authResponse.isSuccess()) {
                System.out.println("ホストに接続しました");
                while (true) {
                    try {
                        String command = console.readLine("コマンドを入力してください >> ");
                        sendCommand(pSocket, command);
                    } catch (UserInterruptException e) {
                        System.out.println("通信を切断中");
                        pSocket.close();
                        break app;
                    }
                }
            } else {
                System.out.println("入力内容に誤りがあります");
            }
        }
        System.out.println("SpigotRCONを終了します");

    }

    public static void sendCommand(PacketSocket packetSocket,String command) throws IOException {
        ServerDataExecuteCommand executeCommand = new ServerDataExecuteCommand();
        executeCommand.setCommand(command);

        ServerDataResponseValue responseValue = (ServerDataResponseValue) packetSocket.sendPacket(executeCommand);
        System.out.println("["+packetSocket.getInetAddress().getHostName()+"] >> "+responseValue.getCommandResponse());
    }






}
