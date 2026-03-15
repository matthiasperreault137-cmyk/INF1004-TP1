import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main {
    public static boolean parenthesesValides(String texte){
        Deque<Character> stack = new ArrayDeque<>();    // Initialisation d'une stack
        for(char c : texte.toCharArray()) {     //Itère à travers chaque charactère du texte
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
    }

    public static void main(String[] args) {
        GestionRequetes.ajouter(new Requete(204, "Alice", 4, "calc([a+b])"));
        GestionRequetes.ajouter(new Requete(201, "Bob", 5, "push({x+[y]})"));
        GestionRequetes.ajouter(new Requete(209, "Lina", 2, "([)]"));
        GestionRequetes.ajouter(new Requete(206, "Noa", 3, ""));
        GestionRequetes.ajouter(new Requete(201, "Maya", 1, "(a+b)"));
        GestionRequetes.ajouter(new Requete(208, "Eve", 5, "{[()]}"));
        GestionRequetes.ajouter(new Requete(203, "Adam", 2, "a+b)"));
        GestionRequetes.ajouter(new Requete(210, "Zoe", 4, "{x+(y*z)}"));

        Queue<Requete> file = construireFile();
        System.out.println(traiterSuivante(file).resume());
        System.out.println(traiterSuivante(file).resume());
    }
}