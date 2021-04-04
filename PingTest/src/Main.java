package src;

import src.file.*;
import src.*;

public class Main{

 public static void main(String[] args)  
 						throws Exception{
 				
 				String postIp ="127.0.0.1";
 				//보낼 IP 
 				
 			 ReadText textIp = new ReadText();
 			 String[] ipList = 	textIp.getTextIpList();
 			 int ipLen = ipList.length;
 			 
 			 Pingthread[] pings = new Pingthread[ipLen];
 			
 			 
 			 for(int i=0;i<ipLen;i++){
 			    pings[i] = new Pingthread(ipList[i],i);
 			    pings[i].start();
 			 }
 			 
 			 Workping workping = new Workping(pings,ipLen,postIp);
 			 
 			 for(int i=0;i<ipLen;i++){
 			     pings[i].setWorkping(workping);
 			 }
 	
 						
 	}//main -end  

}//class -end