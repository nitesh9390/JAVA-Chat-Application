package chatapplicaion.client;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    private String hostName;
    private int port;
    private String userName;
    
    public ChatClient(String hostName,int port)
    {
        this.hostName=hostName;
        this.port=port;
    }
    
    public void execute()
    {
        try{
            Socket socket=new Socket(hostName,port);
            System.out.println("connected to chatserver");
            new readThead(socket,this).start();
            new writeThead(socket,this).start();
        }catch(Exception e)
        {
            System.out.println("chatclient ="+e);
        }
            
    }
    
    void setUserName(String userName)
    {
        this.userName=userName;
    }
    String getUserName()
    {
        return this.userName;
    }
    
    public static void main(String args[])
    {
        
        String hostName="localhost";
        int port=4545;
                
        
        ChatClient client=new ChatClient(hostName,port);
        client.execute();
    }

    

    
    
}
