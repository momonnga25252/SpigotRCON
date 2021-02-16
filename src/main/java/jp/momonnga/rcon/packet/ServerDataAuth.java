package jp.momonnga.rcon.packet;

public final class ServerDataAuth extends RequestPacket{

    //認証パケット

    public static final int PACKET_TYPE = 3;

    public ServerDataAuth() {
        super(PACKET_TYPE);
    }

    public void setPassword(String password) {
        setBody(password);
    }
}
