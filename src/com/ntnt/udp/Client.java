package com.ntnt.udp;

import java.net.DatagramSocket;

public class Client {

    private static final int SERVER_PORT = 9999;
    private static final int CLIENT_PORT = 8888;

    public static void main(String[] args) {

        try (
                DatagramSocket ds = new DatagramSocket(CLIENT_PORT);
        ) {

            WriterThread writerThread = new WriterThread(ds, SERVER_PORT);
            ReaderThread readerThread = new ReaderThread(ds);
            readerThread.start();
            writerThread.start();

            readerThread.join();
            writerThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
