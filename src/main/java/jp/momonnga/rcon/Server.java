package jp.momonnga.rcon;

import java.net.InetSocketAddress;

public class Server {

    private InetSocketAddress socketAddress;

    public Server(InetSocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public Server(String address,int port) {
        this(new InetSocketAddress(address,port));
    }

    public InetSocketAddress getSocketAddress() {
        return socketAddress;
    }

}
