package com.ntnt.udp;

import java.net.DatagramSocket;

public class Server {
    private static final int SERVER_PORT = 9999;
    private static final int CLIENT_PORT = 8888;
    public static void main(String[] args) {

        try (
                DatagramSocket ds = new DatagramSocket(SERVER_PORT);
        ) {
            ReaderThread readerThread = new ReaderThread(ds);
            WriterThread writerThread = new WriterThread(ds, CLIENT_PORT);
            writerThread.setDaemon(false);
            readerThread.setDaemon(false);

            readerThread.start();
            writerThread.start();
            readerThread.join();
            writerThread.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
