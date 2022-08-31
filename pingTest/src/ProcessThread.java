package src;

import static java.lang.Runtime.getRuntime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class ProcessThread extends Thread {

    private final PingEntity pingEntity;
    private BufferedReader in;

    private InetAddress ips;
    private Process p;

    private ProcessThread(PingEntity pingEntity) {
        this.pingEntity = pingEntity;
    }

    public static ProcessThread of(PingEntity entity) {
        return new ProcessThread(entity);
    }

    @Override
    public void run() {

        connection();

        int istart = pingEntity.getIpTimeLen();
        byte[] buf;

        String inputLine;

        while ((inputLine = ioReadLine())!=null) {

            String ping = partGet(istart, inputLine);

            buf = ("" + Integer.parseInt(ping))
                    .getBytes();

            pingEntity.updatePacket(
                    buf,
                    buf.length,
                    ips,
                    pingEntity.getPort()
            );
        }

    }

    private void connection() {
        try {
            p = getRuntime().exec("ping -t " + pingEntity.getIp());
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            ips = InetAddress.getByName(pingEntity.getPostIp());
        } catch (IOException e) {
            close();
        }
    }

    private String ioReadLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public void close() {
        try {
            in.close();
            p.destroy();
        } catch (IOException ignored) {
        }
    }

    public PingEntity getPingEntity() {
        return pingEntity;
    }

    public String partGet(int istart, String inputLine) {
        String strNumber = "";

        for (int i = istart; i < inputLine.length(); i++) {
            char sc = inputLine.charAt(i);

            if (sc=='m') {
                break;
            }
            if (!(sc >= '0' && sc <= '9')) {
                break;
            }
            strNumber += sc;
        }

        if (strNumber.equals("")) {
            return "0";
        }

        return strNumber;
    }

}
