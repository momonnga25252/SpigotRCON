package jp.momonnga.rcon.packet;

public final class ServerDataResponseValue extends ResponsePacket{

    //コマンド実行パケットの応答パケット

    public static final int PACKET_TYPE = 0;

    public ServerDataResponseValue(int id,String body) {
        super(PACKET_TYPE,id,body);
    }

    public String getCommandResponse() {
        return super.body;
    }

}
