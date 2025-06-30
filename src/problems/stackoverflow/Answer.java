package problems.stackoverflow;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)// Using Lombok's @Data to generate getters, setters, equals, hashCode, and toString methods
public class Answer extends Entity {
    private final Question question;
    private boolean isAccepted;


    public Answer(String content, User author, Question question) {
        super(content,author); // Call to the superclass constructor
        this.question = question;
        this.isAccepted = false;
    }

    public void markAsAccepted() {
        if (this.isAccepted) {
            throw new IllegalStateException("This answer is already accepted.");
        }
        createdBy.updateReputation(15);
        this.isAccepted = true;
        notifyObservers(this, "Your Answer for Question with Title :"+this.question.getTitle()+" got accepted");
    }


    public int getId() { return id; }
    public User getAuthor() { return createdBy; }
    public Question getQuestion() { return question; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public boolean isAccepted() { return isAccepted; }
}
