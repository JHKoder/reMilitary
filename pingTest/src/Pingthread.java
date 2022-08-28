package src;

import static java.lang.Runtime.getRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;

public class Pingthread extends Thread {

    private final String postIp;
    private BufferedReader in;
    private DatagramSocket socket;
    private DatagramPacket dp;
    private Process p;


    private String ip;
    private int threadnumber = 0;
    private int port = 8800;


    public Pingthread(String ip, int threadnumber, String postIp) {
        this.ip = ip;
        this.threadnumber = threadnumber;
        this.postIp = postIp;
    }

    public void run() {
        try {
            p = getRuntime().exec("ping -t " + ip);
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String inputLine;
            int istart = getIpTimeLen(ip);

            InetAddress ips = InetAddress.getByName(postIp);
            socket = new DatagramSocket();

            while ((inputLine = in.readLine()) != null) {
                int ping = 0;

                String time = partGet(istart, inputLine);

                if (!Objects.equals(time, "")) {
                    ping = stringToInt(time);
                }

                byte[] buf = ("" + ping).getBytes();

                dp = new DatagramPacket(
                        buf,
                        buf.length,
                        ips,
                        port + threadnumber
                );

            }

        } catch (Exception e) {
            close();
        }
        close();
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public DatagramPacket getDp() {
        return dp;
    }

    public int getThreadnumber() {
        return threadnumber;
    }


    public void close() {
        try {
            in.close();
            socket.close();
        } catch (IOException ignored) {

        }
    }

    public void processClose() {
        p.destroy();
    }

    private String partGet(int istart, String inputLine) {
        String strNumber = "";

        for (int i = istart; i < inputLine.length(); i++) {
            char sc = inputLine.charAt(i);

            if (sc == 'm') {
                break;
            }
            if (!(sc >= '0' && sc <= '9')) {
                break;
            }
            strNumber += sc;
        }

        return strNumber;
    }


    public static int stringToInt(String number) {
        int intNumber = 0;
        for (int i = 0; i < number.length(); i++) {
            intNumber += number.charAt(i) - 48;
            if (number.length() - 1 != i) {
                intNumber *= 10;
            }
        }

        return intNumber;
    }

    public int getIpTimeLen(String ip) {
        int count = 0;
        for (int i = 0; i < ip.length(); i++) {
            if (ip.charAt(i) != '.') {
                count++;
            }
        }

        return count + 19;
    }
} 