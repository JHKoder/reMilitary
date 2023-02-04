package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import src.os.Computer;

public class Controller {

    public static void main(String[] args) {

        String udpConnection = "127.0.0.1";
        int startPointPortNumber = 8800;
        int endPointPortNumber = 8899;

        Map<String, String> ipLists = new HashMap<>();
        ipLists.put("서버1", "127.0.0.1");
        ipLists.put("서버2", "127.0.0.2");
        ipLists.put("서버3", "127.0.0.3");

        LiveActionContext live = LiveActionContext.builder()
                .os(Computer.WINDOWS)
                .ping(ipLists)       // 조회 하고 싶은 IP
                .disk()                     // 모든 디스크 용량 나중에는 C: D: F: G: 별로추가
                .cpu()                      // cpu 사용량     나중에는 코어 온도 추가
                .memory()                   // 메모리 사용량
                .build();

        LiveManagement liveManagement = new LiveManagement(live, udpConnection, startPointPortNumber,endPointPortNumber);
        liveManagement.start();

        command(liveManagement);
    }

    private static void command(LiveManagement liveManagement) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (true) {
                if (Objects.equals(br.readLine(), "shutdown")) {
                    liveManagement.shutdown();
                }
            }
        } catch (IOException e) {
            liveManagement.shutdown();
        }
    }

}