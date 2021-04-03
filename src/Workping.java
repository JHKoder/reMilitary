package src;

import java.net.*;
import src.*;

public class Workping extends Thread{

 private static Pingthread[] pingthread;
 private static int len;
 private static boolean 
 private boolean[] pingboolean;
 
 private static DatagramSocket[] dsArray;
 private static DatagramPacket[] dpArray;
 
 publc Workping(Pingthread[] pingthread, int len){
  this.pingthread = pingthread;
  this.len        = len;
  
  pingboolean     = new boolean[len];
  dsArray         = new DatagramSocket[len];
  dpArray         = new DatagramPacket[len];
  
 }
 
 public synchronized void postPing(DatagramPacket bp,DatagramSocket socket , int threadnumber){
 try{
 					dsArray[threadnumber]     = socket;
 					dpArray[threadnumber]     = bp;
 					pingboolean[threadnumber] = true;
 
 	     while(true){
 	     	
 	         if(pingAllCheck() == true){
 	             postSend();
 	             break;
 	         }
 	         System.out.println("wait");
 	         yield();
 	         wait(100*len);
 	     
 	     }
 
 }catch(Exception e){}
 
 
 }//postPing -end
 
 public boolean pingAllCheck(){
 
     for(int i=0;i<len;i++){
         if(pingboolean[i] == false){
             return false;
         }
     }
     
     return true;
 }//pingAllCheck -end
 
 public void postSend(){
 
     try{
        for(int i=0;i<len;i++){
            dsArray[i].send(dpArray[i]);
            sleep(10);
        }
        sleep(100*len);
     
     }catch(Exception e){System.out.println(e);}
 
 }//postSend -end 
 
 
}
 
 