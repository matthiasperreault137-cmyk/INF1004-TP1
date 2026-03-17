

public class GestionRequetes {


    //Attributs


    private static Requete[] requetes = new Requete[1];
    private static int taille = 0;

    //Mutateurs et accesseurs

    public static Requete[] getRequetes(){
        return requetes;
    }
    public static int getTaille(){
        return taille;
    }


    //Méthodes

    private static void tableauAdd(Requete r){
        if(taille == requetes.length){
            Requete[] temp = new Requete[requetes.length + 1];
            for(int i = 0; i < requetes.length; i++){
                temp[i] = requetes[i];
            }
            requetes = temp;
        }
        requetes[taille] = r;
        taille ++;
    }       //Ajoute la requête dans le tableau si assez d'espace sinon agrandit

    public static boolean ajouter(Requete r)  {
        if (!Main.parenthesesValides(r.getTexte()) || chercherIdLineaire(r.getId()) != -1 || r.getPriorite() < 1 || r.getPriorite() > 5){
            return false;
        }
        else{
            tableauAdd(r);
            return true;
        }
    }       //Retourne faux si requête peut pas être ajouté sinon retourne vrai et ajoute requête

    public static int chercherIdLineaire(int id){
        for(int i = 0; i < taille; i++){
            if (requetes[i].getId() == id){
                return i;
            }
        }
        return -1;
    }       //Retourne l'indexe de la requête selon son id si elle n'existe pas retourne -1

    public static Requete get(int i){
        return requetes[i];
    } //Retourne une requête selon son indexe

    public static int nombreRequetes(){
        return taille;
    }       //Retourne le nombre de requêtes acceptées

}
