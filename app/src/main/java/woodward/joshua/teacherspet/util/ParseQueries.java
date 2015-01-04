package woodward.joshua.teacherspet.util;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Joshua on 1/3/2015.
 */
public final class ParseQueries {

    //given the current user, return associated students
    public final static ParseQuery<ParseObject> getAllStudent(ParseUser currentUser){

        ParseQuery<ParseObject> allStudentsQuery=ParseQuery.getQuery(ParseConstants.TABLE_STUDENT);
        //filter to students where teacher id matches
        allStudentsQuery.whereEqualTo(ParseConstants.STUDENT_KEY_TEACHER,currentUser.getObjectId());

        return allStudentsQuery;
    }

    



}
