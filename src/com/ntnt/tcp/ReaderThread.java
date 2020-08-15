package com.ntnt.tcp;

import java.io.BufferedReader;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ReaderThread extends Thread{

    private BufferedReader br;
    private String from;
    private Logger logger;

    public ReaderThread(BufferedReader br, String from){
        this.br = br;
        this.from = from;
        this.logger = Logger.getLogger(ReaderThread.class.getName());
    }

    @Override
    public void run() {
        try{
            while (true){
                String message = br.readLine();
                System.out.printf("%s >> %s\n",this.from, message);

                if(message.equalsIgnoreCase("bye bye")){
                    break;
                }
            }
        }catch (Exception e){
            logger.log(new LogRecord(Level.FINE, "Can't read anymore"));
        }
    }
}
