public class Requete {

    //Attributs

    private int id;
    private String auteur;
    private int priorite;
    private String texte;
    private int ordreArrivee;
    private static int ordreCounter = 0;

    //Constructeur

    public Requete(int id, String auteur, int priorite, String texte){
        this.id = id;
        this.auteur = auteur;
        this.priorite = priorite;
        this.texte = texte;
        this.ordreArrivee = ordreCounter++;
    }

    //Accesseurs et mutateurs

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setAuteur(String auteur){
        this.auteur = auteur;
    }
    public String getAuteur(){
        return auteur;
    }
    public void setPriorite(int priorite){
        this.priorite = priorite;
    }
    public int getPriorite(){
        return priorite;
    }
    public void setTexte(String texte){
        this.texte = texte;
    }
    public String getTexte(){
        return texte;
    }
    public void setOrdreArrivee(int ordre){
        ordreArrivee = ordre;
    }
    public int getOrdreArrivee(){
        return ordreArrivee;
    }
    public String resume(){
        return "id=" + getId() + ", auteur=" + getAuteur() + ", priorite=" + getPriorite() + ", ordre=" + getOrdreArrivee() + ", texte=" + getTexte();
    }
}
