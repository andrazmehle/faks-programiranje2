package domace.dn11;

import java.io.*;
import java.util.Arrays;

public class DN11 {
    public static void main(String[] args) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(args[0]));
            byte[] buffer = new byte[1024];
            dis.read(buffer, 0 ,8);

            while(dis.available() > 0) {
                int len = dis.readInt();
                byte[] hdr = new byte[4];
                dis.read(hdr);

                System.out.printf("Chunk: " + new String(hdr) + ", length: " + len + "\n");
                len += 4;
                while (len > 0) {
                    int read = dis.read(buffer, 0, Math.min(len, 1024));
                    len -= read;
                }
            }
            dis.close();
        } catch (Exception e) {
            System.out.println("Napaka " + e);
        }
    }
}
