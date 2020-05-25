package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
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

public class HomeActivity extends BaseActivity {
    private ListView listView;
    private DatabaseReference myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        navHeader  = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.mipmap.logo)
                .build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withName("Home");
        PrimaryDrawerItem children = new PrimaryDrawerItem().withIdentifier(2).withName("Children");
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(2).withName("Logout");

        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(navHeader)
                .withActionBarDrawerToggle(true)
                .withSelectedItem(1)
                .addDrawerItems(
                        home,
                        children,
                        logout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch(position){
                            //Home Button
                            case 1:
                                reOpenCurrentActivity();
                                break;
                            case 2:
                                openNewUserActivity();
                                break;
                            //Logout case
                            case 3:
                                signOff();
                                break;
                        }
                        return true;
                    }
                })
                .build();
        //********************** End of nav bar code

        //Firebase database -------------------
        listView = (ListView) findViewById(R.id.dashboard);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, list);
        listView.setAdapter(adapter);

        myDatabase = FirebaseDatabase.getInstance().getReference().child("children");
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Child aChild = snapshot.getValue(Child.class);
                    String txt = aChild.getName()+": "+aChild.getGlucose() ;
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
