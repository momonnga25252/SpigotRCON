package jp.momonnga.rcon;

import jp.momonnga.rcon.packet.PacketSocket;
import jp.momonnga.rcon.packet.RCONPacket;
import jp.momonnga.rcon.packet.ServerDataPacket;

import java.io.IOException;

public class Client {

    private PacketSocket socket;

    public void connect(Server server, String password) throws Exception {
        if(socket != null && socket.isConnected()) disconnect();
        socket = new PacketSocket(server);
        socket.connect();
        if(!auth(password)) {
            throw new IllegalArgumentException();
        }
    }

    public String sendCommand(String command) throws IOException {
        if(socket == null) throw new NullPointerException();
        if(socket.isConnected()) {
            ServerDataPacket commandPacket = new ServerDataPacket(RCONPacket.SERVER_DATA_EXECUTE_COMMAND);
            commandPacket.setBody(command);
            return socket.sendPacket(commandPacket).getBody();
        }
        throw new IOException();
    }

    public void disconnect() throws IOException{
        socket.close();
    }

    private boolean auth(String password) throws IOException {
        ServerDataPacket serverDataPacket = new ServerDataPacket(RCONPacket.SERVER_DATA_AUTH);
        serverDataPacket.setBody(password);
        ServerDataPacket response = socket.sendPacket(serverDataPacket);
        return response.getId() != -1;
    }


}
