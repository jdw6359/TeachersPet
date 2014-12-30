package woodward.joshua.teacherspet.application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
