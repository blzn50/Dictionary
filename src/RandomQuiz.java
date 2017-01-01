import java.util.*;

public class RandomQuiz extends Quiz {

    private Random rand = new Random();
    private ArrayList<Integer> list;
    private Scanner reader = new Scanner(System.in);
    Set<Integer> generatedNum = new LinkedHashSet<Integer>();
    public int randQues;

    public RandomQuiz(Dictionary dic, int length) {
        super(dic, length);
        this.list = new ArrayList<Integer>(length);
    }

    @Override
    public String doQuiz() {
        randQues = rand.nextInt(questions.size());
        question = questions.get(randQues);
        System.out.println("What is the correct finnish word for '" + question + "'?");

        return question;
    }
}
