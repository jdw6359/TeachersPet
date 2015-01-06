package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
                //grab values

                //create parse object

                //save parse object in the background

                //on success bring user to main activity
            }
        });


    }
}
