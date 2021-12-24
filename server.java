import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static int portnum = 4990; //numero de port.

    public static void main(String[] args)throws Exception{

    ServerSocket server = new ServerSocket(portnum); //creation du socket serveur.

    System.out.println("Server listening on port: " + portnum); //pendation.


    while(true){
        Socket client = server.accept();
        traiter(client); //la methode qui traite le client
        client.close(); //on ferme le socket client.
    }


    }
    public static void traiter(Socket client) throws Exception{


            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("bienvenue, envoyer une de ces commandes: 1-List nom_repertoir 2-Get nom_fichier 3-QUIT");

            String commande = in.readLine(); //lecture de la premiere commande.

            while(!commande.equals("QUIT")){ //condition d'arret: le client envoi la commande "QUIT"

                if (commande.startsWith("List")){
                    out.println("longue reponse"); //le serveur envoi le type de la reponse (Longue, erreur, quit)
                    List (commande,out);
                }else if (commande.startsWith("Get")){
                    out.println("longue reponse");//le serveur envoi le type de la reponse (Longue, erreur, quit)
                    Get (commande,out);
                }else {
                    out.println("erreur, commande n'existe pas");
                }
                commande = in.readLine(); // on recoit la commande suivante
            }

            out.println("QUIT"); // le serveur envoi un quit pour fermer la connection des deux cotés
            System.out.println("connection terminé");



    }

    public static void List (String commande,PrintWriter out){
        String nomRep = commande.substring(5); //le repertoire demandé, on prend la partie aprés le mot List seulement. donc on commance du 5eme caractere.
        File rep = new File(nomRep); //on cree un File pour le repertoir.
        if(rep.isDirectory()){ //si le repertoir existe.
            File[] contenue =  rep.listFiles(); //on lis le contnue du repetroire rep. on les stockes dans un tableau a parcourir

            for (int  i = 0; i < contenue.length;  i++){//parcour du tableau
                out.println(contenue[i].getName()); //et on envoi chaque sous ficher/repretoir ligne par ligne.
            }

        }else{ //si le repetoire n'existe pas.
            out.println("erreur, le repertoire n'existe pas");

        }
        out.println("fin<"); // la fin de la transmission de la reponse longue.
    }

    public static void Get (String commande, PrintWriter out)throws Exception{
        String nomfichier = commande.substring(4); //le fichier demandé, on prend la partie aprés le mot Get seulement.
        File fichier = new File(nomfichier); // on cree le fichier.

        if(fichier.isFile()){ //si le fichier existe.
            Scanner fileReader = new Scanner(fichier); //un scanner sur le fichier en question.
            while(fileReader.hasNextLine()){ //envoi du ficher ligne par ligne, tanq'il existe une autre ligne.
                out.println(fileReader.nextLine());
            }

        }else{ // si le ficheir n'existe pas.
            out.println("erreur, le ficheir n'existe pas");
        }

        out.println("fin<");// la fin de la transmission de la reponse longue.
    }
}
