package problems.stackoverflow;

public class User {
    Integer id;
    String userName;
    String email;
    Integer reputation;

    public User(String userName, String email) {
        this.id=generateId();
        this.userName = userName;
        this.email = email;
        this.reputation = 0;
    }
    public void updateReputation(int value) {
        this.reputation += value;
    }
    private int generateId() {return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);}
}
