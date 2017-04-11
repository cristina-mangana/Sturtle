package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionDaysActivity extends AppCompatActivity {

    int correct;
    int incorrect;
    int incomplete;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_days);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        Bundle extras = getIntent().getExtras();
        playerName = extras.getString("playerName");
        correct = extras.getInt("correct");
        incorrect = extras.getInt("incorrect");
        incomplete = extras.getInt("incomplete");

        // Set the font's path
        String fontPathRobotoMedium = "fonts/Roboto-Medium.ttf";
        // Get the id TextView
        TextView textViewQuestionDays = (TextView) findViewById(R.id.question_days);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        // Set the extra fancy font in the customFont TextView
        textViewQuestionDays.setTypeface(typeFaceRobotoMedium);

        LinearLayout linearLayoutQuestionDays = (LinearLayout) findViewById(R.id.question_days_layout);
        // Restore state from saved instance
        if (savedInstanceState != null) {
            int questionDaysVisibility = savedInstanceState.getInt("questionDaysVisibility");
            String numberOfDays = savedInstanceState.getString("numberOfDays");
            // Apply saved data (suppress warning about setting visibility)
            // noinspection ResourceType
            linearLayoutQuestionDays.setVisibility(questionDaysVisibility);
            EditText daysEditText = (EditText) findViewById(R.id.days_edit_text);
            daysEditText.setText(numberOfDays);
        } else {
            // Question in with animation from bottom (only when first open the activity)
            linearLayoutQuestionDays.setVisibility(View.VISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);
            linearLayoutQuestionDays.startAnimation(animSlide);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        LinearLayout linearLayoutQuestionDays = (LinearLayout) findViewById(R.id.question_days_layout);
        int questionDaysVisibility = linearLayoutQuestionDays.getVisibility();
        savedInstanceState.putInt("questionDaysVisibility", questionDaysVisibility);
        EditText daysEditText = (EditText) findViewById(R.id.days_edit_text);
        if (!TextUtils.isEmpty(daysEditText.getText())) {
            String numberOfDays = daysEditText.getText().toString();
            savedInstanceState.putString("numberOfDays", numberOfDays);
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method is called when the next button is clicked.
    public void next (View view) {
        EditText daysEditText = (EditText) findViewById(R.id.days_edit_text);
        String numberOfDays = daysEditText.getText().toString();
        if (TextUtils.isEmpty(numberOfDays)) {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer(numberOfDays);
            LinearLayout linearLayoutQuestionDays = (LinearLayout) findViewById(R.id.question_days_layout);
            linearLayoutQuestionDays.setVisibility(View.INVISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
            linearLayoutQuestionDays.startAnimation(animSlide);
            ViewFlipper backgroundViewFlipper = (ViewFlipper) findViewById(R.id.background_days_viewFlipper);
            backgroundViewFlipper.showNext();
            // delay the execution
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Intent openActivityQuestionGender = new Intent(getApplicationContext(), QuestionGenderActivity.class);
                    openActivityQuestionGender.putExtra("playerName",playerName);
                    openActivityQuestionGender.putExtra("correct",correct);
                    openActivityQuestionGender.putExtra("incorrect",incorrect);
                    openActivityQuestionGender.putExtra("incomplete",incomplete);
                    startActivity(openActivityQuestionGender);
                    finish();
                }
            }, 4300); //delay is here
        }
    }

    /**
     * Checks if the answer is correct or no.
     *
     * @param numberOfDays is the number introduced by the user
     * @return value of score tracking variables
     */
    private int checkAnswer (String numberOfDays) {
        if (Integer.parseInt(numberOfDays) >= 45 && Integer.parseInt(numberOfDays) <= 70) {
            correct++;
            return correct;
        } else {
            incorrect++;
            return incorrect;
        }
    }

    // Do nothing when back button is pressed
    @Override
    public void onBackPressed() {
    }
}
