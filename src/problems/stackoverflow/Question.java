package problems.stackoverflow;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Question {
    private final Integer id;
    private final String title;
    private final String content;
    private final User author;
    //private final Integer viewCount;
    private final List<Comment> comments;
    private final List<Tag> tags;
    private final List<Answer> answers;
    private final List<Vote>votes;
    private final Date creationDate;
    private Answer acceptedAnswer;


    public Question( String title, String content, User author, List<String> tags) {
        this.id = generateId();
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags.stream().map(Tag::new).toList();
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.votes = new ArrayList<>();

    }
    public synchronized void addAnswer(Answer answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
        }
    }
    public synchronized void setAcceptedAnswer(Answer answer) {
        if (answers.contains(answer)) {
            this.acceptedAnswer = answer;
            answer.markAsAccepted();
        } else {
            throw new IllegalArgumentException("Answer not found in the question's answers.");
        }
    }
    private Integer generateId() {
        // Generate a unique ID based on the current time in milliseconds
        return (int) (System.currentTimeMillis()%Integer.MAX_VALUE);
    }

}
