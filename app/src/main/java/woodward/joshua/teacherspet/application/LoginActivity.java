package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import woodward.joshua.teacherspet.R;

public class LoginActivity extends Activity {

    //set member variables here
    EditText mUsernameField;
    EditText mPasswordField;
    Button mSignInButton;
    Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //access + set ui elements here
        mUsernameField=(EditText)findViewById(R.id.usernameField);
        mPasswordField=(EditText)findViewById(R.id.passwordField);
        mSignInButton=(Button)findViewById(R.id.signInButton);
        mSignUpButton=(Button)findViewById(R.id.signUpButton);

        //signin button listener
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //grab username and password values
                String username=mUsernameField.getText().toString();
                String password=mPasswordField.getText().toString();
                //if either are empty, alert user
                if(username.equals("") || password.equals("")){
                    AlertDialog.Builder incompleteAlertBuilder=new AlertDialog.Builder(LoginActivity.this);
                    incompleteAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    incompleteAlertBuilder.setMessage(R.string.missing_fields_alert_message);
                    incompleteAlertBuilder.setPositiveButton(android.R.string.ok,null);
                    AlertDialog incompleteAlert=incompleteAlertBuilder.create();
                    incompleteAlert.show();
                    return;
                }

                //attempt to sign in user
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        if(parseUser!=null){
                            //sign in was successful, bring user to home page
                            Intent homeIntent=new Intent(LoginActivity.this,MainActivity.class);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(homeIntent);
                        }else{
                            //sign in was not successful
                            AlertDialog.Builder failLoginAlertBuilder=new AlertDialog.Builder(LoginActivity.this);
                            failLoginAlertBuilder.setTitle(R.string.login_fail_alert_title);
                            failLoginAlertBuilder.setMessage(e.getMessage());
                            failLoginAlertBuilder.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //wipe out the password field
                                    mPasswordField.setText("");
                                }
                            });
                            AlertDialog failLoginAlert=failLoginAlertBuilder.create();
                            failLoginAlert.show();
                        }
                    }
                });
            }
        });

        //when "sign up" clicked, bring user to sign up activity
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }
}
