package problems.stackoverflow;

import lombok.Data;



@Data
public class Tag {
    Integer id;
    String val;

    public Tag( String val) {
        this.id =generateId();
        this.val = val;
    }
    public Integer generateId(){
        return (int)(System.currentTimeMillis()%Integer.MAX_VALUE);
    }

}
