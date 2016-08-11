package com.example.alice.shareflat.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by alice on 8/9/16.
 */
public class FireBaseHelper {
    private static final String TAG = "FireBaseHelper";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private DatabaseReference mDataBase;
    private boolean isSuccess = false;

    public FireBaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

    }


    public void createUserInDB(FirebaseUser user) {
        String userKey = user.getEmail().trim().replace(".", "_");
        mDataBase.child("users").child(userKey).child("email").setValue(user.getEmail());
    }

    public void createUserWithEmailAndPassword(String email, String password, FirebaseActionListenerCallBack firebaseActionListenerCallBack) {
              mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                               @Override
                                               public void onComplete(@NonNull Task<AuthResult> task) {
                                                   Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                                                   // If sign in fails, display a message to the user. If sign in succeeds
                                                   // the auth state listener will be notified and logic to handle the
                                                   // signed in user can be handled in the listener.
                                                   if (!task.isSuccessful()) {
                                                       Log.d(TAG, "createUserWithEmail:no success:");
                                                   } else {
                                                       Log.d(TAG, "createUserWithEmail: is success:");
                                                       FirebaseUser newUser = mAuth.getCurrentUser();
                                                       createUserInDB(newUser);
                                                   }
                                               }
                                           }
                    );
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }


}
