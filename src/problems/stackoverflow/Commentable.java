package problems.stackoverflow;

import java.util.List;

public interface Commentable {
    void addComment(Comment comment);
    void removeComment(Comment comment);
    List<Comment> getComments();
    int getCommentCount();
}
