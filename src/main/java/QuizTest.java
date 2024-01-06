import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wklej sciezke do pliku pdf/podaj jego nazwe jezeli jest w tym samym folderze:");
        String path = scanner.next();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        "src/main/java/patofizjocz1.pdf"
        if (!path.endsWith(".pdf"))
            path += ".pdf";
        File file = new File(path);
        System.out.println("Wklej sciezke do pliku z odpowiedziami/podaj jego nazwe jezeli jest w tym samym folderze:");
        String answPath = scanner.next();
        if (!answPath.endsWith(".txt"))
            answPath += ".txt";
        try {
            Scanner answw = new Scanner(new File(answPath));
            List<Integer> answ = new ArrayList<>();
            while (answw.hasNextLine())
                answ.add(Integer.valueOf(answw.nextLine()));
            int lastq = 0;
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();
            String[] split = text.split("\n");
            List<Question> questions = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                Question question = new Question();
                StringBuilder sb = new StringBuilder();
                String line = split[i].trim();
                if (line.matches("\\d+\\.\\s+.*")) {
                    sb.append(line).append(" ");
                    int j = 1;
                    while (i + j < split.length) {
                        if (split[i + j].trim().startsWith("a)") || split[i + j].trim().startsWith("b)") || split[i + j].trim().startsWith("c)") || split[i + j].trim().startsWith("d)")) {
                            question.setQuestion(sb.toString());
                            break;
                        }
                        sb.append(split[i + j].trim()).append(" ");
                        j++;
                    }
                    i = i + j;
                    String[] answers = new String[4];
                    j = i;

                    sb = new StringBuilder();
                    sb.append(split[j].trim()).append(" ");
                    j++;
                    while (j < split.length && !(split[j].trim().startsWith("a)") || split[j].trim().startsWith("b)") || split[j].trim().startsWith("c)") || split[j].trim().startsWith("d)"))) {
                        sb.append(split[j].trim()).append(" ");
                        j++;
                    }
                    answers[0] = sb.toString();

                    sb = new StringBuilder();
                    sb.append(split[j].trim()).append(" ");
                    j++;
                    while (j < split.length && !(split[j].trim().startsWith("a)") || split[j].trim().startsWith("b)") || split[j].trim().startsWith("c)") || split[j].trim().startsWith("d)"))) {
                        sb.append(split[j].trim()).append(" ");
                        j++;
                    }
                    answers[1] = sb.toString();

                    sb = new StringBuilder();
                    sb.append(split[j].trim()).append(" ");
                    j++;
                    while (j < split.length && !(split[j].trim().startsWith("a)") || split[j].trim().startsWith("b)") || split[j].trim().startsWith("c)") || split[j].trim().startsWith("d)"))) {
                        sb.append(split[j].trim()).append(" ");
                        j++;
                    }
                    answers[2] = sb.toString();
                    sb = new StringBuilder();
                    sb.append(split[j].trim()).append(" ");
                    j++;
                    while (j < split.length && !split[j].trim().matches("\\d+\\.\\s+.*") && !(split[j].trim().startsWith("a)") || split[j].trim().startsWith("b)") || split[j].trim().startsWith("c)") || split[j].trim().startsWith("d)"))) {
                        sb.append(split[j].trim()).append(" ");
                        j++;
                    }
                    answers[3] = sb.toString();
                    i = j - 1;
                    question.setAnswers(answers);
                    if (lastq <= answ.size())
                        question.setCorrectAnswer(answ.get(lastq) - 1);
                    else
                        question.setCorrectAnswer(1);
                    lastq++;
                    questions.add(question);
                }
            }
            System.out.println("Loaded questions: " + questions.size());
            System.out.println("Loaded answers: " + answ.size() + "\nIf this numbers are different program may not work correctly");
            Thread.sleep(5000);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Test.StartTest(questions, scanner);
        } catch (Exception e) {
            System.out.println("Error during loading the file!\n" + e);
        }
    }
}
