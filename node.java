import java.io.Serializable;

class node implements Serializable {
    String words;
    node yesToQuestion;
    node noToQuestion;

    public node(String words){
        this.words = words;
        this.yesToQuestion = yesToQuestion;
        this.noToQuestion = noToQuestion;
    }


}
