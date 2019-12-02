package com.example.capstoneproject;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase database;
    DatabaseReference root;
    private DatabaseReference mUsers;
    TextView register, clickhere;
    String id, name, users;
    String fireUsername, firePassword, loginUsername, loginPassword, fireEmail, fireContact;
    EditText userName, password;
    Button loginButton;
    CheckBox checkBox;
private  ChildEventListener mChildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.txtRegistration);
        // clickhere = (TextView)findViewById(R.id.enter);
        userName = (EditText)findViewById(R.id.edtUserName);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        password = (EditText)findViewById(R.id.edtPassword);

        //loginPassword = "kamal";
        loginButton = (Button)findViewById(R.id.btnLogin);
        register.setOnClickListener(this);


        database = FirebaseDatabase.getInstance();
        root = database.getReference();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // id = extras.getString("id");
            name = extras.getString("name");


            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println(name);

            // System.out.println(name);
            //The key argument here must match that used in the other activity



        }


        // attachChatMessagesReadListener();


        loginButton.setEnabled(false);
        loginButton.setOnClickListener(this);



    }
    public  void check(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked == true) {
            loginUsername = userName.getText().toString();
            loginPassword = password.getText().toString();
            Log.d("Nav" ," loginuserName" + loginUsername);
            Log.d("Nav" ," loginpassword" + loginPassword);

            mUsers = FirebaseDatabase.getInstance().getReference("users");
            mUsers.child(loginUsername).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    fireUsername = String.valueOf(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    Log.d("Nav", "userName" + fireUsername);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            mUsers.child(loginUsername).child("password").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    firePassword = String.valueOf(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    Log.d("Nav", "password" + firePassword);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            mUsers.child(loginUsername).child("email").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    fireEmail = String.valueOf(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    Log.d("Nav", "password" + fireEmail);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            mUsers.child(loginUsername).child("contact").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    fireContact = String.valueOf(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    Log.d("Nav", "contact" + fireContact);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            Toast.makeText(MainActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
            loginButton.setEnabled(true);
        }
        else{
            loginButton.setEnabled(false);

        }

        //  checkBox.refreshDrawableState();
    }
    public void enter(View view) {
        //firebase
        database = FirebaseDatabase.getInstance();
        root = database.getReference();
        System.out.println("**************************************");

    }
    public void onClick(View view) {
        if (view.getId() == register.getId()) {
            Toast.makeText(this,
                    "Register Clicked",
                    Toast.LENGTH_SHORT).show();

            Intent registerIntent = new Intent
                    (this, registerActivity.class);
            startActivity(registerIntent);
        }
        if(view.getId() == loginButton.getId()){

            if(Objects.equals(loginUsername,fireUsername) && Objects.equals(loginPassword,firePassword)){
                Toast.makeText(MainActivity.this,"login clicked",Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(MainActivity.this,homeActivity.class);
                loginIntent.putExtra("name",loginUsername);
                loginIntent.putExtra("email",fireEmail);
                loginIntent.putExtra("contact",fireContact);
                startActivity(loginIntent);
            }else{
                Toast.makeText(MainActivity.this,"username or password incorrect,Try again!",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

