package com.example.alice.shareflat.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.example.alice.shareflat.R;
import com.example.alice.shareflat.domain.FireBaseHelper;
import com.example.alice.shareflat.domain.FirebaseActionListenerCallBack;
import com.google.firebase.auth.FirebaseAuthException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.txtPassword)
    EditText inputPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.btnSignInUp)
    Button btnSignInUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FireBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        helper = new FireBaseHelper();
    }


    @OnClick(R.id.btnSignInUp)
    public void setBtnSignInUp(){

          helper.createUserWithEmailAndPassword(inputEmail.getText().toString(), inputPassword.getText().toString() , new FirebaseActionListenerCallBack(){

              @Override
              public void onSuccess() {

              }

              @Override
              public void onExeption(FirebaseAuthException exception) {

              }

              @Override
              public void onError(String error) {

              }
          } )  ;

    }


}
