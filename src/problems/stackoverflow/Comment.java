package problems.stackoverflow;


import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private final Integer id;
    private  String content;
    private final   User author;
    private final   Date createdAt;

    public Comment(String content, User author) {
        this.id =generateId();
        this.content = content;
        this.author = author;
        this.createdAt = new Date(); //UTC time
    }

    private int generateId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }
    private synchronized int editComment(String newContent) {
        if (newContent == null || newContent.isEmpty()) {
            throw new IllegalArgumentException("New content cannot be null or empty");
        }
        this.content = newContent;
        return this.id; // Return the ID of the edited comment
    }

    /*
    necessary if you are not using @Data annotation from Lombok
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
      if (!id.equals(comment.id)) return false;
      if (!content.equals(comment.content)) return false;
      if (!author.equals(comment.author)) return false;
      return createdAt.equals(comment.createdAt);
    }

    /*
   necessary if you are not using @Data annotation from Lombok
    */
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

}
