package com.ntnt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReaderThread extends Thread {

    private DatagramSocket ds;
    private byte[] buffer;

    public ReaderThread(DatagramSocket ds) {
        this.ds = ds;
        this.buffer = new byte[4096];
    }

    @Override
    public void run() {
        while (true) {
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

            try {
                ds.receive(dp);
                String message = new String(dp.getData());

                System.out.printf("%s:%s>> %s\n", dp.getAddress().getHostName(), dp.getPort(), message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
