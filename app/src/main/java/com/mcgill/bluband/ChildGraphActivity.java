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

        setNavigationDrawer();

        Intent intent = getIntent();
        int childIndex = intent.getIntExtra("CHILD_ID", -1) + 1;
        String childId = "CH" + String.format("%03d", childIndex);

        if (childIndex > -1) {
            final DatabaseReference childDatabase= FirebaseDatabase.getInstance().getReference().child("children").child(childId);

            setTitle(childDatabase);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: create a new activity that displays the kid's info and allow the nurse to change those values.
                    // then connect to the database to change whichever value was changed
                }
            });

        }

    }

    private void setNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getDrawable(R.color.lightBlue));
        ImageView navBtn = (ImageView) findViewById(R.id.navButton);
        navBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                navDrawer.openDrawer();
            }
        });

        navHeader  = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.mipmap.logo)
                .build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem children = new PrimaryDrawerItem().withIdentifier(2).withName("Add a Child");
        PrimaryDrawerItem nurses = new PrimaryDrawerItem().withIdentifier(3).withName("Nurses");
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(4).withName("Logout");

        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(navHeader)
                .withActionBarDrawerToggle(true)
                .withSelectedItem(1)
                .addDrawerItems(
                        home,
                        children,
                        nurses,
                        logout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch(position){
                            //Home Button
                            case 1:
                                openHomeActivity();
                                break;
                            //Children
                            case 2:
                                openNewChildActivity();
                                break;
                            case 3:
                                openNursesActivity();
                                break;
                            //Logout case
                            case 4:
                                signOff();
                                break;
                        }
                        return true;
                    }
                })
                .build();
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
