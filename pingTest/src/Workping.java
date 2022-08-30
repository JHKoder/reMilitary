package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Workping extends Thread {

    private List<PingEntity> pingEntities = new ArrayList<>();
    private List<ProcessThread> processThread = new ArrayList<>();
    private DatagramPacket[] dpArray;
    private DatagramSocket socket;

    public Workping(List<String> ipList, String postIp, int port) {

        for (int i = 0; i < ipList.size(); i++) {
            pingEntities.add(PingEntity.of(ipList.get(i), postIp, i, port));
        }

        dpArray = new DatagramPacket[ipList.size()];

        try {
            socket = new DatagramSocket();
        } catch (SocketException ignored) {
            //@Transactional -> Exception 터트림
            throw new RuntimeException();
        }

    }

    public void run() {

        for (PingEntity entity : pingEntities) {
            processThread.add(ProcessThread.of(entity));
        }

        for (ProcessThread thread : processThread) {
            thread.start();
        }

        new Timer().scheduleAtFixedRate(new ScheduleTask(() -> {
            for (ProcessThread thread : processThread) {
                dpArray[thread.getPingEntity().getIndex()] = thread.getPingEntity().getPacket();
            }
            postSend();
        }), new Date(), 5_000); // 5초 마다 send
    }

    public void shutdown() {
        for (ProcessThread thread : processThread) {
            thread.close();
        }
    }

    public void postSend() {

        try {
            for (DatagramPacket datagramPacket : dpArray) {
                socket.send(datagramPacket);
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