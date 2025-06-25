package problems.stackoverflow;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Question implements Commentable , Votable {
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

    @Override
    public void addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        comments.remove(comment);
    }

    @Override
    public int getCommentCount() {
        return comments.size();
    }

    @Override
    public void vote(User voter, VoteType voteType) {
        if (voter == null) {
            throw new IllegalArgumentException("Voter cannot be null");
        }
        if (voteType == null) {
            throw new IllegalArgumentException("Vote type cannot be null");
        }
        votes.removeIf(vote -> vote.getVoter().equals(voter));
        Vote vote = new Vote(voter, voteType);
        votes.add(vote);
        author.updateReputation(voteType == VoteType.UPVOTE ? 5 : -5);
    }

    @Override
    public int getVoteCount() {
        return votes.stream()
                .mapToInt(vote -> vote.getVoteType() == VoteType.UPVOTE ? 1 : -1)
                .sum();
    }
    // Getters
    public int getId() { return id; }
    public User getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public List<Answer> getAnswers() { return new ArrayList<>(answers); }
    public List<Tag> getTags() { return new ArrayList<>(tags); }
    public Answer getAcceptedAnswer() {return acceptedAnswer;}
}
