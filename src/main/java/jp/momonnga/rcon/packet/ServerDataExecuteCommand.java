package jp.momonnga.rcon.packet;

public final class ServerDataExecuteCommand extends RequestPacket {

    //コマンド実行パケット

    public static final int PACKET_TYPE = 2;

    public ServerDataExecuteCommand() {
        super(PACKET_TYPE);
    }

    public void setCommand(String command) {
        setBody(command);
    }

}
