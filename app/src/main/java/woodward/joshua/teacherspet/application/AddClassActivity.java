package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import woodward.joshua.teacherspet.R;

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

                Toast.makeText(AddClassActivity.this,"adding class record",Toast.LENGTH_LONG).show();

            }
        });


    }

}
