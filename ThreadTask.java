import java.net.Socket;

public class ThreadTask implements Runnable{
    public Socket client=null;
    ThreadTask(Socket client){
        this.client=client;
    }

    @Override
    public void run(){
        try{
            server.traiter(client); //on fait appelle au traitemend du serveur normal.
        }catch (Exception e){
            e.printStackTrace(); //traitement des exceptions.
        }
        finally {
            System.out.println("le nombre de clients est: "+(Thread.activeCount()-2-1));//le nombre de client -1, car celui ci vas quitter.
            return; //pour fermer le thread quand tout est fini.
        }
    }
}
