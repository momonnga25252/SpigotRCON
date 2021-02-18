package jp.momonnga.rcon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Random;

public class Utils {

    public static byte[] read(InputStream inputStream, int size) throws IOException {
        byte[] b = new byte[size];
        for (int i = 0;i < size;i++) {
            b[i] = (byte) inputStream.read();
        }
        return b;
    }

    public static int readIntLasB(InputStream inputStream) throws IOException{
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).put(read(inputStream,4)).getInt(0);
    }

    public static String readAsString(InputStream inputStream, int byteSize, Charset charsets) throws IOException {
        if (byteSize == 0) return "";
        return new String(read(inputStream,byteSize), charsets);
    }

    public static byte[] toByteDifEndian(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }
    public static int randomID() {
        Random random = new Random();
        int id;
        while ( (id = random.nextInt()) == -1 ) {}
        return id;
    }
}
