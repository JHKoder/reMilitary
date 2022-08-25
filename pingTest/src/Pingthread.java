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

    private Workping workping;
    private String ip;
    private int threadnumber = 0;
    private int port = 8800;
    private final String postIp;

    public void setWorkping(Workping workping) {
        this.workping = workping;
    }

    public Pingthread(String ip, int threadnumber, String postIp) {
        this.ip = ip;
        this.threadnumber = threadnumber;
        this.postIp = postIp;
    }

    public void run() {
        try {
            Process p = getRuntime().exec("ping -t " + ip);

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;

            int istart = getIpTimeLen(ip);

            InetAddress ips = InetAddress.getByName(postIp);
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket dp;

            for (; ; ) {
                while ((inputLine = in.readLine()) != null) {

                    int ping = 0;

                    String time = partGet(istart, inputLine);

                    if (!Objects.equals(time, "")) {
                        ping = stringToInt(time);
                    }

                    String msg = "" + ping;
                    byte[] buf = msg.getBytes();

                    dp = new DatagramPacket(
                            buf,
                            buf.length,
                            ips,
                            port + threadnumber
                    );

                    workping.postPing(dp, socket, threadnumber, ping);

                }

            }

        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

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