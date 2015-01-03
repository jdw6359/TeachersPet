package woodward.joshua.teacherspet.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseQueries;

/**
 * Created by Joshua on 12/29/2014.
 */
public class StudentsFragment extends ListFragment {

    Button mAddNewStudentButton;

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

        ParseQuery<ParseObject> allStudentsQuery= ParseQueries.getAllStudent(ParseUser.getCurrentUser());

    }
}