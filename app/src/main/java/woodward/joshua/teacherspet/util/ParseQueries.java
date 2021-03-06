package woodward.joshua.teacherspet.util;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Joshua on 1/3/2015.
 */
public final class ParseQueries {

    //given the current user, return associated students
    public final static ParseQuery<ParseObject> getAllStudents(ParseUser currentUser){

        ParseQuery<ParseObject> allStudentsQuery=ParseQuery.getQuery(ParseConstants.TABLE_STUDENT);
        //filter to students where teacher id matches
        allStudentsQuery.whereEqualTo(ParseConstants.STUDENT_KEY_TEACHER,currentUser.getObjectId());

        return allStudentsQuery;
    }

    //given the current user, return associated classes
    public final static ParseQuery<ParseObject> getAllClasses(ParseUser currentUser){

        ParseQuery<ParseObject> allClassesQuery=ParseQuery.getQuery(ParseConstants.TABLE_CLASS);
        //filter to students where teacher id matches
        allClassesQuery.whereEqualTo(ParseConstants.CLASS_KEY_TEACHER,currentUser.getObjectId());

        return allClassesQuery;
    }

    //given the current user, return associated evaluations (evaluations that belong to user's classes)
    public final static ParseQuery<ParseObject> getAllEvaluations(ParseUser currentUser){

        ParseQuery<ParseObject> allClassesQuery=ParseQueries.getAllClasses(currentUser);
        ParseQuery<ParseObject> allEvaluationsQuery=ParseQuery.getQuery(ParseConstants.TABLE_EVALUATION);

        //.whereMatchesQuery clause filters the evaluations to those belonging to the current user
        allEvaluationsQuery.whereMatchesQuery(ParseConstants.EVALUATION_KEY_CLASS,allClassesQuery);
        return allEvaluationsQuery;
    }
}
