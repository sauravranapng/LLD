package problems.stackoverflow;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String userName;
    private final String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;

    public User(int id, String userName, String email) {
        this.id=id;
        this.userName = userName;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    public void updateReputation(int value) {
        this.reputation += value;
    }
    private int generateId() {return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);}

    public Comment addComment(Commentable commentable, String content) {
    }
}
