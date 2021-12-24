import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[] args)throws Exception{

        //entrer de l'@ ip et du numero de port

        Scanner sc = new Scanner(System.in);

        Socket client = null; //creation du socket client
        client = new Socket("localhost",4990); //connection au serveur.

        PrintWriter out = new PrintWriter(client.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String commande= ""; //contien la commande entré par le client
        String typeReponse = ""; //contien le type de la reponse, longue, erreur, ou QUIT

        System.out.println(in.readLine()); //reception du msg d'acceuill et l'afficher.

        while(!typeReponse.equals("QUIT")){ //condition d'arret: le serveur envoi "QUIT"
            commande = sc.nextLine(); //lecture de la commande.
            out.println(commande); //envoi de la commande au serveur.

            typeReponse = in.readLine(); //le client recoit d'abort le type de la reponse.

            if (typeReponse.equals("longue reponse")) { //si la reponse est longue on appelle la methode ecrireLongueReponse.
                ecrireLongueReponse(in);
            }
            else{ //sinon on ecrit la reponse directement (erreur ou quit)
                System.out.println(typeReponse);
            }
        }
        out.print("QUIT"); // le client envoi QUIT aussi pour fermer la connection des deux cotés.
        System.out.println("la connection est terminé");
        sc.close(); out.close(); in.close();client.close(); // fermeture de tout les objets utilisé.




    }

    public static void ecrireLongueReponse(BufferedReader in) throws Exception{
        String ligne = in.readLine(); //lecture de la premiere ligne
        while (!ligne.equals("fin<")&&!ligne.equals("QUIT")){ //condition d'arret: le serveur envoi "fin<" ou "QUIT"
            System.out.println(ligne); //on affiche la ligne
            ligne = in.readLine(); // on recoit la ligne suivante
        }
    }
}

// List /home/mehdi/Desktop
// Get /home/mehdi/Desktop/text.txt