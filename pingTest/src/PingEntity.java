package src;

import java.net.DatagramPacket;
import java.net.InetAddress;

//@Getter
public class PingEntity {

    private DatagramPacket packet;

    private String postIp;
    private String ip;

    private int index;
    private int port;

    private PingEntity(String ip, String postIp, int index, int port) {
        this.ip = ip;
        this.postIp = postIp;
        this.index = index;
        this.port = port;
    }

    public static PingEntity of(String ip, String postIp, int index, int port) {
        return new PingEntity(ip, postIp, index, port);
    }

    public void updatePacket(byte[] buf, int length, InetAddress ips, int index) {
        packet = new DatagramPacket(buf, length, ips, index);
    }

    public DatagramPacket getPacket() {
        return packet;
    }

    public int getIpTimeLen() {
        int count = 0;
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i)!='.') {
                count++;
            }
        }

        return count + 19;
    }

    public String getPostIp() {
        return postIp;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getIndex() {
        return index;
    }

}
