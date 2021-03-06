package woodward.joshua.teacherspet.util;

/**
 * Created by Joshua on 12/29/2014.
 */
public final class ParseConstants {

    public final static String USER_KEY_NAME="name";

    public final static String TABLE_STUDENT="Student";
    public final static String STUDENT_KEY_FIRSTNAME="firstName";
    public final static String STUDENT_KEY_LASTNAME="lastName";
    public final static String STUDENT_KEY_GRADE="grade";
    public final static String STUDENT_KEY_TEACHER="teacherId";

    public final static String TABLE_CLASS="Class";
    public final static String CLASS_KEY_NAME="name";
    public final static String CLASS_KEY_TEACHER="teacherId";
    public final static String CLASS_RELATION_STUDENTS="students";

    public final static String TABLE_EVALUATION="Evaluation";
    public final static String EVALUATION_KEY_NAME="name";
    public final static String EVALUATION_KEY_CLASS="class";
    public final static String EVALUATION_KEY_COMPLETED="completed";



}
