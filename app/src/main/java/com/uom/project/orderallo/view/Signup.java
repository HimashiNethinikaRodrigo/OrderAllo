package com.uom.project.orderallo.view;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.entity.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_signup)
public class Signup extends AppCompatActivity {

    @ViewById
    EditText edtEmail;
    @ViewById
    EditText edtPassword;
    @ViewById
    Button btnSignup;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    @AfterViews
    void initiateView(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null)
                    Toast.makeText(Signup.this, "Add new user " + user.getUserName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        progressDialog = new ProgressDialog(this);


    }

    @Click
    void btnSignup(View view){
        progressDialog.show();
        if(!edtEmail.getText().toString().isEmpty() && !edtPassword.getText().toString().isEmpty()){
            registerUser();
        }else{
            Toast.makeText(Signup.this, "Fill All the Fields", Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }

    }

    private void addDataToDatabase() {
        User user = new User();
        user.setUserName(edtEmail.getText().toString());
        user.setPassword(edtPassword.getText().toString());

        databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "User registration successful DB", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(Signup.this, "User registration fail DB", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("ERROR ADD DB" + e);
            }
        });
    }

    private void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "User registration successful", Toast.LENGTH_SHORT).show();
                    addDataToDatabase();
                }
                else {
                    edtPassword.setText("");
                    edtEmail.setText("");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("ERROR " + e);
                if (e.getMessage().contains("WEAK_PASSWORD")){
                    Toast.makeText(Signup.this, "User registration fail. Password is not strong.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else if(e.getMessage().contains("The email address is badly formatted")){
                    Toast.makeText(Signup.this, "User registration fail. Email is not valid.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else if(e.getMessage().contains("The email address is already in use by another account")){
                    Toast.makeText(Signup.this, "User registration fail. Email is not valid.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
