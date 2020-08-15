package com.ntnt.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Server {

    private static Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {

        try (
                ServerSocket server = new ServerSocket(9999);
        ) {
            while (true) {
                Socket socket = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                ReaderThread readerThread = new ReaderThread(br, socket.toString());
                WriterThread writerThread = new WriterThread(bw);

                readerThread.setDaemon(false);
                writerThread.setDaemon(false);

                System.out.printf("%s joined\n", socket.toString());

                readerThread.start();
                writerThread.start();
            }

        } catch (Exception e) {
            logger.log(new LogRecord(Level.FINE, e.getMessage()));
        }
    }
}
