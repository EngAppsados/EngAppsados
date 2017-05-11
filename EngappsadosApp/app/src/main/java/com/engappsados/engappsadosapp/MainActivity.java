package com.engappsados.engappsadosapp;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

    private LinearLayout Prof_secction;
    private SignInButton SignIn;
    private TextView nombre, correo;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Prof_secction = (LinearLayout)findViewById(R.id.SingIn_panel);
        SignIn = (SignInButton)findViewById(R.id.btn_SignIn);
        nombre = (TextView)findViewById(R.id.txt_name);
        correo = (TextView)findViewById(R.id.txt_mail);
        SignIn.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions.Builder));
    }
    @Override
    public void onClick(View v) {

        if (R.id.btn_SignIn == v.getId()){
            signInMethod();
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void  signInMethod(){
        IntDef intDef = Auth.GoogleSignInApi.getSignInIntent(GoogleApiClient);
        startActivityForResult(intDef,REQ_CODE);
    }
    private void  handleResult(GoogleSignInResult result){

    }
    private void  updateLabels(boolean result){

    }
}
