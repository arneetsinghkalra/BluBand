package com.mcgill.bluband;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

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

import java.util.ArrayList;

public class NursesActivity extends BaseActivity {
    private ListView listView;
    private DatabaseReference myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurses);

        //**********************Create Nav Bar with custom Tool Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getDrawable(R.color.lightBlue));
        ImageView navBtn = (ImageView) findViewById(R.id.navButton);
        navBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                navDrawer.openDrawer();
            }
        });

        navHeader = new AccountHeaderBuilder()
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
                .withSelectedItem(3)
                .addDrawerItems(
                        home,
                        children,
                        nurses,
                        logout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {
                            //Home Button
                            case 1:
                                openHomeActivity();
                                break;
                            //Children
                            case 2:
                                openNewChildActivity();
                                break;
                            case 3:
                                reOpenCurrentActivity();
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

        //End of navbar code **************************************

        //Firebase database -------------------
        listView = (ListView) findViewById(R.id.nursesList);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);
        listView.setAdapter(adapter);

        myDatabase = FirebaseDatabase.getInstance().getReference().child("nurses");
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Nurse aNurse = snapshot.getValue(Nurse.class);
                    String txt = "Name: "+aNurse.getName()+"\nEmail: "+aNurse.getEmail()+"\nPhone: "+aNurse.getPhone() ;
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
