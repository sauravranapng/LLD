package problems.stackoverflow;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends Entity {
    private final String title;
    private final List<Tag> tags;
    private final List<Answer> answers;
    private Answer acceptedAnswer;


    public Question( String title, String content, User author, List<String> tags) {
        super(content, author);
        this.title=title;
        this.tags = tags.stream().map(Tag::new).toList();
        this.answers = new ArrayList<>();
    }

    public synchronized void addAnswer(Answer answer) {
        if (!answers.contains(answer)) {
            answers.add(answer);
            notifyObservers(answer, "New answer added to question: " + title);
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
    // Getters
    public int getId() { return id; }
    public User getAuthor() { return createdBy; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Tag> getTags() { return new ArrayList<>(tags); }
    public Answer getAcceptedAnswer() {return acceptedAnswer;}
}
