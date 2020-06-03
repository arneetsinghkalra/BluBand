package com.mcgill.bluband;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class NewChildActivity extends BaseActivity {
    private Button addChildButton;
    private EditText nameInput;
    private EditText genderInput;
    private EditText dateOfBirthInput;
    private EditText addressInput;
    private EditText contactPersonInput;
    private EditText phoneInput;
    DatabaseReference myDatabase;
    Child aNewChild;
    String name;
    String gender;
    String dateOfBirth;
    String address;
    String contactPerson;
    String phone;
    String childId;

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
        PrimaryDrawerItem children = new PrimaryDrawerItem().withIdentifier(2).withName("Add a Child").withSelectable(true);
        PrimaryDrawerItem nurses = new PrimaryDrawerItem().withIdentifier(3).withName("Nurses");
        PrimaryDrawerItem logout = new PrimaryDrawerItem().withIdentifier(4).withName("Logout");


        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(navHeader)
                .withSelectedItem(2)
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
                            case 2:
                                reOpenCurrentActivity();
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
        //********************** End of nav bar code
        //Get a reference for each field
        addChildButton = (Button)findViewById(R.id.addChildBtn);
        nameInput =(EditText) findViewById(R.id.nameText);
        genderInput = (EditText) findViewById(R.id.genderText);
        //ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item);
        //genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //genderInput.setAdapter(genderAdapter);
        dateOfBirthInput = (EditText)findViewById(R.id.dateOfBirthText);
        addressInput = (EditText)findViewById(R.id.addressText);
        contactPersonInput = (EditText)findViewById(R.id.contactPersonText);
        phoneInput = (EditText)findViewById(R.id.phoneText) ;


        //Create a child instance
        aNewChild = new Child();
        //Reference database
        myDatabase = FirebaseDatabase.getInstance().getReference().child("children");

        addChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store input in variables
                name = nameInput.getText().toString().trim();
                gender = genderInput.getText().toString().trim();
                dateOfBirth = dateOfBirthInput.getText().toString().trim();
                address = addressInput.getText().toString().trim();
                contactPerson = contactPersonInput.getText().toString().trim();
                phone = phoneInput.getText().toString().trim();

                //Check case that not all inputs were added
                if (name.isEmpty()){
                    Toast.makeText(NewChildActivity.this, "Please insert a name!", Toast.LENGTH_SHORT).show();
                }
                else if (gender.isEmpty()) {
                    Toast.makeText(NewChildActivity.this, "Please insert a gender!", Toast.LENGTH_SHORT).show();
                }
                else if (!((gender.equals("Male")) || (gender.equals("Female")))){
                    //If Male or Female is not inputted, show an error
                    Toast.makeText(NewChildActivity.this, gender+" Please type 'Male' or 'Female'!", Toast.LENGTH_SHORT).show();
                }
                else if (dateOfBirth.isEmpty()){
                    Toast.makeText(NewChildActivity.this, "Please insert a date of birth!", Toast.LENGTH_SHORT).show();
                }
                else if (address.isEmpty()){
                    Toast.makeText(NewChildActivity.this, "Please insert an address!", Toast.LENGTH_SHORT).show();
                }
                else if (phone.isEmpty()){
                    Toast.makeText(NewChildActivity.this, "Please insert a phone number!", Toast.LENGTH_SHORT).show();
                }
                else if (phone.length() != 10){
                    Toast.makeText(NewChildActivity.this, "Please insert a valid phone number!", Toast.LENGTH_SHORT).show();
                }
                else if (contactPerson.isEmpty()){
                    Toast.makeText(NewChildActivity.this, "Please insert a contact person!", Toast.LENGTH_SHORT).show();
                }
                else{
                    aNewChild.setName(name);
                    aNewChild.setGender(gender);
                    aNewChild.setDateOfBirth(dateOfBirth);
                    aNewChild.setAddress(address);
                    aNewChild.setContactPerson(contactPerson);
                    aNewChild.setPhone(phone);
                    //Add to database
                    myDatabase.push().setValue(aNewChild);
                    //Show a toast to see if it worked
                    Toast.makeText(NewChildActivity.this, "Added "+name, Toast.LENGTH_LONG).show();
                    openNewChildActivity();
                }
            }
        });
    }
}
