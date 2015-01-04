package woodward.joshua.teacherspet.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;
import woodward.joshua.teacherspet.util.ParseQueries;

/**
 * Created by Joshua on 12/29/2014.
 */
public class StudentsFragment extends ListFragment {

    //Tag variable
    public final static String TAG="Students Fragment";

    //fragment member variables
    Button mAddNewStudentButton;
    List<ParseObject> mStudents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_students, container, false);

        //get and set click listener for add new student button
        mAddNewStudentButton=(Button)rootView.findViewById(R.id.add_new_student_button);
        mAddNewStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigate user to add student activity
                Intent addStudentIntent=new Intent(rootView.getContext(),AddStudentActivity.class);
                startActivity(addStudentIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        //get query from static query class
        ParseQuery<ParseObject> allStudentsQuery= ParseQueries.getAllStudents(ParseUser.getCurrentUser());
        allStudentsQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> students, ParseException e) {
                if(e==null){
                    //successful query, update list
                    mStudents=students;

                    String[] studentsList=new String[mStudents.size()];
                    for(int i=0;i<mStudents.size();i++){
                        //get the first and last name of the students from mStudents and append to studentsList string array
                        String studentFirstName=mStudents.get(i).get(ParseConstants.STUDENT_KEY_FIRSTNAME).toString();
                        String studentLastName=mStudents.get(i).get(ParseConstants.STUDENT_KEY_LASTNAME).toString();
                        studentsList[i]=studentLastName + ", " + studentFirstName;
                    }

                    ArrayAdapter<String> studentAdapter=new ArrayAdapter<String>(getListView().getContext(),
                            android.R.layout.simple_list_item_1,studentsList);
                    setListAdapter(studentAdapter);

                }else{
                    //unsuccessful query, log to user
                    Log.d(TAG,e.getMessage());
                }
            }
        });
    }
}