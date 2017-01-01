import java.util.*;

public class Quiz {

    public Dictionary dic;
    public ArrayList<String> questions;
    private int index;
    public String question;
    public int length;

    public Quiz(Dictionary dic, int length) {
        questions = dic.question();
        index = 0;
        this.length = length;
        this.dic = dic;
    }

    public String doQuiz() {
        question = questions.get(index);
        index++;
        System.out.println("What is the correct finnish word for " + question + "?");
        return question;
    }

    public boolean checkAns(String answer) {
        return dic.giveAns(question, answer);
    }

    public boolean endQuiz() {
        if (index == this.length) {

            return true;
        } else {
            return false;
        }
    }
}
