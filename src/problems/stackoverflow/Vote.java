package problems.stackoverflow;

import lombok.Data;

@Data
public class Vote {
    private final User voter;
   private  final VoteType voteType;

    public Vote(  User voter, VoteType voteType) {
        this.voter = voter;
        this.voteType = voteType;
    }
}
