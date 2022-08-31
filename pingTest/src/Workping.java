package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Workping extends Thread {

    private List<PingEntity> pingEntities = new ArrayList<>();
    private List<ProcessThread> processThread = new ArrayList<>();
    private Map<Integer, DatagramPacket> datagramPackets = new HashMap<>();
    private DatagramSocket socket;

    public Workping(List<String> ipList, String postIp, int port) {

        for (int i = 0; i < ipList.size(); i++) {
            pingEntities.add(PingEntity.of(ipList.get(i), postIp, i, port + i));
            processThread.add(ProcessThread.of(pingEntities.get(i)));
        }

        try {
            socket = new DatagramSocket();
        } catch (SocketException ignored) {
            throw new RuntimeException();
        }

    }

    public void run() {

        for (ProcessThread thread : processThread) {
            thread.start();
        }

        new Timer().scheduleAtFixedRate(new ScheduleTask(() -> {
            for (ProcessThread thread : processThread) {
                datagramPackets.put(thread.getPingEntity().getIndex(), thread.getPingEntity().getPacket());
            }
            updSend();
        }), new Date(), 5_000);
    }

    public void shutdown() {
        for (ProcessThread thread : processThread) {
            thread.close();
        }
    }

    public void updSend() {

        try {
            for (DatagramPacket packet : datagramPackets.values()) {
                socket.send(packet);
            }
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