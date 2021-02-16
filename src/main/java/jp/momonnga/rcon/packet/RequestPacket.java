package jp.momonnga.rcon.packet;

import java.util.Random;

public abstract class RequestPacket extends Packet {

    protected RequestPacket(int type) {
        this(type,randomID());
    }

    protected RequestPacket(int type,int id) {
        super(type);
        setID(id);
    }

    protected void setID(int id) {
        super.id = id;
    }

    protected void setBody(String body) {
        super.body = body;
    }

    public static int randomID() {
        Random random = new Random();
        int id;
        while ( (id = random.nextInt()) == -1 ) {}
        return id;
    }

}
