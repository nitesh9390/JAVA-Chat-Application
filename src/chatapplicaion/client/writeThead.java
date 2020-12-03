
package chatapplicaion.client;

import java.net.*;
import java.io.*;
import java.util.*;

public class writeThead extends Thread{

    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;
    
    public writeThead(Socket socket, ChatClient client) {
        this.socket=socket;
        this.client=client;
        
        try{
            OutputStream output=socket.getOutputStream();
            writer=new PrintWriter(output,true);
            
        }catch(Exception e)
        {
            System.out.println("error geting outputStream"+e);
        }
    }
    @Override
     public void run()
     {
         Scanner sc=new Scanner(System.in);
         
         System.out.println("\nEnter your name");
         String userName=sc.nextLine();
        // String userName=console.readLine("\n Enter your name");
         client.setUserName(userName);
         writer.println(userName);
         
         String text;
         do{
             //System.out.print("["+userName+"]: ");
             
             text=sc.nextLine();
             writer.println(text);
             
         }while(!text.equalsIgnoreCase("bye"));
         try{
             socket.close();
         }catch(Exception e)
         {
             System.out.println("error writting to server:"+e  );
         }
     }
    
}
