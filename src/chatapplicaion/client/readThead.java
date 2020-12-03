
package chatapplicaion.client;

import java.io.*;
import java.net.*;
import java.util.*;


public class readThead extends Thread {
    
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public readThead(Socket socket, ChatClient client) {
        this.socket=socket;
        this.client=client;
        
        try
        {
            InputStream input=socket.getInputStream();
            reader=new BufferedReader(new InputStreamReader(input));
        }catch(Exception e)
        {
            System.out.println("error getting inputStream"+e);
        }
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try{
                String respone=reader.readLine();
                System.out.println(respone);
                
                if(client.getUserName()!=null)
                {
                    System.out.print("["+client.getUserName()+"]: ");
                }
                
            }catch(Exception e)
            {
                System.out.println("error reading from server"+e);
                break;
            }
        }
    }
    
}
