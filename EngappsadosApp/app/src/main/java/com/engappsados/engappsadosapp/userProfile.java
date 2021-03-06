package com.engappsados.engappsadosapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

// para fireBase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class userProfile extends AppCompatActivity {
    private static final String TAG = "";
    private ImageView user_Picture;
    private TextView user_Name;
    private TextView user_Points;
    private TextView user_mail;
    private Button btnOut;
    //para base de datps
    private FirebaseUser usuario = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        user_Picture = (ImageView) findViewById(R.id.ImgV_usePicture);
        user_Name = (TextView) findViewById(R.id.nombreDeUsuario);
        user_Points = (TextView) findViewById(R.id.puntosDeUsuario);
        user_mail = (TextView) findViewById(R.id.user_txemail);
        btnOut  = (Button) findViewById(R.id.user_btnSignOut);
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //aca se debe de cerrar sesion
            }
        });


        String uID = usuario.getUid();

        mDatabaseRef.child("usuarios").child(uID).child("Imagen").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imgUrl = dataSnapshot.getValue().toString();
                Picasso.with(userProfile.this).load(imgUrl).transform(new CircleTransform()).into(user_Picture);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });


        mDatabaseRef.child("usuarios").child(uID).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue().toString();
                user_Name.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

        mDatabaseRef.child("usuarios").child(uID).child("Puntos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userPoints = dataSnapshot.getValue().toString();
                user_Points.setText(userPoints);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });


        mDatabaseRef.child("usuarios").child(uID).child("e-mail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue().toString();
                user_mail.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Hey", "Failed to read user name.", error.toException());
            }
        });

    }

}
