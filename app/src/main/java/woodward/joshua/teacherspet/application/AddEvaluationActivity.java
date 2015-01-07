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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;
import woodward.joshua.teacherspet.util.ParseQueries;

public class AddEvaluationActivity extends ListActivity {

    public static final String TAG=AddEvaluationActivity.class.getSimpleName();

    EditText mEvaluationNameEditText;
    Button mAddEvaluationButton;
    ListView mClassList;
    List<ParseObject> mClasses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);

        mEvaluationNameEditText=(EditText)findViewById(R.id.evaluationNameTextField);
        mClassList=getListView();
        mAddEvaluationButton=(Button)findViewById(R.id.addEvaluationButton);

        //get parse query from ParseQueries
        ParseQuery<ParseObject> allClassesQueries =ParseQueries.getAllClasses(ParseUser.getCurrentUser());
        allClassesQueries.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> classes, ParseException e) {
                if(e==null){
                    //success
                    //set member variable of classes
                    mClasses=classes;
                    //create list of strings for the class names that will be adapted
                    String[] classNamesList=new String[mClasses.size()];
                    //loop through mClasses and append to mClassNamesList
                    for(int i=0;i<mClasses.size();i++){
                        //take classname from parse object and add to classNamesList
                        String className=mClasses.get(i).get(ParseConstants.CLASS_KEY_NAME).toString();
                        classNamesList[i]=className;
                    }

                    //create new array adapter
                    ArrayAdapter<String> classNamesAdapter=new ArrayAdapter<String>(
                            AddEvaluationActivity.this,android.R.layout.simple_list_item_checked,classNamesList
                    );

                    //set the adapter as the adapter for the list
                    setListAdapter(classNamesAdapter);

                    //set choice mode to single
                    mClassList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                }else{
                    //query fail, log to user
                    Log.d(TAG, e.getMessage());
                }
            }
        });

        //add click listener for add evaluation button
        mAddEvaluationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String evaluationName=mEvaluationNameEditText.getText().toString();
                //ensure that evaluation name is not empty
                if(evaluationName.equals("")){
                    AlertDialog.Builder emptyEvaluationNameAlertBuilder=new AlertDialog.Builder(AddEvaluationActivity.this);
                    emptyEvaluationNameAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    emptyEvaluationNameAlertBuilder.setMessage(R.string.empty_evaluation_name_field);
                    emptyEvaluationNameAlertBuilder.setPositiveButton(android.R.string.ok,null);
                    AlertDialog emptyEvaluationNameAlert=emptyEvaluationNameAlertBuilder.create();
                    emptyEvaluationNameAlert.show();
                    return;
                }

                ParseObject evaluationClass=null;
                //if the list is checked at a certain location, set selectedClass to parse object
                for(int i=0;i<mClasses.size();i++){
                    if(mClassList.isItemChecked(i)){
                        evaluationClass=mClasses.get(i);
                    }
                }

                //if there is no selected class (selectedClass=null), alert the user
                if(evaluationClass==null){
                    AlertDialog.Builder emptyClassSelectAlertBuilder=new AlertDialog.Builder(AddEvaluationActivity.this);
                    emptyClassSelectAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    emptyClassSelectAlertBuilder.setMessage(R.string.empty_evaluation_class_select);
                    emptyClassSelectAlertBuilder.setPositiveButton(android.R.string.ok,null);
                    AlertDialog emptyEvaluationNameAlert=emptyClassSelectAlertBuilder.create();
                    emptyEvaluationNameAlert.show();
                    return;
                }

                //create parse object
                ParseObject newEvaluation=new ParseObject(ParseConstants.TABLE_EVALUATION);
                //add fields to evaluation parse object
                newEvaluation.put(ParseConstants.EVALUATION_KEY_NAME,evaluationName);
                newEvaluation.put(ParseConstants.EVALUATION_KEY_CLASS,evaluationClass);
                //hardcode this value for now
                newEvaluation.put(ParseConstants.EVALUATION_KEY_COMPLETED,true);

                //attempt to save the evaluation object
                newEvaluation.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            //bring the user to the main activity
                            Intent homeIntent=new Intent(AddEvaluationActivity.this,MainActivity.class);
                            startActivity(homeIntent);
                        }else{
                            //fail, alert user
                            AlertDialog.Builder dataStoreAlertBuilder=new AlertDialog.Builder(AddEvaluationActivity.this);
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
