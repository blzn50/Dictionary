public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DictionaryController dc = new DictionaryController();

        dc.loadFile();
        dc.run();
    }
}
