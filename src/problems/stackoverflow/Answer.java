package problems.stackoverflow;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data // Using Lombok's @Data to generate getters, setters, equals, hashCode, and toString methods
public class Answer implements Votable , Commentable {
    private final int id;
    private final String content;
    private final User author;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(String content, User author, Question question) {
        this.id=generateId();
        this.question = question;
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.content = content;
        this.author = author;
        this.isAccepted = false;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public List<Comment> getComments() {
        /*  By return a new ArrayList<>(comments), we ensure that the original list is not modified */
        return new ArrayList<>(comments);
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
        author.updateReputation(voteType == VoteType.UPVOTE ? 10 : -10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream()
                .mapToInt(vote -> vote.getVoteType() == VoteType.UPVOTE ? 1 : -1)
                .sum();
    }

    public void markAsAccepted() {
        if (this.isAccepted) {
            throw new IllegalStateException("This answer is already accepted.");
        }
        author.updateReputation(15);
        this.isAccepted = true;
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public int getId() { return id; }
    public User getAuthor() { return author; }
    public Question getQuestion() { return question; }
    public String getContent() { return content; }
    public Date getCreationDate() { return creationDate; }
    public boolean isAccepted() { return isAccepted; }
}
