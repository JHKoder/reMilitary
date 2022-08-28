package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Workping extends Thread {

    private Pingthread[] pingthread;
    private int len;
    private boolean[] pingboolean;

    private DatagramSocket[] dsArray;
    private DatagramPacket[] dpArray;

    public Workping(Pingthread[] pingthread, int len) {
        this.pingthread = pingthread;
        this.len = len;

        pingboolean = new boolean[len];
        dsArray = new DatagramSocket[len];
        dpArray = new DatagramPacket[len];
    }

    public void run() {
        new Timer().scheduleAtFixedRate(new ScheduleTask(() -> {
            for (Pingthread ls : pingthread) {
                select(ls.getDp(), ls.getSocket(), ls.getThreadnumber());
            }
            postSend();
        }), new Date(), 5_000); // 5√  ∏∂¥Ÿ send
    }


    public synchronized void select(DatagramPacket bp, DatagramSocket socket, int threadnumber) {
        dsArray[threadnumber] = socket;
        dpArray[threadnumber] = bp;
        pingboolean[threadnumber] = true;
    }

    public void shutdown() {
        for (int i = 0; i < pingthread.length; i++) {
            pingthread[i].processClose();
        }
    }

    public void postSend() {

        try {
            for (int i = 0; i < len; i++) {
                dsArray[i].send(dpArray[i]);
            }
            System.out.println("send-");
        } catch (Exception ignored) {
        }

    }

    private static class ScheduleTask extends TimerTask {

        private final Runnable runnable;

        public ScheduleTask(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            runnable.run();
        }

    }

}