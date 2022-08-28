import model.SentenceGenerator;

public class Main {

    public static void main(String[] args) {
//        try {
//            new SetUp();
//        } catch (Error e) {
//            System.out.println("Unable to run application: file not found!");
//        }
        SentenceGenerator s = new SentenceGenerator();

        try {
            s.getRandomSentences();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

    }
}
