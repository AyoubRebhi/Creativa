package tn.esprit.Models;

public class Comments {
private int id,Post_id,Editeur;
private String Content;
private int Seen;


    public Comments() {
    }

    public Comments(int post_id, int editeur, String content, int seen) {
        Post_id = post_id;
        Editeur = editeur;
        Content = content;
        Seen = seen;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return Post_id;
    }

    public void setPost_id(int post_id) {
        Post_id = post_id;
    }

    public int getEditeur() {
        return Editeur;
    }

    public void setEditeur(int editeur) {
        Editeur = editeur;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getSeen() {
        return Seen;
    }

    public void setSeen(int seen) {
        Seen = seen;
    }



    @Override
    public String toString() {
        return

                ", Content='" + Content + '\'' +


                '}';
    }
}

