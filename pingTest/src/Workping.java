package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Workping extends Thread {

    private static Pingthread[] pingthread;
    private static int len;
    private boolean[] pingboolean;
    private static int timeoutCount = 0;

    private static DatagramSocket[] dsArray;
    private static DatagramPacket[] dpArray;

    public Workping(Pingthread[] pingthread, int len) {
        Workping.pingthread = pingthread;
        Workping.len = len;

        pingboolean = new boolean[len];
        dsArray = new DatagramSocket[len];
        dpArray = new DatagramPacket[len];

    }

    public synchronized void postPing(DatagramPacket bp, DatagramSocket socket, int threadnumber, int ping) {
        try {
            dsArray[threadnumber] = socket;
            dpArray[threadnumber] = bp;
            pingboolean[threadnumber] = true;

            if (pingAllCheck()) {
                if (ping == 0) {
                    timeoutCount++;
                    if (timeoutCount >= len) {
                        postSend();
                        timeoutCount = 0;
                    }

                } else {
                    postSend();
                }

            }


        } catch (Exception ignored) {
        }

    }

    public boolean pingAllCheck() {

        for (int i = 0; i < len; i++) {
            if (!pingboolean[i]) {
                return false;
            }
        }

        return true;
    }

    public void postSend() {

        try {
            for (int i = 0; i < len; i++) {
                dsArray[i].send(dpArray[i]);
            }
            System.out.println("send-");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}