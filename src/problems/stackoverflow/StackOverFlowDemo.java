package problems.stackoverflow;

import java.util.Arrays;
import java.util.List;

public class StackOverFlowDemo {
    public static void main( String [] args) {
        StackOverflow system = new StackOverflow();

        // Create users
        User alice = system.createUser("Alice", "alice@example.com");
        User bob = system.createUser("Bob", "bob@example.com");
        User charlie = system.createUser("Charlie", "charlie@example.com");

        // Alice asks a question
        Question javaQuestion = system.askQuestion(alice, "Why we override equals in JAVA in some classes?",
                "I have seen many times that in some classes there is one overridden method called equals. Why it is required ?",
                Arrays.asList("java", "library"));

        // Bob answers Alice's question
        Answer bobAnswer = system.answerQuestion(bob, javaQuestion,
                "By default, the equals() method (inherited from Object) compares object references, not content." +
                        "Even if two objects look the same inside, this will return false because they are two different objects in memory. " +
                        "You should override equals() in your class when you want to define equality based on the content/state of the object, not just its reference.");

        // Charlie comments on the question
        system.addComment(charlie, javaQuestion, "Great question! I'm also interested in learning about this.");

        // Alice comments on Bob's answer
        system.addComment(alice, bobAnswer, "Thanks for the explanation! Could you provide a code example?");

        // Charlie votes on the question and answer
        system.vote(charlie, javaQuestion, VoteType.UPVOTE);  // Upvote
        system.vote(charlie, bobAnswer, VoteType.UPVOTE);  // Upvote

        // Alice accepts Bob's answer
        system.acceptAnswer(alice,bobAnswer);

        // Bob asks another question
        Question pythonQuestion = system.askQuestion(bob, "How to use list comprehensions in Python?",
                "I'm new to Python and I've heard about list comprehensions. Can someone explain how to use them?",
                Arrays.asList("python", "list-comprehension"));

        // Alice answers Bob's question
        Answer aliceAnswer = system.answerQuestion(alice, pythonQuestion,
                "List comprehensions in Python provide a concise way to create lists...");

        // Charlie votes on Bob's question and Alice's answer
        system.vote(charlie, pythonQuestion, VoteType.UPVOTE);  // Upvote
        system.vote(charlie, aliceAnswer, VoteType.UPVOTE);  // Upvote

        // Print out the current state
        System.out.println("Question: " + javaQuestion.getTitle());
        System.out.println("Asked by: " + javaQuestion.getAuthor().getUserName());
        System.out.println("Tags: " + javaQuestion.getTags().stream().map(Tag::getVal).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + javaQuestion.getVoteCount());
        System.out.println("Comments: " + javaQuestion.getComments().size());
        System.out.println("\nAnswer by " + bobAnswer.getAuthor().getUserName() + ":");
        System.out.println(bobAnswer.getContent());
        System.out.println("Votes: " + bobAnswer.getVoteCount());
        System.out.println("Accepted: " + bobAnswer.isAccepted());
        System.out.println("Comments: " + bobAnswer.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Alice: " + alice.getReputation());
        System.out.println("Bob: " + bob.getReputation());
        System.out.println("Charlie: " + charlie.getReputation());

        // Demonstrate search functionality
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = system.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        System.out.println("\nSearch Results for 'python':");
        searchResults = system.searchQuestions("python");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        // Demonstrate getting questions by user
        System.out.println("\nBob's Questions:");
        List<Question> bobQuestions = system.getQuestionsByUser(bob);
        for (Question q : bobQuestions) {
            System.out.println(q.getTitle());
        }
    }
}
