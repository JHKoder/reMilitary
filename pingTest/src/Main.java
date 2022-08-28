package src;

import static src.ReadText.getTextIpList;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String postIp = "127.0.0.1";

        List<String> ipList = getTextIpList();
        int ipLen = ipList.size();

        Pingthread[] pings = new Pingthread[ipLen];

        for (int i = 0; i < ipLen; i++) {
            pings[i] = new Pingthread(ipList.get(i), i, postIp);
            pings[i].start();
        }

        Workping workping = new Workping(pings, ipLen);

        workping.start();

    }

}