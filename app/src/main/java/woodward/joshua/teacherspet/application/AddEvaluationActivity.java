package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import woodward.joshua.teacherspet.R;

public class AddEvaluationActivity extends ListActivity {

    EditText mEvaluationNameEditText;
    Button mAddEvaluationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluation);

        mEvaluationNameEditText=(EditText)findViewById(R.id.evaluationNameTextField);
        mAddEvaluationButton=(Button)findViewById(R.id.addEvaluationButton);

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
