package jp.momonnga.rcon.packet;

public abstract class Packet {

    protected int id;
    protected final int type;
    protected String body;

    protected Packet(int type){
        this(type,0,"");
    }

    protected Packet(int type,int id,String body) {
        this.type = type;
        this.id = id;
        this.body = body;
    }

}
