package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import woodward.joshua.teacherspet.R;

public class AddStudentActivity extends Activity {

    EditText mFirstNameField;
    EditText mLastNameField;
    EditText mGradeField;
    Button mAddStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //set text fields and buttons
        mFirstNameField=(EditText)findViewById(R.id.firstNameTextField);
        mLastNameField=(EditText)findViewById(R.id.lastNameTextField);
        mGradeField=(EditText)findViewById(R.id.gradeTextField);
        mAddStudentButton=(Button)findViewById(R.id.addStudentButton);

        mAddStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddStudentActivity.this, "Clicked",Toast.LENGTH_LONG).show();
            }
        });

    }
}
