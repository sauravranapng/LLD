package problems.stackoverflow;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private final int id;
    private final String userName;
    private final String email;
    private int reputation;
    private final List<Question> questions;
    private final List<Answer> answers;
    private final List<Comment> comments;
    private final List<Vote> votes;

    private static final int QUESTION_REPUTATION = 5;
    private static final int ANSWER_REPUTATION = 10;
    private static final int COMMENT_REPUTATION = 2;

    public User(int id, String userName, String email) {
        this.id=id;
        this.userName = userName;
        this.email = email;
        this.reputation = 0;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();

    }
    public Question askQuestion(String title, String content, List<String> tags) {
        Question question = new Question(title, content, this, tags);
        questions.add(question);
        updateReputation(QUESTION_REPUTATION);
        return question;
    }
    public Answer answerQuestion(Question question,String content) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        Answer answer = new Answer(content, this, question);
        question.addAnswer(answer);
        answers.add(answer);
        updateReputation(ANSWER_REPUTATION);
        return answer;
    }
    public void updateReputation(int value) {
        this.reputation += value;
        if (this.reputation < 0) {
            this.reputation = 0;
        }
    }
  public void acceptAnswer(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer cannot be null");
        }
      Question question = answer.getQuestion();
      if (!this.equals(question.getAuthor())) {
          throw new IllegalArgumentException("Only the question author can accept an answer.");
      }
      question.setAcceptedAnswer(answer);
      // Assuming accepting an answer gives reputation
    }
    //Commentable interface reference variable which can old both Question and Answer objects.
    // So we don't need to overload the addComment method for both Question and Answer classes.
    public Comment addComment(Commentable commentable, String content) {
        if (commentable == null) {
            throw new IllegalArgumentException("Commentable cannot be null");
        }
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
        Comment comment = new Comment(content, this);
        commentable.addComment(comment);
        comments.add(comment);
        updateReputation(COMMENT_REPUTATION);
        return comment;
    }
    public void vote(Votable votable, VoteType voteType) {
        if (votable == null) {
            throw new IllegalArgumentException("Entity on which voting is happening cannot be null");
        }
        if (voteType == null) {
            throw new IllegalArgumentException("Vote type cannot be null");
        }
        votable.vote(this, voteType);
    }


}
