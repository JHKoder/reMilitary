<%@ page contextType ="text/html; charset=euc-kr" %>
<%@ page import="src.Pingthread" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
<head>
  <title> 강정훈의 핑 테스트 <title>
<head>
<body>
<div>
 <%!
     public String listNameget(String fullStr){
       String name ="";
       
       for(int i=0;i<fullStr.length();i++){
         if(fullStr.charAt(i) == ':'){
           break;
         }
         name += fullStr.charAt(i);
       }//for -end
       return name;
     }
     //method - listNameget -end 
 %>
 
 <% 
  BufferedReader fileReader =null;
  
  String[] ipName = new String[50];
  int countLen=0;
  
  try{
    String file = application.getRealPath("\\WEB-INF\\iplist\\ipList.txt");
    fileReader  = new BufferedReader(
                  new InputStreamReader(
                  new FileInputStream(file),"euc-kr")
                  );
    String line =null
    
    while((line=fileReader.readLine()) != null){
      ipNames[countLen++] = listNameget(line);
    }//while -end
    
    
  }catch(FileNotFoundException e1){
    out.println(e1);
  }catch(IOException e2){
    out.println(e2);
  }//try -catch -end
  
  try{
    fileReader.close();
  }catch(Exception e){
    out.println(e);
  }
  //ipList.txt -> (IP)fileNameGet -end 
 %>
 
 <%
  try{
    int defaultPort = 8800;
    boolean bindcheck =false;
    DatagramPacket[] = packets;
    DatagramSocket[] = dsArray;
    
    dsArray = new DatagramSocket[countLen];
    for(int i=0;i<countLen;i++){
      dsArray[i] = new DatagramSocket(defaultPort + i);
    }
    
    byte[][] getBytes = new byte[countLen][100];
    packets = new DatagramPacket[countLen];
    
    for(int i=0;i<countLen;i++){
      packets[i] = new DatagramPacket(getBytes[i],100);
    }
    for(int i=0;i<countLen;i++){
      dsArray[i].receive(packets[i]);
    }
    
    %><table><%
    int ui=0;
    for(int i=0;i<countLen;i++){
      ui = pkToInt(new String(packets[i].getData(),0,100,"UTF-8"));
      
      %><tr><%
      %><td><% out.print(ipNames[i]);%></td>
      <td><%
      if(ui ==0){
        %> <b>●</b> <%
        //검정 (단절)
      }else if(ui <= 50){
        %> <b style="color:#43d241;">●<b><%
        // 녹색 (매우잘됨)
      }else if(ui <=190){
        %> <b style="color:#ff3461;">●<b><%
        // 노랑 (느림)
      }else{
        %> <b style="color:#DD0000;">●<b><%
        //레드 (매우 느림)
      }
      //if -else -end
      %></td><%
      %></tr><%
    }//for -end
        %></table><%
        for(int i=0;i<countLen;i++){
          dsArray[i].close();
          }
        }
  } catch(Exception e){
    out.println(e);
  }//try -catch -end
 //UI -end 
 %>
 <%! 
  public static int pkToInt(String str){
    int len =1;
    for(int i=0;i<100;i++){
      if(str.charAt(i) == '\u0000'){
        len=i;
        break;
      }//for -if -end
    }//for -end
    
    int intNumber =0;
  
    for(int i=0;i<len;i++){
      intNumber += str.charAt(i) -48;
        if(len-1 != i){
          intNumber *= 10;
        }//for -if -end 
    }//for -end
    return intNumber;
  }//pkToInt method -end 
 %>
 </div>
 </body>
 <script LANGUAGE="javascript">
 
 function autoRefresh_div(){
   var currentLocation = window.location;
   setTimeout(location.reload(),10000);
 }// 개발 환경상 div 영역내 새로고침이 안되어 페이지 새로고침으로 함
 setInterval('autoRefresh_div()',10000);
 </script>