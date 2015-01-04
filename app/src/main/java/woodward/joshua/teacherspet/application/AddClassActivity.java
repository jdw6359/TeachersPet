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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;

public class AddClassActivity extends Activity {

    EditText mClassNameField;
    Button mAddClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        mClassNameField=(EditText)findViewById(R.id.classNameText);
        mAddClassButton=(Button)findViewById(R.id.addClassButton);

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
