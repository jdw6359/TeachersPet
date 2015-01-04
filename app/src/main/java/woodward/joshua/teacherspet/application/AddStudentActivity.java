package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;

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
                //get handle on fields
                String firstName=mFirstNameField.getText().toString();
                String lastName=mLastNameField.getText().toString();
                String grade=mGradeField.getText().toString();

                //ensure that first and last name have been provided
                if(firstName.equals("") || lastName.equals("") || grade.equals("")){
                    AlertDialog.Builder incompleteAlertBuilder=new AlertDialog.Builder(AddStudentActivity.this);
                    incompleteAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    incompleteAlertBuilder.setMessage(R.string.missing_fields_alert_message);
                    incompleteAlertBuilder.setPositiveButton(android.R.string.ok,null);
                    AlertDialog incompleteAlert=incompleteAlertBuilder.create();
                    incompleteAlert.show();
                    return;
                }

                ParseObject newStudent=new ParseObject(ParseConstants.TABLE_STUDENT);
                newStudent.put(ParseConstants.STUDENT_KEY_FIRSTNAME,firstName);
                newStudent.put(ParseConstants.STUDENT_KEY_LASTNAME,lastName);
                newStudent.put(ParseConstants.STUDENT_KEY_GRADE,grade);
                newStudent.put(ParseConstants.STUDENT_KEY_TEACHER, ParseUser.getCurrentUser().getObjectId());

                newStudent.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            //successful, navigate user to main activity
                            Intent homeIntent=new Intent(AddStudentActivity.this, MainActivity.class);
                            startActivity(homeIntent);
                        }else{
                            //unsuccessful
                            AlertDialog.Builder dataStoreAlertBuilder=new AlertDialog.Builder(AddStudentActivity.this);
                            dataStoreAlertBuilder.setTitle(R.string.data_store_error_title);
                            dataStoreAlertBuilder.setMessage(R.string.data_store_error_message);
                            dataStoreAlertBuilder.setPositiveButton(android.R.string.ok,null);
                            AlertDialog dataStoreAlert=dataStoreAlertBuilder.create();
                            dataStoreAlert.show();
                        }
                    }
                });
            }
        });

    }
}
