package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the font's path
        String fontPathRobotoMedium = "fonts/Roboto-Medium.ttf";
        // Get the id TextView
        TextView textViewDescription = (TextView) findViewById(R.id.description);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        // Set the extra fancy font in the customFont TextView
        textViewDescription.setTypeface(typeFaceRobotoMedium);

        // Restore state from saved instance
        if (savedInstanceState != null) {
            String name = savedInstanceState.getString("name");
            // Apply saved data
            EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
            nameEditText.setText(name);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        if (!TextUtils.isEmpty(nameEditText.getText())) {
            String name = nameEditText.getText().toString();
            savedInstanceState.putString("name", name);
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    //This method is called when the right arrow (start) button is clicked.
    public void start(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String playerName = nameEditText.getText().toString();
        if (!TextUtils.isEmpty(playerName)) {
            Intent openActivityQuestionFood = new Intent(this, QuestionFoodActivity.class);
            openActivityQuestionFood.putExtra("playerName",playerName);
            startActivity(openActivityQuestionFood);
        } else {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.name_error), Toast.LENGTH_SHORT).show();
        }
    }
}
