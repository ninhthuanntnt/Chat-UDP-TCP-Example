package com.ntnt.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class WriterThread extends Thread {

    private String ip;
    private int port;
    private DatagramSocket ds;

    public WriterThread(DatagramSocket ds, int port) {
        this.ds = ds;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            while (true) {
                this.ds = new DatagramSocket();
                String message = sc.nextLine();
                DatagramPacket dp = new DatagramPacket(message.getBytes(),
                                                        message.getBytes().length,
                                                        address,
                                                        port);
                ds.send(dp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
