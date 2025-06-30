package problems.stackoverflow;


import lombok.Data;


@Data
public class Comment extends Entity{

    public Comment(String content, User author) {
     super(content,author);
    }

    /*
    necessary if you are not using @Data annotation from Lombok
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
      if (id!=comment.id) return false;
      if (!content.equals(comment.content)) return false;
      if (!createdBy.equals(comment.createdBy)) return false;
      return creationDate.equals(comment.creationDate);
    }

    /*
   necessary if you are not using @Data annotation from Lombok
    */
    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + content.hashCode();
        result = 31 * result + createdBy.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }
    public String getContent() { return content; }

}
