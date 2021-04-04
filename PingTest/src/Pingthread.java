package src;

import java.net.*;
import java.lang.Process;
import java.io.*;
import src.*;

public class Pingthread extends Thread{
  private Workping workping;
  private String ip;
  private int ping =0;
  private int threadnumber =0;
  private int port = 8800;
  private String postIp;
  
  public void setWorkping(Workping workping){
     this.workping = workping;
  }
  
  public Pingthread(String ip,int threadnumber, String postIp){
    this.ip=ip;
    this.threadnumber=threadnumber;
    this.postIp = postIp;
  }
  
  public void run(){
     try{
         Runtime r = Runtime.getRuntime();
         Process p = r.exec("ping -t "+ip);
         
         BufferedReader in = 
              new BufferReader(new InputStreamReader
                                    (p.getInputStream() )
                               );
         String inputLine;
         
         int istart = getIpTimeLen(ip);
         
         InetAddress ips = InetAddress.getByName(postIp);
         DatagramSocket = new DatagramSocket();
         
         inputLine = in.readLine();
         DatagramPacket dp;
         
         for(;;){
           while((inputLine = in.readLine()) != null ){
           
             String str = inputLine.split("");
             String strNumber ="";
             
             for(int i= istart;i<str.length;i++){
                 char sc = str[i].charAt(0);
                 if(sc == 'm'){break;}
                 if(!(sc >= '0' && sc <= '9' ) ){
                    setPing(0);
                    break;
                 }
                 strNumber += str[i].charAt(0);
             }//for -for -end
             
             if(strNumber ==""){
                setPing(0);
             }else{
                setPing(StringToInt(strNumber));
             }//for -if-else -end
             
             String msg = ""+ping;
             byte[] buf = msg.getBytes();
             
             dp = new DatagramPacket(
                buf,
                buf.length,
                ips,
                port+threadnumber
               );
        
                 workping.postPing(dp,socket,threadnumber,ping);
                 
           
           }//while -end
         
         }//for -end
         
     }catch(IOException e){System.out.println(e);
     }catch(Exception e){System.out.println(e);}
     //try -catch -catch -end
     
  } //run -end
  
  
  public static int stringToInt(String number){
     int intNumber =0;
     for(int i=0;i<number.length();i++){
         intNumber += number.charAt(i)-48;
         if(number.length()-1 != i){
           intNumber *=10;
         }
     }//for -end
     
     return intNumber;
  }// stringToInt -end
  
  public int getIpTimeLen(String ip){
      int count =0;
      for(int i=0;i<ip.length();i++){
        if(ip.charAt(i) != '.'){
          count++;
        }
      }//for -end 
      
    return count+20; 
  }// getIpTimeLen -end
  
} 