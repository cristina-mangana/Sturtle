package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionFoodActivity extends AppCompatActivity {

    int correct = 0;
    int incorrect = 0;
    int incomplete = 0;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_food);

        Bundle extras = getIntent().getExtras();
        playerName = extras.getString("playerName");

        // Set the font's path
        String fontPathRobotoMedium = "fonts/Roboto-Medium.ttf";
        String fontPathRobotoRegular = "fonts/Roboto-Regular.ttf";
        // Get the id TextView
        TextView textViewQuestionFood = (TextView) findViewById(R.id.question_food);
        CheckBox checkBoxFoodAnswer1 = (CheckBox) findViewById(R.id.food_answer_1);
        CheckBox checkBoxFoodAnswer2 = (CheckBox) findViewById(R.id.food_answer_2);
        CheckBox checkBoxFoodAnswer3 = (CheckBox) findViewById(R.id.food_answer_3);
        CheckBox checkBoxFoodAnswer4 = (CheckBox) findViewById(R.id.food_answer_4);
        CheckBox checkBoxFoodAnswer5 = (CheckBox) findViewById(R.id.food_answer_5);
        CheckBox checkBoxFoodAnswer6 = (CheckBox) findViewById(R.id.food_answer_6);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), fontPathRobotoRegular);
        // Set the extra fancy font in the customFont TextView
        textViewQuestionFood.setTypeface(typeFaceRobotoMedium);
        checkBoxFoodAnswer1.setTypeface(typeFaceRobotoRegular);
        checkBoxFoodAnswer2.setTypeface(typeFaceRobotoRegular);
        checkBoxFoodAnswer3.setTypeface(typeFaceRobotoRegular);
        checkBoxFoodAnswer4.setTypeface(typeFaceRobotoRegular);
        checkBoxFoodAnswer5.setTypeface(typeFaceRobotoRegular);
        checkBoxFoodAnswer6.setTypeface(typeFaceRobotoRegular);

        LinearLayout linearLayoutQuestionFood = (LinearLayout) findViewById(R.id.question_food_layout);
        // Restore state from saved instance
        if (savedInstanceState != null) {
            int questionFoodVisibility = savedInstanceState.getInt("questionFoodVisibility");
            boolean isCheckedFoodAnswer1 = savedInstanceState.getBoolean("isCheckedFoodAnswer1");
            boolean isCheckedFoodAnswer2 = savedInstanceState.getBoolean("isCheckedFoodAnswer2");
            boolean isCheckedFoodAnswer3 = savedInstanceState.getBoolean("isCheckedFoodAnswer3");
            boolean isCheckedFoodAnswer4 = savedInstanceState.getBoolean("isCheckedFoodAnswer4");
            boolean isCheckedFoodAnswer5 = savedInstanceState.getBoolean("isCheckedFoodAnswer5");
            boolean isCheckedFoodAnswer6 = savedInstanceState.getBoolean("isCheckedFoodAnswer6");
            // Apply saved data (suppress warning about setting visibility)
            // https://stackoverflow.com/questions/26139115/not-able-to-dynamically-set-the-setvisibility-parameter
            // noinspection ResourceType
            linearLayoutQuestionFood.setVisibility(questionFoodVisibility);
            checkBoxFoodAnswer1.setChecked(isCheckedFoodAnswer1);
            checkBoxFoodAnswer2.setChecked(isCheckedFoodAnswer2);
            checkBoxFoodAnswer3.setChecked(isCheckedFoodAnswer3);
            checkBoxFoodAnswer4.setChecked(isCheckedFoodAnswer4);
            checkBoxFoodAnswer5.setChecked(isCheckedFoodAnswer5);
            checkBoxFoodAnswer6.setChecked(isCheckedFoodAnswer6);
        } else {
            // Question in with animation from bottom (only when first open the activity: when
            // saveInstanceState == null)
            linearLayoutQuestionFood.setVisibility(View.VISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);
            linearLayoutQuestionFood.startAnimation(animSlide);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        LinearLayout linearLayoutQuestionFood = (LinearLayout) findViewById(R.id.question_food_layout);
        int questionFoodVisibility = linearLayoutQuestionFood.getVisibility();
        CheckBox checkBoxFoodAnswer1 = (CheckBox) findViewById(R.id.food_answer_1);
        boolean isCheckedFoodAnswer1 = checkBoxFoodAnswer1.isChecked();
        CheckBox checkBoxFoodAnswer2 = (CheckBox) findViewById(R.id.food_answer_2);
        boolean isCheckedFoodAnswer2 = checkBoxFoodAnswer2.isChecked();
        CheckBox checkBoxFoodAnswer3 = (CheckBox) findViewById(R.id.food_answer_3);
        boolean isCheckedFoodAnswer3 = checkBoxFoodAnswer3.isChecked();
        CheckBox checkBoxFoodAnswer4 = (CheckBox) findViewById(R.id.food_answer_4);
        boolean isCheckedFoodAnswer4 = checkBoxFoodAnswer4.isChecked();
        CheckBox checkBoxFoodAnswer5 = (CheckBox) findViewById(R.id.food_answer_5);
        boolean isCheckedFoodAnswer5 = checkBoxFoodAnswer5.isChecked();
        CheckBox checkBoxFoodAnswer6 = (CheckBox) findViewById(R.id.food_answer_6);
        boolean isCheckedFoodAnswer6 = checkBoxFoodAnswer6.isChecked();
        savedInstanceState.putInt("questionFoodVisibility", questionFoodVisibility);
        savedInstanceState.putBoolean("isCheckedFoodAnswer1",isCheckedFoodAnswer1);
        savedInstanceState.putBoolean("isCheckedFoodAnswer2",isCheckedFoodAnswer2);
        savedInstanceState.putBoolean("isCheckedFoodAnswer3",isCheckedFoodAnswer3);
        savedInstanceState.putBoolean("isCheckedFoodAnswer4",isCheckedFoodAnswer4);
        savedInstanceState.putBoolean("isCheckedFoodAnswer5",isCheckedFoodAnswer5);
        savedInstanceState.putBoolean("isCheckedFoodAnswer6",isCheckedFoodAnswer6);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method is called when the next button is clicked.
    public void next (View view) {
        CheckBox checkBoxFoodAnswer1 = (CheckBox) findViewById(R.id.food_answer_1);
        boolean isCheckedFoodAnswer1 = checkBoxFoodAnswer1.isChecked();
        CheckBox checkBoxFoodAnswer2 = (CheckBox) findViewById(R.id.food_answer_2);
        boolean isCheckedFoodAnswer2 = checkBoxFoodAnswer2.isChecked();
        CheckBox checkBoxFoodAnswer3 = (CheckBox) findViewById(R.id.food_answer_3);
        boolean isCheckedFoodAnswer3 = checkBoxFoodAnswer3.isChecked();
        CheckBox checkBoxFoodAnswer4 = (CheckBox) findViewById(R.id.food_answer_4);
        boolean isCheckedFoodAnswer4 = checkBoxFoodAnswer4.isChecked();
        CheckBox checkBoxFoodAnswer5 = (CheckBox) findViewById(R.id.food_answer_5);
        boolean isCheckedFoodAnswer5 = checkBoxFoodAnswer5.isChecked();
        CheckBox checkBoxFoodAnswer6 = (CheckBox) findViewById(R.id.food_answer_6);
        boolean isCheckedFoodAnswer6 = checkBoxFoodAnswer6.isChecked();
        if (!isCheckedFoodAnswer1 && !isCheckedFoodAnswer2 && !isCheckedFoodAnswer3
                && !isCheckedFoodAnswer4 && !isCheckedFoodAnswer5 && !isCheckedFoodAnswer6)
        {
            // Show an error message as a toast if there's no answer
            Toast.makeText(this, getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer(isCheckedFoodAnswer1, isCheckedFoodAnswer2, isCheckedFoodAnswer3,
                    isCheckedFoodAnswer4, isCheckedFoodAnswer5, isCheckedFoodAnswer6);
            LinearLayout linearLayoutQuestionFood = (LinearLayout) findViewById(R.id.question_food_layout);
            linearLayoutQuestionFood.setVisibility(View.INVISIBLE);
            // Question out to bottom animated
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
            linearLayoutQuestionFood.startAnimation(animSlide);
            ViewFlipper backgroundViewFlipper = (ViewFlipper) findViewById(R.id.background_food_viewFlipper);
            backgroundViewFlipper.showNext();
            //delay the execution until animated gif finishes
            // https://stackoverflow.com/questions/5321344/android-animation-wait-until-finished
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Intent openActivityQuestionLayEggs = new Intent(getApplicationContext(), QuestionLayEggsActivity.class);
                    openActivityQuestionLayEggs.putExtra("playerName",playerName);
                    openActivityQuestionLayEggs.putExtra("correct",correct);
                    openActivityQuestionLayEggs.putExtra("incorrect",incorrect);
                    openActivityQuestionLayEggs.putExtra("incomplete",incomplete);
                    startActivity(openActivityQuestionLayEggs);
                    finish();
                }
            }, 3100); //delay is here
        }
    }

    /**
     * Checks if the answer is correct or no.
     *
     * @param isCheckedFoodAnswer1 is whether or not the 1 answer is checked
     * @param isCheckedFoodAnswer2 is whether or not the 2 answer is checked
     * @param isCheckedFoodAnswer3 is whether or not the 3 answer is checked
     * @param isCheckedFoodAnswer4 is whether or not the 4 answer is checked
     * @param isCheckedFoodAnswer5 is whether or not the 5 answer is checked
     * @param isCheckedFoodAnswer6 is whether or not the 6 answer is checked
     * @return value of score tracking variables
     */
    private int checkAnswer (boolean isCheckedFoodAnswer1, boolean isCheckedFoodAnswer2, boolean isCheckedFoodAnswer3,
            boolean isCheckedFoodAnswer4, boolean isCheckedFoodAnswer5, boolean isCheckedFoodAnswer6) {
        // Only first option is incorrect. If options 2-6 aren't all checked, answer is correct but incomplete
        // TODO include messages to the user with tips
        if (!isCheckedFoodAnswer1) {
            if (isCheckedFoodAnswer2 && isCheckedFoodAnswer3
                    && isCheckedFoodAnswer4 && isCheckedFoodAnswer5 && isCheckedFoodAnswer6) {
                correct++;
                return correct;
            } else {
                incomplete++;
                return incomplete;
            }
        } else {
            incorrect++;
            return incorrect;
        }
    }

    // Do nothing when back button is pressed: don't allow going back until the quiz is completely done
    // https://stackoverflow.com/questions/4779954/disable-back-button-in-android
    @Override
    public void onBackPressed() {
    }
}
