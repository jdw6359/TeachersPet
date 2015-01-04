package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;
import woodward.joshua.teacherspet.util.ParseQueries;

public class AddClassActivity extends ListActivity {

    public static final String TAG=AddClassActivity.class.getSimpleName();

    List<ParseObject> mStudents;
    EditText mClassNameField;
    Button mAddClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        mClassNameField=(EditText)findViewById(R.id.classNameText);
        mAddClassButton=(Button)findViewById(R.id.addClassButton);

        //get list of students belonging to teacher, create check list
        ParseQuery<ParseObject> allStudents= ParseQueries.getAllStudents(ParseUser.getCurrentUser());
        allStudents.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> students, ParseException e) {
                if(e==null){
                    //successful
                    mStudents=students;
                    String[] studentNames=new String[mStudents.size()];
                    for(int i=0;i<mStudents.size();i++){
                        String studentFirstName=mStudents.get(i).get(ParseConstants.STUDENT_KEY_FIRSTNAME).toString();
                        String studentLastName=mStudents.get(i).get(ParseConstants.STUDENT_KEY_LASTNAME).toString();
                        studentNames[i]=studentLastName + ", " + studentFirstName;
                    }

                    ArrayAdapter<String> studentNamesAdapter=new ArrayAdapter<String>(
                            AddClassActivity.this, android.R.layout.simple_list_item_checked,studentNames);
                    setListAdapter(studentNamesAdapter);
                    getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                }else{
                    //unsuccessful
                    Log.d(TAG, e.getMessage());
                }
            }
        });

        //click listener for clicking on items in the list
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //check the item
                getListView().setItemChecked(position,true);
            }
        });


        //click listener for add class button
        mAddClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ensure that class name field has value
                String className=mClassNameField.getText().toString();
                if(className.equals("")){
                    AlertDialog.Builder incompleteAlertBuilder=new AlertDialog.Builder(AddClassActivity.this);
                    incompleteAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    incompleteAlertBuilder.setMessage(R.string.missing_fields_alert_message);
                    incompleteAlertBuilder.setPositiveButton(android.R.string.ok,null);
                    AlertDialog incompleteAlert=incompleteAlertBuilder.create();
                    incompleteAlert.show();
                    return;
                }

                //create parse object
                ParseObject newClass=new ParseObject(ParseConstants.TABLE_CLASS);
                newClass.put(ParseConstants.CLASS_KEY_NAME,className);
                newClass.put(ParseConstants.CLASS_KEY_TEACHER, ParseUser.getCurrentUser().getObjectId());

                //for selected students, add to class via parse relationship
                ParseRelation<ParseObject> classStudentRelation=newClass.getRelation(ParseConstants.CLASS_RELATION_STUDENTS);
                for(int i=0;i<mStudents.size();i++){
                    if(getListView().isItemChecked(i)){
                        //add the student to the relation
                        classStudentRelation.add(mStudents.get(i));
                    }
                }

                //save parse object in backend, navigate to main activity when complete
                newClass.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            //successful, navigate user to main activity
                            Intent homeIntent=new Intent(AddClassActivity.this,MainActivity.class);
                            startActivity(homeIntent);

                        }else{
                            //unsuccessful
                            AlertDialog.Builder dataStoreAlertBuilder=new AlertDialog.Builder(AddClassActivity.this);
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
