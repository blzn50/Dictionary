/**
 *
 * @author blaze
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary implements Serializable{
    private String allWord = "";
    private transient HashMap<String, HashSet<String>> hashmap = new HashMap<String, HashSet<String>>();
    private transient Map<String, HashSet<String>> sorted = new TreeMap<String, HashSet<String>>();
    transient File list = new File("src/list.txt");
    private transient Scanner reader;

    public Dictionary() {

    }

    public void addWord(String englishWord, String finnishWord) {
    
        if (hashmap.containsKey(englishWord)) {
            hashmap.get(englishWord).add(finnishWord);
            System.out.println("added: " + englishWord + ", " + hashmap.get(englishWord));
        } else {
            HashSet<String> finnishWords = new HashSet<String>();
            finnishWords.add(finnishWord);
            hashmap.put(englishWord, finnishWords);
            System.out.println("added: " + englishWord + ", " + finnishWords);
        }
    }

    public void deleteWord(String delWord1, String delWord2) {
        if (hashmap.containsKey(delWord1)) {
            hashmap.remove(delWord1, hashmap.get(delWord1).remove(delWord2));
            if (hashmap.get(delWord1).isEmpty()) {
                hashmap.remove(delWord1);
            }
            System.out.println("deleted word: " + delWord1 + ", " + delWord2);
        }
    }

    public ArrayList<String> question() {
        return new ArrayList<String>(hashmap.keySet());
    }

    public boolean giveAns(String ques, String ans) {
        return hashmap.get(ques).contains(ans);
    }

    public String toString() {
        allWord = "";
        Map<String, HashSet<String>> sorted = new TreeMap<String, HashSet<String>>(hashmap);
        for (String englishWord : sorted.keySet()) {
            allWord += (englishWord + ": " + sorted.get(englishWord) + "\n");
        }
        return allWord;
    }

    public void loadFile() {
        String delims = "[,;]+";
        String line;
        try {
            reader = new Scanner(new FileInputStream(list));

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] tokens = line.split(delims);
                String toEnglish = tokens[0];

                for (int i = 0; i < tokens.length - 1; i++) {
                    String toFinnish = tokens[i + 1].trim();
                    addWord(toEnglish, toFinnish);
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.hashmap);
    }
   
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.hashmap = (HashMap) in.readObject();
    }
}
