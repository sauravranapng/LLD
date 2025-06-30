package problems.stackoverflow;

import lombok.Data;

import java.util.*;


public abstract class Entity implements Votable , Commentable{
    protected final int id;
    protected String content;
    protected final User createdBy;
    protected final Date creationDate;
    protected final List<Vote> votes ;
    protected final List<Comment> comments ;
    private final List<Observer> observers;

   protected Entity(){
       this.id = generateId();
       this.content = null;
       this.createdBy = null;
       this.creationDate = new Date();
       this.votes = new ArrayList<>();
       this.comments = new ArrayList<>();
       this.observers = new ArrayList<>();
       addObserver(null);
   }
     //visible for subclasses and package-private hence protected
    protected Entity(String content, User createdBy) {
        this.id = generateId();
        this.content = content;
        this.createdBy = createdBy;
        this.creationDate = new Date();
        this.votes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.observers = new ArrayList<>();
        addObserver(createdBy);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void vote(User voter, VoteType voteType) {
        if (voter == null) {
            throw new IllegalArgumentException("Voter cannot be null");
        }
        if (voteType == null) {
            throw new IllegalArgumentException("Vote type cannot be null");
        }
        votes.removeIf(vote -> vote.voter().equals(voter));
        Vote vote = new Vote(voter, voteType);
        votes.add(vote);
        createdBy.updateReputation(voteType == VoteType.UPVOTE ? 10 : -10);
    }

    @Override
    public int getVoteCount() {
        return votes.stream()
                .mapToInt(vote -> vote.voteType() == VoteType.UPVOTE ? 1 : -1)
                .sum();
    }
    @Override
    public void removeComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
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
    public synchronized int editComment(String newContent) {
        if (newContent == null || newContent.isEmpty()) {
            throw new IllegalArgumentException("New content cannot be null or empty");
        }
        this.content = newContent;
        return this.id; // Return the ID of the edited comment
    }
    /*
    Because we can not call subclass method from superclass hence i needed to add this.
     */
    public abstract String getContent();
    public int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Entity entity , String message) {
        for (Observer observer : observers) {
            observer.notify(entity, message);
        }
    }
}
