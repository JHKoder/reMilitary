package src.file;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadText{
 
 public static String[] getTextIpList(){
    try{
        File file = 
            new File("C:\\kang\\tomcat\\web\\WEB-INF\\iplist\\ipList.txt");
        FileReader filereader = new FileReader(file);
        
        List ary = new ArrayList();
        int singleCh =0;
        boolean isSingle =false;
        
        int i=0;
        String st="";
        
        while( (singleCh = filereader.read()) != -1 ){
        
            char ch = (char)singleCh;
            
            if(ch =='\r'){
               ary.add(i,st);
               st="";
               isSingle = false;
               i++;
            }
            
            if(isSIngle == true){
               st += ""+ch;
            }
            if(ch == ':'){
              isSingle = true;
            }
            
            
            ary.add(i,st);
            filereader.close();
        
          
            return arrayToStringArray(ary);
        }//while -end
 
    }catch(FileNotFoundException e1){    
    }catch(Exception e2){}
   
    return null;
 }//getTextIpList -end
 
 
 public static String[] arrayToStringArray(List ary){
 
    String[] str = new String[ary.size()];
    
    for(int i=0;i<ary.size();i++){
        str[i] = ary.get(i).toString();
    }
    return str;
    
 }//arrayToStringArray -end
 
 
}