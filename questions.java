import java.util.Scanner;
import java.io.*;

public class questions {
    private node root;
    private Scanner variable;
    private static final String FILENAME = "JennaBeauchain.ser";
    public int count = 0;
    public questions() throws IOException, ClassNotFoundException {
        variable = new Scanner(System.in);
        File file = new File(FILENAME);
        if (file.exists()) {
            Game();
        } else {
            rootQuestions();
        }
    }
    public void rootQuestions() {
     root = new node("Is it an animal? (yes/no)");
     root.yesToQuestion = new node("Can it fly? (yes/no)");
     root.yesToQuestion.yesToQuestion = new node("Is it a bird? (yes/no)");
     root.yesToQuestion.noToQuestion = new node("Does it have a tail? (yes/no)");
     root.yesToQuestion.noToQuestion.yesToQuestion = new node ("Is it a mouse? (yes/no)");
     root.yesToQuestion.noToQuestion.noToQuestion = new node ("Is it a spider? (yes/no)");
     root.noToQuestion = new node("Does it have wheels? (yes/no)");
     root.noToQuestion.noToQuestion = new node("Is it a bicycle? (yes/no)");
     root.noToQuestion.yesToQuestion = new node ("is it a school item (yes/no)");
     root.noToQuestion.yesToQuestion.yesToQuestion = new node("is it a pencil? (yes/no)");
     root.noToQuestion.yesToQuestion.noToQuestion = new node("Is it a computer? (yes/no)");


    }
    public void treeTransverse(node no) {
        System.out.println("question #" + count);
        if (count == 20) {
            System.out.println("I lose.");
        } else if (no.yesToQuestion == null && no.noToQuestion == null){
            System.out.println(no.words );
            String outPut = variable.nextLine();
            if (outPut.equalsIgnoreCase("yes")) {
                System.out.println("I guessed it!");
            } else if (outPut.equalsIgnoreCase("no")) {
                System.out.println("I lost, what was your answer?");
                String Answer = (variable.nextLine());
                System.out.println("Please enter a question to lead me to your answer");
                String Question = (variable.nextLine());
                System.out.println("What is the answer to " + Question + "? (yes/no)");
                String string = variable.next();
                String data = no.words;
                node da = new node(data);
                node answer = new node(Answer);
                if (string.equalsIgnoreCase("yes")) {
                    no.words = Question;
                    no.yesToQuestion = answer;
                    no.noToQuestion = da;
                } else if (string.equalsIgnoreCase("no")) {
                    no.words = Question;
                    no.yesToQuestion = da;
                    no.noToQuestion = answer;
                }
            }
        }
        else {
            System.out.println("" + no.words + "? (yes/no)");
            String outPut = variable.nextLine();
            if (outPut.equalsIgnoreCase("yes")){
                count  += 1;
                treeTransverse(no.yesToQuestion);
            } else if (outPut.equalsIgnoreCase("no")){
                count += 1;
                treeTransverse(no.noToQuestion);
            }
        }
    }
    private void saveTheGame() throws IOException {
        FileOutputStream file = new FileOutputStream(FILENAME);
        ObjectOutputStream goesOut = new ObjectOutputStream(file);
        goesOut.writeObject(root);
        goesOut.close();
        file.close();
    }
    private void Game() throws IOException, ClassNotFoundException {
        FileInputStream fileGoingIn = new FileInputStream(FILENAME);
        ObjectInputStream fileIn = new ObjectInputStream(fileGoingIn);
        root = (node) fileIn.readObject();
        fileIn.close();
        fileGoingIn.close();
    }
    public void playTheGame() throws IOException {
        System.out.println("Welcome! Input a person, place, or thing and I will guess it!!");
        count += 1;
        treeTransverse(root);
        saveTheGame();

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        questions game = new questions();
        game.playTheGame();
    }


}