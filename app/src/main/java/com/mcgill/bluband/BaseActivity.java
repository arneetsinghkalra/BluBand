package com.mcgill.bluband;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

public class BaseActivity extends AppCompatActivity {
    com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Drawer navDrawer;
    AccountHeader navHeader;

    public void signOff(){
        openLoginActivity(); //Go back to login page
        FirebaseAuth.getInstance().signOut();
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void openNewChildActivity(){
        Intent intent = new Intent(this, NewChildActivity.class);
        startActivity(intent);
    }

    public void openNursesActivity(){
        Intent intent = new Intent(this, NursesActivity.class);
        startActivity(intent);
    }

    public void openChildGraphActivity(String childId){
        Intent intent = new Intent(this, ChildGraphActivity.class);
        intent.putExtra("CHILD_ID", childId);
        startActivity(intent);
    }

    public void openEditChildActivity(String childNumberId) {
        Intent intent = new Intent(this, EditChildActivity.class);
        intent.putExtra("CHILD_ID", childNumberId);
        startActivity(intent);
    }

    public void reOpenCurrentActivity(){
        navDrawer.closeDrawer();
    }

}
