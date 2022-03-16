package pl.sggw.wzimlibrary.model;

public final class ValidationConstant {
    public final static String NAME_REGEXP = "/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u";
    public final static int FIRST_NAME_MAX_LENGTH = 20;
    public final static int LAST_NAME_MAX_LENGTH = 40;
    public final static int PASSWORD_MIN_LENGTH = 7;
    public final static int PASSWORD_MAX_LENGTH = 30;
    public final static int SECURITY_QUESTION_MAX_LENGTH = 15;
    public final static int SECURITY_QUESTION_ANSWER_MAX_LENGTH = 30;
}
