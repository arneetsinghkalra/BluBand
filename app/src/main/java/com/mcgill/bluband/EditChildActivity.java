package com.mcgill.bluband;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditChildActivity extends BaseActivity {

    private EditText nameInput;
    private EditText genderInput;
    private EditText dateOfBirthInput;
    private EditText addressInput;
    private EditText contactPersonInput;
    private EditText phoneInput;
    DatabaseReference myDatabase;
    String name;
    String gender;
    String dateOfBirth;
    String address;
    String contactPerson;
    String phone;
    String childId;
    Button editChild;
    Button removeChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child);

        //Get a reference for each field
        nameInput =(EditText) findViewById(R.id.editNameText);
        genderInput = (EditText) findViewById(R.id.editGenderText);
        dateOfBirthInput = (EditText)findViewById(R.id.editDateOfBirthText);
        addressInput = (EditText)findViewById(R.id.editAddressText);
        contactPersonInput = (EditText)findViewById(R.id.editContactPersonText);
        phoneInput = (EditText)findViewById(R.id.editPhoneText) ;
        editChild = (Button) findViewById(R.id.editChildBtn);

        //Get id of child we want to modify, which is passed in from the child graph activity
        Intent intent = getIntent();
        final String childId = intent.getStringExtra("CHILD_ID");

        Child existingChild;

        //Set an on click listener to to the edit child button. When clicked it retrieves the info added by the user and then updates it. Finally returns
        //back to the details page of that specific child.
        editChild.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getEditTextInput();
                //Update fields that have been modified
                myDatabase = FirebaseDatabase.getInstance().getReference().child("children").child(childId);

                if (!address.isEmpty()) {
                    myDatabase.child("address").setValue(address);
                }
                if (!name.isEmpty()) {
                    myDatabase.child("name").setValue(name);
                }
                if (!gender.isEmpty()){
                    myDatabase.child("gender").setValue(gender);
                }
                if (!dateOfBirth.isEmpty()) {
                    myDatabase.child("dateOfBirth").setValue(dateOfBirth);
                }
                if (!contactPerson.isEmpty()) {
                    myDatabase.child("contactPerson").setValue(contactPerson);
                }
                if (!phone.isEmpty()) {
                    myDatabase.child("phone").setValue(phone);
                }
                //Go back to child details of child that was updated
                openChildGraphActivity(childId);
            }
        });


    }

    private void getEditTextInput(){
        //Store input in variables
        name = nameInput.getText().toString().trim();
        gender = genderInput.getText().toString().trim();
        dateOfBirth = dateOfBirthInput.getText().toString().trim();
        address = addressInput.getText().toString().trim();
        contactPerson = contactPersonInput.getText().toString().trim();
        phone = phoneInput.getText().toString().trim();
    }


}
