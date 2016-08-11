package com.example.alice.shareflat.domain;

import com.google.firebase.auth.FirebaseAuthException;

/**
 * Created by alice on 8/9/16.
 */
public interface FirebaseActionListenerCallBack {

    void onSuccess();
    void onExeption(FirebaseAuthException  exception);
    void onError(String  error);
}
