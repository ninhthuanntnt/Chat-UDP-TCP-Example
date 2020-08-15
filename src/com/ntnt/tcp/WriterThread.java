package com.ntnt.tcp;

import java.io.BufferedWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class WriterThread extends Thread{
    private BufferedWriter bw;
    private Logger logger;
    public WriterThread(BufferedWriter bw){
        this.bw = bw;
        this.logger = Logger.getLogger(WriterThread.class.getName());
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try{
            while (true){
                String message = sc.nextLine();

                bw.write(message);
                bw.newLine();
                bw.flush();

            }

        }catch (Exception e){

            logger.log(new LogRecord(Level.FINE, "Can't write anymore"));
        }
    }
}
