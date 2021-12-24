import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer{
    public static int portnum = 4990; //numero de port.

    public static void main(String[] args)throws Exception{

        ServerSocket server = new ServerSocket(portnum); //creation du socket serveur.

        System.out.println("Server listening on port: " + portnum); //pendation.


        while(true){
            Socket client = server.accept();
            new Thread(new ThreadTask(client)).start();
            System.out.println("le nombre de clients est: "+(Thread.activeCount()-2)); //le nombre de thread total - 2 (les thread tjr actives.
        }


    }

}
