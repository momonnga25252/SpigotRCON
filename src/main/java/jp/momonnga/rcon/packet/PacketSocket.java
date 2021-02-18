package jp.momonnga.rcon.packet;

import jp.momonnga.rcon.Server;
import jp.momonnga.rcon.utils.Utils;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketSocket extends Socket {

    private final Server host;

    public PacketSocket(Server host) {
        this.host = host;
    }

    public void connect() throws IOException {
        connect(host.getSocketAddress());
    }

    public ServerDataPacket sendPacket(ServerDataPacket packet) throws IOException {
        getOutputStream().write(toByteArray(packet));
        return toPacket(getInputStream());
    }

    private static byte[] toByteArray(ServerDataPacket serverDataPacket) {
        byte[] idByte = Utils.toByteDifEndian(serverDataPacket.id);
        byte[] typeByte = Utils.toByteDifEndian(serverDataPacket.getType().getType());
        byte[] bodyByte = serverDataPacket.body.getBytes(StandardCharsets.US_ASCII);
        byte[] sizeByte = Utils.toByteDifEndian(bodyByte.length+10);

        ByteBuffer buf = ByteBuffer.allocate(bodyByte.length+14);

        return buf.put(sizeByte).put(idByte).put(typeByte).put(bodyByte).put((byte) 0).put((byte) 0).array();
    }

    private static int getPacketSize(InputStream inputStream) throws IOException {
        return Utils.readIntLasB(inputStream);
    }

    private static ServerDataPacket toPacket(InputStream inputStream) throws IOException {
        int size = getPacketSize(inputStream);
        int id = Utils.readIntLasB(inputStream);
        int type = Utils.readIntLasB(inputStream);
        String body = Utils.readAsString(inputStream,size-10,StandardCharsets.US_ASCII);
        inputStream.skip(2);
        return new ServerDataPacket(RCONPacket.getRCONPacket(type,false),id,body);
    }





}
