package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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
import android.widget.Toast;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.w3c.dom.Text;

public class ChildGraphActivity extends BaseActivity {

    TextView childNameTextView;
    TextView genderText;
    TextView addressText;
    TextView phoneText;
    TextView contactPersonText;
    TextView dateOfBirthText;

    Button editBtn;
    Button removeChild;

    String childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_graph);

        childNameTextView = (TextView) findViewById(R.id.childNameTextView);
        genderText = (TextView) findViewById(R.id.genderFixedText);
        addressText = (TextView) findViewById(R.id.addressFixedText);
        phoneText = (TextView) findViewById(R.id.phoneFixedText);
        contactPersonText = (TextView) findViewById(R.id.contactPersonFixedText);
        dateOfBirthText = (TextView) findViewById(R.id.dateOfBirthFixedText);

        editBtn = (Button) findViewById(R.id.editChildBtn);
        removeChild = (Button) findViewById(R.id.removeChildBtn);
        final DatabaseReference childDatabase;

        setNavigationDrawer();

        Intent intent = getIntent();
        int childNumberId = intent.getIntExtra("CHILD_ID", -1);
        String childId = "CH" + String.format("%03d", childNumberId);

        //A valid ID was passed
        if (!childId.isEmpty()) {
            childDatabase= FirebaseDatabase.getInstance().getReference().child("children").child(childId);
            addChildInfo(childDatabase);
        if (childNumberId > -1) {
            final DatabaseReference childDatabase= FirebaseDatabase.getInstance().getReference().child("children").child(childId);

            setTitle(childDatabase);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openEditChildActivity();
                }
            });

            removeChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChildGraphActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Remove "+childName);
                    builder.setMessage("Are you sure you would like to remove this child from the database?");
                    builder.setPositiveButton("Remove Child",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    childDatabase.removeValue();
                                    openHomeActivity();
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Do nothing
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.bluBandBlue));
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.bluBandBlue));

                }
            });

        }
    }

    private void setNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getDrawable(R.color.lightBlue));
        ImageView navBtn = (ImageView) findViewById(R.id.backButton);
        navBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                openHomeActivity();
            }
        });
        //Commented the navgation bar out because we should ideally only use a back button to return to the home activity
        /*
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
         */
    }

    private void openChildDatabase() {
        
    }

    //Method that captures all child data from database and inputs it into the correct fields
    private void addChildInfo(DatabaseReference childDatabase) {
        childDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Child child = dataSnapshot.getValue(Child.class);
                childName = child.getName();
                String childGender = child.getGender();
                String childAddress= child.getAddress();
                String childPhone = child.getPhone();
                String childContactPerson = child.getContactPerson();
                String childDateOfBirth = child.getDateOfBirth();

                if (childPhone == null) childPhone = "Phone number not provided";

                childNameTextView.setText(childName);
                genderText.setText("Gender: "+childGender);
                addressText.setText("Address: "+childAddress);
                phoneText.setText("Phone: "+childPhone);
                contactPersonText.setText("Contact Person: "+childContactPerson);
                dateOfBirthText.setText("Date of Birth: "+childDateOfBirth);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
