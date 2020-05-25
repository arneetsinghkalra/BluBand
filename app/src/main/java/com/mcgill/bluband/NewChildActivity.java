package com.mcgill.bluband;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class NewChildActivity extends BaseActivity {
    FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button addChildButton;
    private EditText nameInput;
    private EditText genderInput;
    private EditText dateOfBirthInput;
    private EditText addressInput;
    private EditText contactPersonInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child);

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

        final AccountHeader navHeader  = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.mipmap.logo)
                .build();

        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1).withName("Home").withSelectable(true);
        PrimaryDrawerItem children = new PrimaryDrawerItem().withIdentifier(2).withName("Children").withSelectable(true);
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(2).withName("Logout");


        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(navHeader)
                .withSelectedItem(2)
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
                                openHomeActivity();
                                break;
                            case 2:
                                reOpenCurrentActivity();
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

        addChildButton = findViewById(R.id.addChildBtn);
        nameInput = findViewById(R.id.nameText);
        genderInput = findViewById(R.id.genderText);
        dateOfBirthInput = findViewById(R.id.dateOfBirthText);
        addressInput = findViewById(R.id.addressText);
        contactPersonInput = findViewById(R.id.contactPersonText);

    }
}
