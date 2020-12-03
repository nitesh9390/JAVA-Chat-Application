package chatapplicaion.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private int port;
    private Set<String> userNames=new HashSet<>();
    private Set<UserThread> userThread=new HashSet<>();
    
    public ChatServer(int port)
    {
        this.port=port;
    }
    
    public void execute()
    {
        try(ServerSocket serverSocket=new ServerSocket(port))
        {
            System.out.println("system is listenning on port :"+port);
            while(true)
            {
                Socket socket=serverSocket.accept();
                 System.out.println("New user connected...");
                 UserThread newUser=new UserThread(socket,this);
                 userThread.add(newUser);
                 newUser.start();
            }
            
        }catch(Exception e)
        {
            System.out.println("clientserver="+e);
        }
            
    }
    
    public static void main(String args[])
    {
       
        int port=4545; //Integer.parseInt(args[0]);
        ChatServer server=new ChatServer(port);
        server.execute();
    }
    //for boardcasting a message
    void boardcast(String message, UserThread excludeuser)
    {
        for(UserThread auser:userThread)
        {
            if(auser!=excludeuser)
            {
                auser.sendMessage(message);
            }
        }
    }
    //store userName of newly connected clent...
    void addUserName(String userName)
    {
        userNames.add(userName);
    }
    //when a client is diconnected,remove that username and userthread
    
    void removeUser(String userName,UserThread auser)
    {
        boolean remove=userNames.remove(userName);
        if(remove)
        {
            userThread.remove(auser);
            System.out.println("The user "+userName+" quitted");
        }
    }
    
    Set <String> getUserName()
    {
        return this.userNames;
    }
    
    boolean hasUser()
    {
        return !userNames.isEmpty();
    }

    

    
}
