package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;

public class SignUpActivity extends Activity {

    //declare member variables here
    EditText mNameField;
    EditText mUsernameField;
    EditText mEmailField;
    EditText mPasswordField;
    EditText mPasswordConfirmField;
    Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_sign_up);

        //access + set member variables here
        mNameField=(EditText)findViewById(R.id.nameField);
        mUsernameField=(EditText)findViewById(R.id.usernameField);
        mEmailField=(EditText)findViewById(R.id.emailField);
        mPasswordField=(EditText)findViewById(R.id.passwordField);
        mPasswordConfirmField=(EditText)findViewById(R.id.passwordConfirmField);
        mSignUpButton=(Button)findViewById(R.id.signUpButton);

        //set click listener for sign up button
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get string values from field
                String name=mNameField.getText().toString();
                String username=mUsernameField.getText().toString();
                String email=mEmailField.getText().toString();
                String password=mPasswordField.getText().toString();
                String passwordConfirm=mPasswordConfirmField.getText().toString();

                //check to make sure that all fields are filled
                if(name.equals("") || username.equals("") || email.equals("") || password.equals("") || passwordConfirm.equals("")){
                    AlertDialog.Builder incompleteAlertBuilder=new AlertDialog.Builder(SignUpActivity.this);
                    incompleteAlertBuilder.setTitle(R.string.missing_fields_alert_title);
                    incompleteAlertBuilder.setMessage(R.string.missing_fields_alert_message);
                    incompleteAlertBuilder.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //reset the password and confirm password fields
                            mPasswordField.setText("");
                            mPasswordConfirmField.setText("");
                        }
                    });
                    AlertDialog incompleteAlert=incompleteAlertBuilder.create();
                    incompleteAlert.show();
                }

                //check to make sure that password and confirm password match
                if(!(password.equals(passwordConfirm))){
                    AlertDialog.Builder passwordAlertBuilder=new AlertDialog.Builder(SignUpActivity.this);
                    passwordAlertBuilder.setTitle(R.string.confirm_password_alert_title);
                    passwordAlertBuilder.setMessage(R.string.confirm_password_alert_message);
                    passwordAlertBuilder.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //reset the password and confirm password fields
                            mPasswordField.setText("");
                            mPasswordConfirmField.setText("");
                        }
                    });
                    AlertDialog passwordAlert=passwordAlertBuilder.create();
                    passwordAlert.show();
                }

                setProgressBarIndeterminateVisibility(true);

                //create ParseUser object
                ParseUser newUser=new ParseUser();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setEmail(email);
                newUser.put(ParseConstants.KEY_NAME,name);

                newUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        setProgressBarIndeterminateVisibility(false);

                        if(e==null){
                            //success, send the user to the main activity
                            Intent homeIntent=new Intent(SignUpActivity.this, MainActivity.class);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(homeIntent);
                        }else{
                            //fail, alert the user
                            AlertDialog.Builder signupFailAlertBuilder=new AlertDialog.Builder(SignUpActivity.this);
                            signupFailAlertBuilder.setTitle(R.string.signup_fail_alert_title);
                            signupFailAlertBuilder.setMessage(e.getMessage());
                            signupFailAlertBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //wipe the password and password confirm fields
                                    mPasswordField.setText("");
                                    mPasswordConfirmField.setText("");
                                }
                            });
                            AlertDialog signupFailAlert=signupFailAlertBuilder.create();
                            signupFailAlert.show();
                        }
                    }
                });
            }
        });
    }
}
