package pl.sggw.wzimlibrary.model.constant;

public enum Gender {
    M("mężczyzna"),
    F("kobieta"),
    U("nieokreślona");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
