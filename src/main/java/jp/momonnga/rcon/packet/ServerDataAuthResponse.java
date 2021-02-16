package jp.momonnga.rcon.packet;

public final class ServerDataAuthResponse extends ResponsePacket {

    //認証パケットの応答パケット

    public static final int PACKET_TYPE = 2;

    public ServerDataAuthResponse(int id,String body) {
        super(PACKET_TYPE,id,body);
    }

    public boolean isSuccess() {
        return super.id != -1;
    }

}
