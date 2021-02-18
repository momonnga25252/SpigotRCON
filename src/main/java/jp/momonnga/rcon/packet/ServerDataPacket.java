package jp.momonnga.rcon.packet;

import jp.momonnga.rcon.utils.Utils;

public class ServerDataPacket {

    //パケットの雛形

    protected int id;
    protected final RCONPacket type;
    protected String body;

    public ServerDataPacket(RCONPacket type){
        this(type, Utils.randomID(),"");
    }

    ServerDataPacket(RCONPacket type, int id, String body) {
        this.type = type;
        this.id = id;
        this.body = body;
    }

    public RCONPacket getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(int id) {
        this.id = id;
    }

}
