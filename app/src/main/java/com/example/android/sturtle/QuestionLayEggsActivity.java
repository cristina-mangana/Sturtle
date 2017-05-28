package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class QuestionLayEggsActivity extends AppCompatActivity {

    int correct;
    int incorrect;
    int incomplete;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_lay_eggs);
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
        TextView textViewQuestionLayEggs = (TextView) findViewById(R.id.question_lay_eggs);
        RadioButton radioButtonLayEggsAnswer1 = (RadioButton) findViewById(R.id.lay_eggs_answer_1);
        RadioButton radioButtonLayEggsAnswer2 = (RadioButton) findViewById(R.id.lay_eggs_answer_2);
        RadioButton radioButtonLayEggsAnswer3 = (RadioButton) findViewById(R.id.lay_eggs_answer_3);
        RadioButton radioButtonLayEggsAnswer4 = (RadioButton) findViewById(R.id.lay_eggs_answer_4);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), fontPathRobotoRegular);
        // Set the extra fancy font in the customFont TextView
        textViewQuestionLayEggs.setTypeface(typeFaceRobotoMedium);
        radioButtonLayEggsAnswer1.setTypeface(typeFaceRobotoRegular);
        radioButtonLayEggsAnswer2.setTypeface(typeFaceRobotoRegular);
        radioButtonLayEggsAnswer3.setTypeface(typeFaceRobotoRegular);
        radioButtonLayEggsAnswer4.setTypeface(typeFaceRobotoRegular);

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            // RadioGroup in two columns, only in landscape
            // https://stackoverflow.com/questions/10425569/radiogroup-with-two-columns-which-have-ten-radiobuttons
            RadioGroup radioGroupLayEggsAnswer1 = (RadioGroup) findViewById(R.id.lay_eggs_answer_column1);
            RadioGroup radioGroupLayEggsAnswer2 = (RadioGroup) findViewById(R.id.lay_eggs_answer_column2);
            radioGroupLayEggsAnswer1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
            radioGroupLayEggsAnswer2.clearCheck();
            radioGroupLayEggsAnswer1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId != -1) {
                        fun2();
                    }
                }
            });
            radioGroupLayEggsAnswer2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId != -1) {
                        fun1();
                    }
                }
            });
        }

        LinearLayout linearLayoutQuestionLayEggs = (LinearLayout) findViewById(R.id.question_lay_eggs_layout);
        // Restore state from saved instance
        if (savedInstanceState != null) {
            int questionLayEggsVisibility = savedInstanceState.getInt("questionLayEggsVisibility");
            boolean isCheckedLayEggsAnswer1 = savedInstanceState.getBoolean("isCheckedLayEggsAnswer1");
            boolean isCheckedLayEggsAnswer2 = savedInstanceState.getBoolean("isCheckedLayEggsAnswer2");
            boolean isCheckedLayEggsAnswer3 = savedInstanceState.getBoolean("isCheckedLayEggsAnswer3");
            boolean isCheckedLayEggsAnswer4 = savedInstanceState.getBoolean("isCheckedLayEggsAnswer4");
            // Apply saved data (suppress warning about setting visibility)
            // noinspection ResourceType
            linearLayoutQuestionLayEggs.setVisibility(questionLayEggsVisibility);
            radioButtonLayEggsAnswer1.setChecked(isCheckedLayEggsAnswer1);
            radioButtonLayEggsAnswer2.setChecked(isCheckedLayEggsAnswer2);
            radioButtonLayEggsAnswer3.setChecked(isCheckedLayEggsAnswer3);
            radioButtonLayEggsAnswer4.setChecked(isCheckedLayEggsAnswer4);
        } else {
            // Question in with animation from bottom (only when first open the activity)
            linearLayoutQuestionLayEggs.setVisibility(View.VISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.in_from_bottom);
            linearLayoutQuestionLayEggs.startAnimation(animSlide);
        }
    }

    // Fires when a configuration change occurs and fragment needs to save state
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the current state
        LinearLayout linearLayoutQuestionLayEggs = (LinearLayout) findViewById(R.id.question_lay_eggs_layout);
        int questionLayEggsVisibility = linearLayoutQuestionLayEggs.getVisibility();
        RadioButton radioButtonLayEggsAnswer1 = (RadioButton) findViewById(R.id.lay_eggs_answer_1);
        boolean isCheckedLayEggsAnswer1 = radioButtonLayEggsAnswer1.isChecked();
        RadioButton radioButtonLayEggsAnswer2 = (RadioButton) findViewById(R.id.lay_eggs_answer_2);
        boolean isCheckedLayEggsAnswer2 = radioButtonLayEggsAnswer2.isChecked();
        RadioButton radioButtonLayEggsAnswer3 = (RadioButton) findViewById(R.id.lay_eggs_answer_3);
        boolean isCheckedLayEggsAnswer3 = radioButtonLayEggsAnswer3.isChecked();
        RadioButton radioButtonLayEggsAnswer4 = (RadioButton) findViewById(R.id.lay_eggs_answer_4);
        boolean isCheckedLayEggsAnswer4 = radioButtonLayEggsAnswer4.isChecked();
        savedInstanceState.putInt("questionLayEggsVisibility", questionLayEggsVisibility);
        savedInstanceState.putBoolean("isCheckedLayEggsAnswer1",isCheckedLayEggsAnswer1);
        savedInstanceState.putBoolean("isCheckedLayEggsAnswer2",isCheckedLayEggsAnswer2);
        savedInstanceState.putBoolean("isCheckedLayEggsAnswer3",isCheckedLayEggsAnswer3);
        savedInstanceState.putBoolean("isCheckedLayEggsAnswer4",isCheckedLayEggsAnswer4);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method is called when there's no checked radioButton in column 2
    public void fun1() {
        RadioGroup radioGroupLayEggsAnswer1 = (RadioGroup) findViewById(R.id.lay_eggs_answer_column1);
        radioGroupLayEggsAnswer1.setOnCheckedChangeListener(null);
        radioGroupLayEggsAnswer1.clearCheck();
        radioGroupLayEggsAnswer1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun2();
            }
        });
    }

    // This method is called when there's no checked radioButton in column 1
    public void fun2() {
        RadioGroup radioGroupLayEggsAnswer2 = (RadioGroup) findViewById(R.id.lay_eggs_answer_column2);
        radioGroupLayEggsAnswer2.setOnCheckedChangeListener(null);
        radioGroupLayEggsAnswer2.clearCheck();
        radioGroupLayEggsAnswer2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                fun1();
            }
        });
    }

    // This method is called when the next button is clicked.
    public void next (View view) {
        RadioButton radioButtonLayEggsAnswer1 = (RadioButton) findViewById(R.id.lay_eggs_answer_1);
        boolean isCheckedLayEggsAnswer1 = radioButtonLayEggsAnswer1.isChecked();
        RadioButton radioButtonLayEggsAnswer2 = (RadioButton) findViewById(R.id.lay_eggs_answer_2);
        boolean isCheckedLayEggsAnswer2 = radioButtonLayEggsAnswer2.isChecked();
        RadioButton radioButtonLayEggsAnswer3 = (RadioButton) findViewById(R.id.lay_eggs_answer_3);
        boolean isCheckedLayEggsAnswer3 = radioButtonLayEggsAnswer3.isChecked();
        RadioButton radioButtonLayEggsAnswer4 = (RadioButton) findViewById(R.id.lay_eggs_answer_4);
        boolean isCheckedLayEggsAnswer4 = radioButtonLayEggsAnswer4.isChecked();
        if (!isCheckedLayEggsAnswer1 && !isCheckedLayEggsAnswer2 && !isCheckedLayEggsAnswer3
                && !isCheckedLayEggsAnswer4)
        {
            // Show an error message as a toast
            Toast.makeText(this, getString(R.string.answer_error), Toast.LENGTH_SHORT).show();
        } else {
            checkAnswer(isCheckedLayEggsAnswer1);
            LinearLayout linearLayoutQuestionLayEggs = (LinearLayout) findViewById(R.id.question_lay_eggs_layout);
            linearLayoutQuestionLayEggs.setVisibility(View.INVISIBLE);
            Animation animSlide = AnimationUtils.loadAnimation(this, R.anim.out_to_bottom);
            linearLayoutQuestionLayEggs.startAnimation(animSlide);
            ViewFlipper backgroundViewFlipper = (ViewFlipper) findViewById(R.id.background_lay_eggs_viewFlipper);
            backgroundViewFlipper.showNext();
            //delay the execution
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run() {
                    Intent openActivityQuestionDays = new Intent(getApplicationContext(), QuestionDaysActivity.class);
                    openActivityQuestionDays.putExtra("playerName",playerName);
                    openActivityQuestionDays.putExtra("correct",correct);
                    openActivityQuestionDays.putExtra("incorrect",incorrect);
                    openActivityQuestionDays.putExtra("incomplete",incomplete);
                    startActivity(openActivityQuestionDays);
                    finish();
                }
            }, 2800); //delay is here
        }
    }

    /**
     * Checks if the answer is correct or no.
     *
     * @param isCheckedLayEggsAnswer1 is whether or not the correct answer is checked
     * @return value of score tracking variables
     */
    private int checkAnswer (boolean isCheckedLayEggsAnswer1) {
        if (isCheckedLayEggsAnswer1) {
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
