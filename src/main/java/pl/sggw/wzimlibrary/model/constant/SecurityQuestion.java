package pl.sggw.wzimlibrary.model.constant;

public enum SecurityQuestion {
    ANIMAL("Jakie jest Twoje ulubione zwierzę?"),
    FRIENDS_NAME("Jakie jest imię Twojego przyjaciela z dzieciństwa?"),
    SANDWICH("Z czym najbardziej lubisz kanapki?");

    private final String question;

    SecurityQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
