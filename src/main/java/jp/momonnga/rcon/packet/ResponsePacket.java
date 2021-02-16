package jp.momonnga.rcon.packet;

public abstract class ResponsePacket extends Packet {

    protected ResponsePacket(int type,int id,String body) {
        super(type,id,body);
    }

}
