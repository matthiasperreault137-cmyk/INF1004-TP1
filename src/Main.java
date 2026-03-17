import java.security.KeyStore;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main {
    public static boolean parenthesesValides(String texte){
        Deque<Character> stack = new ArrayDeque<>();    // Initialisation d'une stack
        for(char c : texte.toCharArray()) {     //Itère à travers chaque charactere du texte
            if (c != ')' && c != ']' && c != '}' && c != '(' && c != '[' && c != '{') {             //Ignore tout ce qui n'est pas une parenthèse
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {         //Ajoute les parenthèses ouvrantes au stack
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()){   //Renvoie faux si aucune parenthèse ouvrante ne peut être trouvé dans le stack et donc parenthèse fermante sans ouverture
                return false;
            }
            else {
                char last = stack.pop();        //Assigne la parenthèse ouvrante la plus récente à last
                if ((c == ')' && last != '(') || (c == ']' && last != '[') || (c == '}' && last != '{')) {        //vérifie si la parenthèse fermante correspond
                    return false;
                }
            }
        }
        return stack.isEmpty(); //Si des parenthèses ouvrantes sont encore présente renvoie faux sinon renvoie vrai
    }       //Vérifie la validité des parenthèses dans le texte de requête

    private static Queue<Requete> construireFile(){
        Queue<Requete> file = new ArrayDeque<>();
        Requete[] array = new Requete[GestionRequetes.getTaille()];
        for(int i = 0; i < GestionRequetes.getTaille(); i++){
            array[i] = GestionRequetes.get(i);
        }
        for(int i = 0; i < array.length - 1; i++){       //Insertion Sort
            int j = i + 1;
            while (j > 0 && array[j].getOrdreArrivee() < array[j - 1].getOrdreArrivee()) {
                Requete temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
        }
        for(int i = 0; i < array.length; i++){ //Ajoute les requêtes dans la file selon leur ordre d'arrivée
            file.add(array[i]);
        }
        return file;
    }       //Ajoute les requêtes dans la file selon leur ordre d'arrivée

    private static Requete traiterSuivante(Queue<Requete> file){
        return file.poll();
    }       //Renvoi les deux premières requetes de la file

    private static void triSelectionParPrioritePuisId(Requete[] t){

        for(int i = 0; i < t.length; i++) {      //Tri par sélection
            int currentMaxOrMinIndex = i;           //Initialise l'index de la requête à échanger
            for (int j = i; j < t.length; j++) {  //Itère à travers le reste du tableau
                int priorite = t[j].getPriorite();  //Priorité de la requête itérée
                int id = t[j].getId();              //Id de la requête itérée
                if (t[currentMaxOrMinIndex].getPriorite() > priorite) {       //Redéfini index de la requête à échanger si priorité plus grande sinon fait rien
                    continue;
                } else if (t[currentMaxOrMinIndex].getPriorite() < priorite) {
                    currentMaxOrMinIndex = j;
                } else {       //Si priorité égale compare les id
                    if (t[currentMaxOrMinIndex].getId() > id) {   //Si id plus petit redéfini requête à échanger
                        currentMaxOrMinIndex = j;
                    } else {       //Sinon fait rien
                        continue;
                    }
                }
            }
            Requete temp = t[i];            //Échange requête à l'index i avec la requête à échanger
            t[i] = t[currentMaxOrMinIndex];
            t[currentMaxOrMinIndex] = temp;
        }


    }       //Trie une copie des requêtes par priorités et puis trie les égalités par id

    private static int[] preparerIdsTries(){
        int[] ids = new int[GestionRequetes.getTaille()];       //Crée un tableau contenant les ids
        for(int i= 0; i < ids.length; i++){         //Copie les valeurs des ids de chaque requête dans le tableau
            ids[i] = GestionRequetes.getRequetes()[i].getId();
        }
        boolean switched = true;
        while (switched) {          //Bubble sort pour trier les valeurs du tableau
            switched = false;
            for(int j = 0; j < ids.length - 1; j++) {
                if (ids[j] > ids[j + 1]) {
                    int temp = ids[j];
                    ids[j] = ids[j + 1];
                    ids[j + 1] = temp;
                    switched = true;
                }
            }
        }
        return ids;     //Renvoie le tableau trié
    }

    private static boolean rechercheBinaireId(int[] idsTries, int id){
        int i = 0;              //Initialise les deux pointeurs
        int j = idsTries.length - 1;
        while (i <= j){             //Boucle jusqu'à ce que les pointeurs se croisent
            int midIndex = (i + j) / 2;     //Initialise l'index du milieu
            if(idsTries[midIndex] < id){            //Si milieu est plus petit, deplace le pointeur de gauche
                i = midIndex + 1;
            }
            else if( idsTries[midIndex] > id){      //Si milieu est plus grand deplace le pointeur de droit
                j = midIndex - 1;
            }
            else{           //Sinon renvoie vrai
                return true;
            }
        }
        return false;   //Si aucune correspondance, renvoie faux
    }

    public static void main(String[] args) {

        //Initialisation des requêtes demandées

        Requete r1 = new Requete(204, "Alice", 4, "calc([a+b])");
        Requete r2 = new Requete(201, "Bob", 5, "push({x+[y]})");
        Requete r3 = new Requete(209, "Lina", 2, "([)]");
        Requete r4 = new Requete(206, "Noa", 3, "");
        Requete r5 = new Requete(201, "Maya", 1, "(a+b)");
        Requete r6 = new Requete(208, "Eve", 5, "{[()]}");
        Requete r7 = new Requete(203, "Adam", 2, "a+b)");
        Requete r8 = new Requete(210, "Zoe", 4, "{x+(y*z)}");
        GestionRequetes.ajouter(r1);
        GestionRequetes.ajouter(r2);
        GestionRequetes.ajouter(r3);
        GestionRequetes.ajouter(r4);
        GestionRequetes.ajouter(r5);
        GestionRequetes.ajouter(r6);
        GestionRequetes.ajouter(r7);
        GestionRequetes.ajouter(r8);
        Requete[] requetesCrees = {r1, r2, r3, r4 ,r5, r6, r7, r8};
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");


        //Tel que demandé, affiche les 8 requêtes créées avec resume()
        for(Requete r : requetesCrees){
            System.out.println(r.resume());
        }
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche les identifiants refusés

        int[] idsTries = preparerIdsTries();

        for(Requete r: requetesCrees){
            if(!rechercheBinaireId(idsTries, r.getId())){
                System.out.println(r.getId());
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche le nombre de requêtes acceptées

        System.out.println(GestionRequetes.getTaille());
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche les deux premières requêtes traitées

        Queue<Requete> file = construireFile(); //Initialisation de la file demandée
        System.out.println(traiterSuivante(file).resume());     //Affichage des deux premières requêtes de la file
        System.out.println(traiterSuivante(file).resume());
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche les requêtes acceptées triées
        Requete[] copieRequetes = new Requete[GestionRequetes.getTaille()];
        for(int i = 0; i < copieRequetes.length; i++){
            copieRequetes[i] = GestionRequetes.getRequetes()[i];
        }
        triSelectionParPrioritePuisId(copieRequetes);
        for(Requete r : copieRequetes){
            System.out.println(r.resume());
        }
        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche le tableau d’identifiants triés
        idsTries = preparerIdsTries();
        System.out.println(Arrays.toString(idsTries));

        System.out.println("----------------------------------------");
        System.out.println("****************************************");
        System.out.println("----------------------------------------");

        //Tel que demandé, affiche le résultat des recherches binaires

        System.out.println(rechercheBinaireId(idsTries, 201));
        System.out.println(rechercheBinaireId(idsTries, 208));
        System.out.println(rechercheBinaireId(idsTries, 999 ));

    }
}