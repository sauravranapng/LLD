package problems.stackoverflow;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverflow {
        private final Map<Integer, User> users;
        private final Map<Integer, Question> questions;
        private final Map<Integer, Answer> answers;
        private final Map<String, Tag> tags;

        public StackOverflow() {
            users = new ConcurrentHashMap<>();
            questions = new ConcurrentHashMap<>();
            answers = new ConcurrentHashMap<>();
            tags = new ConcurrentHashMap<>();
        }

        public User createUser(String username, String email) {
            int id = users.size() + 1;
            User user = new User(id, username, email);
            users.put(id, user);
            return user;
        }

        public Question askQuestion(User user, String title, String content, List<String> tags) {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null");
            }
            //we have moved the question creation logic to the User class because it makes more sense there.
            Question question = user.askQuestion(title, content, tags);
            questions.put(question.getId(), question);
            for (Tag tag : question.getTags()) {
                this.tags.putIfAbsent(tag.getVal(), tag);
            }
            return question;
        }

        public Answer answerQuestion(User user, Question question, String content) {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null");
            }
            //we have moved the answer creation logic to the User class because it makes more sense there.
            Answer answer = user.answerQuestion(question, content);
            answers.put(answer.getId(), answer);
            return answer;
        }

        public Comment addComment(User user, Commentable commentable, String content) {return user.addComment(commentable, content);}

        public void vote(User user,Votable votable, VoteType type) {
            user.vote(votable, type);
        }

        public void acceptAnswer(User user, Answer answer) {
        if (user == null) {
            throw new IllegalArgumentException("User and answer cannot be null");
        }
        user.acceptAnswer(answer);
        }

        public List<Question> searchQuestions(String query) {
            return questions.values().stream()
                    .filter(q -> q.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                            q.getContent().toLowerCase().contains(query.toLowerCase()) ||
                            q.getTags().stream().anyMatch(t -> t.getVal().equalsIgnoreCase(query)))
                    .collect(Collectors.toList());
        }

        public List<Question> getQuestionsByUser(User user) {
            return user.getQuestions();
        }

        // Getters
        public User getUser(int id) { return users.get(id); }
        public Question getQuestion(int id) { return questions.get(id); }
        public Answer getAnswer(int id) { return answers.get(id); }
        public Tag getTag(String name) { return tags.get(name); }
    }

