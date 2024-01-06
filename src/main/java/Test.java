import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Test {
    public static void StartTest(List<Question> questions, Scanner scanner) throws InterruptedException, IOException {
        int i = 1;
        for (Question question : questions) {
            System.out.println("Pytanie nr " + i + ":");
            System.out.println(question.getQuestion());
            System.out.println("Odpowiedzi (a,b,c,d):");
            for (String s : question.getAnswers()) {
                System.out.println(s);
            }
            System.out.println("Wybierz odpowiedź wpisując a, b, c lub d");
            String answer = scanner.next().toLowerCase(Locale.ROOT);
            int answ = -1;
            switch (answer) {
                case "a" -> answ = 0;
                case "b" -> answ = 1;
                case "c" -> answ = 2;
                case "d" -> answ = 3;
            }
            i++;
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            if (answ == question.getCorrectAnswer()) {
                System.out.println("Bardzo dobrze! Poprawna odpowiedź!");
            } else {
                System.out.println("Zła odpowiedź :(");
                System.out.println("Poprawna odpowiedź to: " + (question.getCorrectAnswer() == 0 ? "a" : (question.getCorrectAnswer() == 1 ? "b" : (question.getCorrectAnswer() == 2 ? "c" : "d"))));
            }
            System.out.println("Wcisnij enter aby kontynuować!");
            scanner.nextLine();
            scanner.nextLine();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }
}
