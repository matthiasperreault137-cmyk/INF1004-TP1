//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        new Requete(204, "Alice", 4, "calc([a+b])");
        new Requete(201, "Bob", 5, "push({x+[y]})");
        new Requete(209, "Lina", 2, "([)]");
        new Requete(206, "Noa", 3, "");
        new Requete(201, "Maya", 1, "(a+b)");
        new Requete(208, "Eve", 5, "{[()]}");
        new Requete(203, "Adam", 2, "a+b)");
        Requete bruh = new Requete(210, "Zoe", 4, "{x+(y*z)}");
        System.out.println(bruh.resume());
    }
}