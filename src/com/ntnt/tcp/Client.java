package com.ntnt.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Client {

    private static Logger logger = Logger.getLogger(Client.class.getName());
    public static void main(String[] args) {

        try(
                Socket socket = new Socket("localhost", 9999);
                ){
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            ReaderThread readerThread = new ReaderThread(br, socket.toString());
            WriterThread writerThread = new WriterThread(bw);

            readerThread.start();
            writerThread.start();
            readerThread.join();
            writerThread.join();
        }catch (Exception e){
            logger.log(new LogRecord(Level.FINE, e.getMessage()));
        }

        logger.log(new LogRecord(Level.INFO, "Disconnect to server"));
    }

    private static void sendMessage(String message, BufferedWriter bw){
        try{
            bw.write(message);
            bw.newLine();
            bw.flush();
        }catch (Exception e){
            logger.log(new LogRecord(Level.FINE, "Can't send message"));
        }
    }
}
