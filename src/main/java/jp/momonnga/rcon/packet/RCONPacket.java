package jp.momonnga.rcon.packet;

import java.util.Arrays;

public enum RCONPacket {

    SERVER_DATA_AUTH(3),
    SERVER_DATA_AUTH_RESPONSE(2),
    SERVER_DATA_EXECUTE_COMMAND(2),
    SERVER_DATA_RESPONSE_VALUE(0);

    private int type;

    RCONPacket(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static ServerDataPacket createRequestPacket(RCONPacket rconPacket) {
        if (!isRequestPacket(rconPacket)) throw new IllegalArgumentException();
        return new ServerDataPacket(rconPacket);
    }

    public static boolean isRequestPacket(RCONPacket rconPacket) {
        switch (rconPacket) {
            case SERVER_DATA_AUTH:
            case SERVER_DATA_EXECUTE_COMMAND:
                return true;
            default:
                return false;
        }
    }

    public static RCONPacket getRCONPacket(int type,boolean request) {
        return Arrays.stream(values())
                .filter(packet -> packet.getType() == type)
                .filter(packet -> isRequestPacket(packet) == request )
                .findFirst().get();
    }
}
