package com.example.foodfight;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/** Collects user's specifics for BMI calculation and
 *  calorie goal settings.  A button at the bottom navigates to
 *  the AppAbout activity.  When the user data is saved, the food database
 *  will be initialized if it isn't already.
 *
 *  Driver: P Proctor
 */
public class acUserProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //get values to display
        Profile profile = new ProfileHandler().getProfile();
        //set display values
        TextView height = findViewById(R.id.numHeight);
        String testNum = "";

        height.setText(testNum);


    }

    // Called when the user taps the About button
    public void btnAppAbout (View view) {
        Intent intent = new Intent(this, acAppAbout.class);
        startActivity(intent);
    }



    // Update

}