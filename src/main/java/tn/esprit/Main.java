package tn.esprit;

import com.theokanning.openai.OpenAiResponse;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;

import tn.esprit.Models.Comments;
import tn.esprit.Utils.MaConnexion;
import tn.esprit.services.CommentService;
import tn.esprit.services.PostService;
import tn.esprit.services.TopicService;
import tn.esprit.Models.TOPIC;
import tn.esprit.Models.POST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.List;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println("Connexion avec succés");
       //TopicService test= new TopicService();
       //TOPIC t1= new TOPIC(1,3,"MUSICAAAA","KTYBON");
        //TOPIC t2= new TOPIC(4,"CINEMA","NOUBA");
        //TOPIC t3= new TOPIC(3,"GRAPHIQUE","POWER");
        //TOPIC t4= new TOPIC(3,"MUSIC","GULTRAH");
       // test.modifier(t1);
       //test.ajouter(t2);
       //test.ajouter(t3);
       //test.ajouter(t4);
       //test.supprimer(3);
       //List<TOPIC> res= test.afficher();
       //for( TOPIC topic : res ){
       //    System.out.println(topic.toString());
       //}



       // PostService post=new PostService();
        //POST p1= new POST(2,"BRUNO MARS","THAT4S WHAT I LIKE IT","FISRT ATTEMPT","https://www.youtube.com/watch?v=PMivT7MJ41M&list=RDSR6iYWJxHqs&index=13");
        //POST p2= new POST(3,"LABESS","DANCE ME","SECOND ATTEMPT","https://www.youtube.com/watch?v=PMivT7MJ41M&list=RDSR6iYWJxHqs&index=13");
        //POST p3= new POST(2,"GULTRAH","WIN","THIRD ATTEMPT","https://www.youtube.com/watch?v=PMivT7MJ41M&list=RDSR6iYWJxHqs&index=13");
        //POST p4= new POST(1,"KTYBON","INFRARED","FOURTH ATTEMPT","https://www.youtube.com/watch?v=PMivT7MJ41M&list=RDSR6iYWJxHqs&index=13");

        // post.ajouter(p2);
        // post.ajouter(p3);
        // post.ajouter(p4);
        //post.modifier(p1);
        // post.ajouter(p2);
        //List<POST> list=post.rechercher(2);
        //for( POST p : list ){
        //   System.out.println(p.toString());
        //}
        CommentService com= new CommentService();
        //Comments comment=new Comments(2,4,"test",1);
        //Comments comment1=new Comments(2,2,"test0",0);
        //Comments comm2=new Comments(1,3,"11",1);


    List<Comments> comments=com.Affichernotification(2);

    for(Comments c:comments){
        System.out.println(c.toString());
     }


        // Example prompt for generating text


    }


}



