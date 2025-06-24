package problems.stackoverflow;


import lombok.Data;

import java.sql.Date;

@Data
public class Comment {
    Integer id;
    String content;
    String author;
    Date createdAt;

    public Comment(Integer id, String content, String author, Date createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
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
