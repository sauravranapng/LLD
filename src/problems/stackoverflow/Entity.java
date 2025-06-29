package problems.stackoverflow;

import java.lang.reflect.Member;
import java.util.*;

abstract class Entity {
    protected final int id;
    protected final User createdBy;
    protected final Date creationDate;
    protected final List<Vote> votes = new ArrayList<>();
    protected final List<Comment> comments = new ArrayList<>();


     //visible for subclasses and package-private hence protected
    protected Entity(User createdBy) {
        this.id = generateId();
        this.createdBy = createdBy;
        this.creationDate = new Date(); // UTC time
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }

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
        createdBy.updateReputation(voteType == VoteType.UPVOTE ? 10 : -10);
    }

    public int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
}
