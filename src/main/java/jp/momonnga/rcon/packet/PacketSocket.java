package jp.momonnga.rcon.packet;

import jp.momonnga.rcon.utils.Utils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class PacketSocket extends Socket {

    public PacketSocket(String hostname,int port) {
        try {
            connect(new InetSocketAddress(hostname, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PacketSocket(){}

    public ResponsePacket sendPacket(RequestPacket packet) throws IOException {
        getOutputStream().write(toByteArray(packet));
        return toPacket(getInputStream());
    }

    private static byte[] toByteArray(Packet packet) {
        byte[] idByte = Utils.toByteDifEndian(packet.id);
        byte[] typeByte = Utils.toByteDifEndian(packet.type);
        byte[] bodyByte = packet.body.getBytes(StandardCharsets.US_ASCII);
        byte[] sizeByte = Utils.toByteDifEndian(bodyByte.length+10);

        ByteBuffer buf = ByteBuffer.allocate(bodyByte.length+14);

        return buf.put(sizeByte).put(idByte).put(typeByte).put(bodyByte).put((byte) 0).put((byte) 0).array();
    }

    private static int getPacketSize(InputStream inputStream) throws IOException {
        return Utils.readIntLasB(inputStream);
    }

    private static ResponsePacket toPacket(InputStream inputStream) throws IOException {
        int size = getPacketSize(inputStream);
        int id = Utils.readIntLasB(inputStream);
        int type = Utils.readIntLasB(inputStream);
        String body = Utils.readAsString(inputStream,size-10,StandardCharsets.US_ASCII);
        inputStream.skip(2);
        switch (type) {
            case ServerDataResponseValue.PACKET_TYPE:
                return new ServerDataResponseValue(id,body);
            case ServerDataAuthResponse.PACKET_TYPE:
                return new ServerDataAuthResponse(id,body);
            default:
                return null;
        }
    }







}
