package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        String postIp = "127.0.0.1";
        int port = 8800;

        Workping workping = new Workping(ReadText.getTextIpList(), postIp, port);
        workping.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                if (Objects.equals(br.readLine(), "shutdown")) {
                    workping.shutdown();
                }
            }
        } catch (IOException e) {
            workping.shutdown();
        }
    }

}