package tn.esprit;

import tn.esprit.Utils.MaConnexion;
import tn.esprit.services.PostService;
import tn.esprit.services.TopicService;
import tn.esprit.Models.TOPIC;
import tn.esprit.Models.POST;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.List;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println("yes");
       //TopicService test= new TopicService();
      // TOPIC t1= new TOPIC(1,3,"MUSIC","KTYBON");
        //TOPIC t2= new TOPIC(4,"CINEMA","NOUBA");
        //TOPIC t3= new TOPIC(3,"GRAPHIQUE","POWER");
        //TOPIC t4= new TOPIC(3,"MUSIC","GULTRAH");
        //test.modifier(t1);
       //test.ajouter(t2);
       //test.ajouter(t3);
       //test.ajouter(t4);
       //test.supprimer(3);
       //List<TOPIC> res= test.afficher();
       //for( TOPIC topic : res ){
       //    System.out.println(topic.toString());
       //}
        //PostService post=new PostService();

        //POST p1= new POST(2,"BRUNO MARS","THAT4S WHAT I LIKE IT","FISRT ATTEMPT","https://www.youtube.com/watch?v=PMivT7MJ41M&list=RDSR6iYWJxHqs&index=13");
      // post.ajouter(p1);
    }
}