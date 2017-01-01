
/**
 *
 * @author blaze
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DictionaryController {

    Dictionary dic = new Dictionary();
    Scanner reader = new Scanner(System.in);

    public void run() {
        boolean running = true;
        String englishWord = "";
        String finnishWord = "";

        while (running) {
            System.out.println("Type 'add', 'delete', 'print', 'quiz', 'quit' ");
            String input = reader.nextLine();
            System.out.println();

            switch (input) {
                case "add":
                    while (true) {
                        System.out.print("Choose language, '1' for english, '2' for finnish and 'exit' to go back: ");
                        String language = reader.nextLine();

                        if (language.equals("1")) {
                            while (true) {
                                System.out.print("Which word to add?( or 'go back' to go back to upper level): ");
                                englishWord = reader.nextLine().trim().toLowerCase();

                                if (englishWord.equals("") || englishWord.equals("go back")) {
                                    break;
                                } else {

                                    System.out.print("The word in finnish language: ");
                                    finnishWord = reader.nextLine().trim().toLowerCase();

                                    if (finnishWord.equals("") || finnishWord.equals("go back")) {
                                        break;
                                    } else {

                                    }
                                    addWord(englishWord, finnishWord);
                                    writeToFile();
                                }
                            }

                        } else if (language.equals("2")) {
                            while (true) {
                                System.out.print("Which word to add?: ");
                                finnishWord = reader.nextLine().trim().toLowerCase();

                                if (finnishWord.equals("") || finnishWord.equals("go back")) {
                                    break;
                                } else {
                                    System.out.print("The word in english language: ");
                                    englishWord = reader.nextLine().trim().toLowerCase();

                                    if (englishWord.equals("") || englishWord.equals("go back")) {
                                        break;
                                    } else {

                                    }
                                    addWord(englishWord, finnishWord);
                                    writeToFile();
                                }
                            }

                        } else if (language.equals("exit")) {
                            break;
                        } else {

                        }
                    }
                    System.out.println();
                    break;

                case "delete":
                    while (true) {
                        System.out.print("Which english word do you want to delete? : ");
                        String delWord1 = reader.nextLine().trim().toLowerCase();
                        if (delWord1.equals("")) {
                            break;
                        }
                        System.out.print("Type the finnish word: ");
                        String delWord2 = reader.nextLine().trim().toLowerCase();

                        if (delWord2.equals("")) {
                            break;
                        }

                        deleteWord(delWord1, delWord2);
                        System.out.println();
                    }

                    writeToFile();
                    System.out.println();
                    break;

                case "print":
                    System.out.println(loadFromWritten());
                    break;

                case "quiz":
                    System.out.println("The quiz begins. You can choose the length of the quiz:");
                    System.out.print("Input how many questions do you want to answer?: ");
                    int quesLength = Integer.parseInt(reader.nextLine());

                    System.out.println("Which type of quiz you want to do?");
                    System.out.print("'normal' for normal questions and 'random' for random questions: ");
                    String qType = reader.nextLine();

                    if (qType.equals("normal")) {
                        Quiz qz = new Quiz(dic, quesLength);

                        System.out.println("The normal quiz starts now!");
                        int i = 0;
                        int j = 0;

                        while (!qz.endQuiz()) {

                            qz.doQuiz();
                            String ans = reader.nextLine();

                            if (qz.checkAns(ans)) {
                                System.out.println("Right answer!");
                                i++;

                            } else {
                                System.out.println("Wrong answer!");
                                j++;
                            }

                            if (qz.endQuiz()) {
                                break;
                            }
                        }
                        System.out.println("You give correct answer to " + i + " question(s) and wrong answer to " + j + " question(s).");
                        System.out.println();
                        break;

                    } else if (qType.equals("random")) {
                        RandomQuiz rqz = new RandomQuiz(dic, quesLength);
                        System.out.println("Random quiz starts now.");

                        int i = 0;
                        int j = 0;

                        while (rqz.generatedNum.size() < quesLength) {

                            rqz.doQuiz();
                            String ans = reader.nextLine();

                            if (rqz.checkAns(ans)) {
                                System.out.println("Right answer!");
                                i++;

                            } else {
                                System.out.println("Wrong answer!");
                                j++;
                            }

                            rqz.generatedNum.add(rqz.randQues);
                            System.out.println();
                        }
                        System.out.println("You give correct answer to " + i + " question(s) and wrong answer to " + j + " question(s).");
                        System.out.println();
                    } else {

                    }
                    break;

                case "quit":
                    running = false;
                    break;
            }
        }
    }

    private void addWord(String english, String finnishWord) {
        dic.addWord(english, finnishWord);
    }

    private void deleteWord(String delWord1, String delWord2) {
        dic.deleteWord(delWord1, delWord2);
    }

    public void loadFile() {
        dic.loadFile();
    }

    public String loadFromWritten() {
        Dictionary dic2 = new Dictionary();
        String contents = "";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/list2"));
            dic2 = (Dictionary) in.readObject();
            contents = dic2.toString();
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(DictionaryController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DictionaryController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return contents;
    }

    public void writeToFile() {

        try {
            FileOutputStream out = new FileOutputStream("src/list2");
            ObjectOutputStream oout = new ObjectOutputStream(out);

            oout.writeObject(dic);
            oout.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
