package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class ChildGraphActivity extends BaseActivity {

    TextView childNameTextView;
    String childName;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_graph);

        childNameTextView = (TextView) findViewById(R.id.childNameTextView);
        editBtn = (Button) findViewById(R.id.editChildBtn);
        DatabaseReference childDatabase;

        //Set up button to go back to main dashboard
        setToolbar();

        Intent intent = getIntent();
        final String childId = intent.getStringExtra("CHILD_ID");

        //A valid ID was passed
        if (!childId.isEmpty()) {
            childDatabase= FirebaseDatabase.getInstance().getReference().child("children").child(childId);
            setTitle(childDatabase);


            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openEditChildActivity(childId);
                }
            });
        }

        //Inavlid ID
        else{
            Toast.makeText(ChildGraphActivity.this, "Error: Child info cannot be retrieved at this time." , Toast.LENGTH_SHORT).show();
            openHomeActivity();
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getDrawable(R.color.lightBlue));
        ImageView navBtn = (ImageView) findViewById(R.id.backButton);
        navBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                openHomeActivity();
            }
        });
    }

    private void setTitle(DatabaseReference childDatabase) {
        childDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Child child = dataSnapshot.getValue(Child.class);
                childName = child.getName();
                childNameTextView.setText(childName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
