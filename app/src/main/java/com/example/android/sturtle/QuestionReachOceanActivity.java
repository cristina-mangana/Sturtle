package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionReachOceanActivity extends AppCompatActivity {

    int correct;
    int incorrect;
    int incomplete;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_reach_ocean);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        Bundle extras = getIntent().getExtras();
        playerName = extras.getString("playerName");
        correct = extras.getInt("correct");
        incorrect = extras.getInt("incorrect");
        incomplete = extras.getInt("incomplete");

        // Set the font's path
        String fontPathRobotoMedium = "fonts/Roboto-Medium.ttf";
        String fontPathRobotoRegular = "fonts/Roboto-Regular.ttf";
        // Get the id TextView
        TextView textViewQuestionReachOcean = (TextView) findViewById(R.id.question_reach_ocean);
        RadioButton radioButtonReachOceanAnswer1 = (RadioButton) findViewById(R.id.reach_ocean_answer_1);
        RadioButton radioButtonReachOceanAnswer2 = (RadioButton) findViewById(R.id.reach_ocean_answer_2);
        RadioButton radioButtonReachOceanAnswer3 = (RadioButton) findViewById(R.id.reach_ocean_answer_3);
        RadioButton radioButtonReachOceanAnswer4 = (RadioButton) findViewById(R.id.reach_ocean_answer_4);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), fontPathRobotoRegular);
        // Set the extra fancy font in the customFont TextView
        textViewQuestionReachOcean.setTypeface(typeFaceRobotoMedium);
        radioButtonReachOceanAnswer1.setTypeface(typeFaceRobotoRegular);
        radioButtonReachOceanAnswer2.setTypeface(typeFaceRobotoRegular);
        radioButtonReachOceanAnswer3.setTypeface(typeFaceRobotoRegular);
        radioButtonReachOceanAnswer4.setTypeface(typeFaceRobotoRegular);

        LinearLayout linearLayoutQuestionReachOcean = (LinearLayout) findViewById(R.id.question_reach_ocean_layout);
        // Restore state from saved instance
        if (savedInstanceState != null) {
            int questionReachOceanVisibility = savedInstanceState.getInt("questionReachOceanVisibility");
            boolean isCheckedReachOceanAnswer1 = savedInstanceState.getBoolean("isCheckedReachOceanAnswer1");
            boolean isCheckedReachOceanAnswer2 = savedInstanceState.getBoolean("isCheckedReachOceanAnswer2");
            boolean isCheckedReachOceanAnswer3 = savedInstanceState.getBoolean("isCheckedReachOceanAnswer3");
            boolean isCheckedReachOceanAnswer4 = savedInstanceState.getBoolean("isCheckedReachOceanAnswer4");
            // Apply saved data (suppress warning about setting visibility)
            // noinspection ResourceType
            linearLayoutQuestionReachOcean.setVisibility(questionReachOceanVisibility);
            radioButtonReachOceanAnswer1.setChecked(isCheckedReachOceanAnswer1);
            radioButtonReachOceanAnswer2.setChecked(isCheckedReachOceanAnswer2);
            radioButtonReachOceanAnswer3.setChecked(isCheckedReachOceanAnswer3);
            radioButtonReachOceanAnswer4.setChecked(isCheckedReachOceanAnswer4);
        } else {
            // Question in with animation from bottom (only when first open the activity)
            linearLayoutQuestionReachOcean.setVisibility(View.VISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);
            linearLayoutQuestionReachOcean.startAnimation(animSlide);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        LinearLayout linearLayoutQuestionReachOcean = (LinearLayout) findViewById(R.id.question_reach_ocean_layout);
        int questionReachOceanVisibility = linearLayoutQuestionReachOcean.getVisibility();
        RadioButton radioButtonReachOceanAnswer1 = (RadioButton) findViewById(R.id.reach_ocean_answer_1);
        boolean isCheckedReachOceanAnswer1 = radioButtonReachOceanAnswer1.isChecked();
        RadioButton radioButtonReachOceanAnswer2 = (RadioButton) findViewById(R.id.reach_ocean_answer_2);
        boolean isCheckedReachOceanAnswer2 = radioButtonReachOceanAnswer2.isChecked();
        RadioButton radioButtonReachOceanAnswer3 = (RadioButton) findViewById(R.id.reach_ocean_answer_3);
        boolean isCheckedReachOceanAnswer3 = radioButtonReachOceanAnswer3.isChecked();
        RadioButton radioButtonReachOceanAnswer4 = (RadioButton) findViewById(R.id.reach_ocean_answer_4);
        boolean isCheckedReachOceanAnswer4 = radioButtonReachOceanAnswer4.isChecked();
        savedInstanceState.putInt("questionReachOceanVisibility", questionReachOceanVisibility);
        savedInstanceState.putBoolean("isCheckedReachOceanAnswer1",isCheckedReachOceanAnswer1);
        savedInstanceState.putBoolean("isCheckedReachOceanAnswer2",isCheckedReachOceanAnswer2);
        savedInstanceState.putBoolean("isCheckedReachOceanAnswer3",isCheckedReachOceanAnswer3);
        savedInstanceState.putBoolean("isCheckedReachOceanAnswer4",isCheckedReachOceanAnswer4);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method is called when the next button is clicked.
    public void finish (View view) {
        RadioButton radioButtonReachOceanAnswer1 = (RadioButton) findViewById(R.id.reach_ocean_answer_1);
        boolean isCheckedReachOceanAnswer1 = radioButtonReachOceanAnswer1.isChecked();
        RadioButton radioButtonReachOceanAnswer2 = (RadioButton) findViewById(R.id.reach_ocean_answer_2);
        boolean isCheckedReachOceanAnswer2 = radioButtonReachOceanAnswer2.isChecked();
        RadioButton radioButtonReachOceanAnswer3 = (RadioButton) findViewById(R.id.reach_ocean_answer_3);
        boolean isCheckedReachOceanAnswer3 = radioButtonReachOceanAnswer3.isChecked();
        RadioButton radioButtonReachOceanAnswer4 = (RadioButton) findViewById(R.id.reach_ocean_answer_4);
        boolean isCheckedReachOceanAnswer4 = radioButtonReachOceanAnswer4.isChecked();
        if (!isCheckedReachOceanAnswer1 && !isCheckedReachOceanAnswer2 && !isCheckedReachOceanAnswer3
                && !isCheckedReachOceanAnswer4)
        {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer(isCheckedReachOceanAnswer2);
            LinearLayout linearLayoutQuestionReachOcean = (LinearLayout) findViewById(R.id.question_reach_ocean_layout);
            linearLayoutQuestionReachOcean.setVisibility(View.INVISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
            linearLayoutQuestionReachOcean.startAnimation(animSlide);
            ViewFlipper backgroundViewFlipper = (ViewFlipper) findViewById(R.id.background_reach_ocean_viewFlipper);
            backgroundViewFlipper.showNext();
            Toast.makeText(this, getString(R.string.finish_toast), Toast.LENGTH_SHORT).show();
            //delay the execution
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Intent openActivityResult = new Intent(getApplicationContext(), ResultActivity.class);
                    openActivityResult.putExtra("playerName",playerName);
                    openActivityResult.putExtra("correct",correct);
                    openActivityResult.putExtra("incorrect",incorrect);
                    openActivityResult.putExtra("incomplete",incomplete);
                    startActivity(openActivityResult);
                    finish();
                }
            }, 2300); //delay is here
        }
    }

    /**
     * Checks if the answer is correct or no.
     *
     * @param isCheckedReachOceanAnswer2 is whether or not the correct answer is checked
     * @return value of score tracking variables
     */
    private int checkAnswer (boolean isCheckedReachOceanAnswer2) {
        if (isCheckedReachOceanAnswer2) {
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
