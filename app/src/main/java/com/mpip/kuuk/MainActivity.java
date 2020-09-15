package com.mpip.kuuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        final UserInfo user = mAuth.getCurrentUser();
        Handler h = new Handler();
        final Context c = this.getApplicationContext();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user != null) {
                    Intent homeIntent = new Intent(c, HomeActivity.class);
                    homeIntent.putExtra("userID",user.getUid());
                    startActivity(homeIntent);
                } else {
                    Intent signIntent = new Intent(c, LoginActivity.class);
                    startActivity(signIntent);
                }
            }
        }, 3000);
    }
}