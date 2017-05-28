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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class QuestionGenderActivity extends AppCompatActivity {

    int correct;
    int incorrect;
    int incomplete;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_gender);
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
        TextView textViewQuestionGender = (TextView) findViewById(R.id.question_gender);
        RadioButton radioButtonGenderAnswer1 = (RadioButton) findViewById(R.id.gender_answer_1);
        RadioButton radioButtonGenderAnswer2 = (RadioButton) findViewById(R.id.gender_answer_2);
        RadioButton radioButtonGenderAnswer3 = (RadioButton) findViewById(R.id.gender_answer_3);
        RadioButton radioButtonGenderAnswer4 = (RadioButton) findViewById(R.id.gender_answer_4);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), fontPathRobotoRegular);
        // Set the extra fancy font in the customFont TextView
        textViewQuestionGender.setTypeface(typeFaceRobotoMedium);
        radioButtonGenderAnswer1.setTypeface(typeFaceRobotoRegular);
        radioButtonGenderAnswer2.setTypeface(typeFaceRobotoRegular);
        radioButtonGenderAnswer3.setTypeface(typeFaceRobotoRegular);
        radioButtonGenderAnswer4.setTypeface(typeFaceRobotoRegular);

        // RadioGroup in two columns
        // https://stackoverflow.com/questions/10425569/radiogroup-with-two-columns-which-have-ten-radiobuttons
        RadioGroup radioGroupGenderAnswer1 = (RadioGroup) findViewById(R.id.gender_answer_column1);
        RadioGroup radioGroupGenderAnswer2 = (RadioGroup) findViewById(R.id.gender_answer_column2);
        radioGroupGenderAnswer1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        radioGroupGenderAnswer2.clearCheck();
        radioGroupGenderAnswer1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    fun2();
                }
            }
        });
        radioGroupGenderAnswer2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    fun1();
                }
            }
        });

        LinearLayout linearLayoutQuestionGender = (LinearLayout) findViewById(R.id.question_gender_layout);
        // Restore state from saved instance
        if (savedInstanceState != null) {
            int questionGenderVisibility = savedInstanceState.getInt("questionGenderVisibility");
            boolean isCheckedGenderAnswer1 = savedInstanceState.getBoolean("isCheckedGenderAnswer1");
            boolean isCheckedGenderAnswer2 = savedInstanceState.getBoolean("isCheckedGenderAnswer2");
            boolean isCheckedGenderAnswer3 = savedInstanceState.getBoolean("isCheckedGenderAnswer3");
            boolean isCheckedGenderAnswer4 = savedInstanceState.getBoolean("isCheckedGenderAnswer4");
            // Apply saved data (suppress warning about setting visibility)
            // noinspection ResourceType
            linearLayoutQuestionGender.setVisibility(questionGenderVisibility);
            radioButtonGenderAnswer1.setChecked(isCheckedGenderAnswer1);
            radioButtonGenderAnswer2.setChecked(isCheckedGenderAnswer2);
            radioButtonGenderAnswer3.setChecked(isCheckedGenderAnswer3);
            radioButtonGenderAnswer4.setChecked(isCheckedGenderAnswer4);
        } else {
            // Question in with animation from bottom (only when first open the activity)
            linearLayoutQuestionGender.setVisibility(View.VISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);
            linearLayoutQuestionGender.startAnimation(animSlide);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        LinearLayout linearLayoutQuestionGender = (LinearLayout) findViewById(R.id.question_gender_layout);
        int questionGenderVisibility = linearLayoutQuestionGender.getVisibility();
        RadioButton radioButtonGenderAnswer1 = (RadioButton) findViewById(R.id.gender_answer_1);
        boolean isCheckedGenderAnswer1 = radioButtonGenderAnswer1.isChecked();
        RadioButton radioButtonGenderAnswer2 = (RadioButton) findViewById(R.id.gender_answer_2);
        boolean isCheckedGenderAnswer2 = radioButtonGenderAnswer2.isChecked();
        RadioButton radioButtonGenderAnswer3 = (RadioButton) findViewById(R.id.gender_answer_3);
        boolean isCheckedGenderAnswer3 = radioButtonGenderAnswer3.isChecked();
        RadioButton radioButtonGenderAnswer4 = (RadioButton) findViewById(R.id.gender_answer_4);
        boolean isCheckedGenderAnswer4 = radioButtonGenderAnswer4.isChecked();
        savedInstanceState.putInt("questionGenderVisibility", questionGenderVisibility);
        savedInstanceState.putBoolean("isCheckedGenderAnswer1",isCheckedGenderAnswer1);
        savedInstanceState.putBoolean("isCheckedGenderAnswer2",isCheckedGenderAnswer2);
        savedInstanceState.putBoolean("isCheckedGenderAnswer3",isCheckedGenderAnswer3);
        savedInstanceState.putBoolean("isCheckedGenderAnswer4",isCheckedGenderAnswer4);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method is called when there's no checked radioButton in column 2
    public void fun1() {
        RadioGroup radioGroupGenderAnswer1 = (RadioGroup) findViewById(R.id.gender_answer_column1);
        radioGroupGenderAnswer1.setOnCheckedChangeListener(null);
        radioGroupGenderAnswer1.clearCheck();
        radioGroupGenderAnswer1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
            }
        });
    }

    // This method is called when there's no checked radioButton in column 1
    public void fun2() {
        RadioGroup radioGroupGenderAnswer2 = (RadioGroup) findViewById(R.id.gender_answer_column2);
        radioGroupGenderAnswer2.setOnCheckedChangeListener(null);
        radioGroupGenderAnswer2.clearCheck();
        radioGroupGenderAnswer2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun1();
            }
        });
    }

    // This method is called when the next button is clicked.
    public void next (View view) {
        RadioButton radioButtonGenderAnswer1 = (RadioButton) findViewById(R.id.gender_answer_1);
        boolean isCheckedGenderAnswer1 = radioButtonGenderAnswer1.isChecked();
        RadioButton radioButtonGenderAnswer2 = (RadioButton) findViewById(R.id.gender_answer_2);
        boolean isCheckedGenderAnswer2 = radioButtonGenderAnswer2.isChecked();
        RadioButton radioButtonGenderAnswer3 = (RadioButton) findViewById(R.id.gender_answer_3);
        boolean isCheckedGenderAnswer3 = radioButtonGenderAnswer3.isChecked();
        RadioButton radioButtonGenderAnswer4 = (RadioButton) findViewById(R.id.gender_answer_4);
        boolean isCheckedGenderAnswer4 = radioButtonGenderAnswer4.isChecked();
        if (!isCheckedGenderAnswer1 && !isCheckedGenderAnswer2 && !isCheckedGenderAnswer3
                && !isCheckedGenderAnswer4)
        {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer(isCheckedGenderAnswer3);
            LinearLayout linearLayoutQuestionGender = (LinearLayout) findViewById(R.id.question_gender_layout);
            linearLayoutQuestionGender.setVisibility(View.INVISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
            linearLayoutQuestionGender.startAnimation(animSlide);
            ViewFlipper backgroundViewFlipper = (ViewFlipper) findViewById(R.id.background_gender_viewFlipper);
            backgroundViewFlipper.showNext();
            //delay the execution
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Intent openActivityQuestionReachOcean = new Intent(getApplicationContext(), QuestionReachOceanActivity.class);
                    openActivityQuestionReachOcean.putExtra("playerName",playerName);
                    openActivityQuestionReachOcean.putExtra("correct",correct);
                    openActivityQuestionReachOcean.putExtra("incorrect",incorrect);
                    openActivityQuestionReachOcean.putExtra("incomplete",incomplete);
                    startActivity(openActivityQuestionReachOcean);
                    finish();
                }
            }, 2300); //delay is here
        }
    }

    /**
     * Checks if the answer is correct or no.
     *
     * @param isCheckedGenderAnswer3 is whether or not the correct answer is checked
     * @return value of score tracking variables
     */
    private int checkAnswer (boolean isCheckedGenderAnswer3) {
        if (isCheckedGenderAnswer3) {
            correct++;
            return correct;
        } else {
            incorrect++;
            return incorrect;
        }
    }

    // Do nothing when back button is pressed: don't allow going back until the quiz is completely done
    @Override
    public void onBackPressed() {
    }
}
