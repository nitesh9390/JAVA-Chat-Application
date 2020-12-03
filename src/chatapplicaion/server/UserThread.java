
package chatapplicaion.server;

import java.io.*;
import java.net.*;
import java.util.*;

class UserThread extends Thread {
    
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    

    UserThread(Socket socket, ChatServer server) {
        this.socket=socket;
        this.server=server;
    
    }
    
    @Override
    public void run()
    {
        try{
            
            InputStream input=socket.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
            
            OutputStream output=socket.getOutputStream();
            writer=new PrintWriter(output,true);
            printUsers();
            
            String userName=reader.readLine();
            server.addUserName(userName);
            String serverMessage="New user connected:"+userName;
            server.boardcast(serverMessage, this);
            
            String clientMessage;
            do{
                clientMessage=reader.readLine();
                serverMessage=userName+" > "+clientMessage;
                server.boardcast(serverMessage, this);
               
            }while(!clientMessage.equalsIgnoreCase("bye"));
            
            server.removeUser(userName, this);
            server.boardcast(serverMessage, this);
                   
            
        
    }catch(Exception e)
    {
        System.out.println("userthread="+e);
    }
    }

    

    void sendMessage(String message) {
       writer.println(message);
       
    }

    //send a list of online user to new user
    private void printUsers() {
       if(server.hasUser())
       {
           writer.println("connected user: "+server.getUserName());
       }
    }

    
    
}
