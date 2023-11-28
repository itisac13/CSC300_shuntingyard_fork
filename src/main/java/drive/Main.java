package drive;

public class Main {
    public static void main(String[] args) {
        ShuntingYard a = new ShuntingYard();
        // a.parse("3 + 4 * (2 - 1)");
        a.parse("(4+3)*5+(6-1)");
        // a.parse("3^2");
        System.out.println(a.Tokens);
        a.process();
        System.out.println("Reversed Polish: " + a.ReversePolish);
        System.out.println("Expressioin result is: " + a.run());
    }
}