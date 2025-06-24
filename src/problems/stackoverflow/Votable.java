package problems.stackoverflow;

public interface Votable {
    /*
    Why do we not specify the access specifier inside the interface?
    Apart from static and default methods, all methods in an interface are public by default.
    But from java 9 onward  we can have private methods in an interface as helper methods for static and default methods.
     */
    void vote(User voter, VoteType voteType);
    int getVoteCount();
}
