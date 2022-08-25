package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadText {

    public static List<String> getTextIpList() {
        try {

            File file =new File("pingTest\\web\\WEB-INF\\iplist\\ipList.txt");
            FileReader filereader = new FileReader(file);


            List<String> ary = new ArrayList<>();

            int singleCh = 0;
            boolean isSingle = false;

            int i = 0;
            String st = "";

            while ((singleCh = filereader.read()) != -1) {
                char ch = (char) singleCh;

                if (ch == '\r') {
                    ary.add(i++, st);
                    st = "";
                    isSingle = false;
                }

                if (isSingle) {
                    st += "" + ch;
                }
                if (ch == ':') {
                    isSingle = true;
                }

            }
            filereader.close();
            return ary;

        } catch (FileNotFoundException e1) {
            System.out.println("notFound");
        } catch (Exception e2) {
            System.out.println("Exception ");
        }

        return null;
    }


}