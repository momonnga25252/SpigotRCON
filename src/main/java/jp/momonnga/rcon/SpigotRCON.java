package jp.momonnga.rcon;

import jline.console.ConsoleReader;

import java.io.Console;

public class SpigotRCON extends Client {

    public static void main(String[] args) throws Exception {
        SpigotRCON spigotRCON = new SpigotRCON();

        System.out.println("SpigotRCONを起動しました");
        spigotRCON.boot();
    }

    public void boot() throws Exception {
        ConsoleReader console = new ConsoleReader();
        Console cs = System.console();
        if (cs == null) {
            System.out.println("コンソールの取得に失敗しました");
            return;
        }
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
            Server server = new Server(host,port);


            try {
                connect(server,password);
                System.out.println("ホストに接続しました");
                while (true) {
                    String command = console.readLine("コマンドを入力してください >> ");
                    if(command.equals("exit")) {
                        disconnect();
                        break app;
                    }
                    System.out.println("["+server.getSocketAddress().getHostName()+"] >> "+sendCommand(command));
                }
            } catch (IllegalArgumentException e) {
                System.out.println("入力内容に誤りがあります");
            }

        }
        System.out.println("SpigotRCONを終了します");

    }




}
