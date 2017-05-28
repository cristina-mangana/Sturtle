package com.example.android.sturtle;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import rjsv.circularview.CircleView;

public class ResultActivity extends AppCompatActivity {

    int totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("playerName");
        int correct = extras.getInt("correct");
        int incorrect = extras.getInt("incorrect");
        int incomplete = extras.getInt("incomplete");

        // Set the font's path
        String fontPathRobotoMedium = "fonts/Roboto-Medium.ttf";
        String fontPathRobotoRegular = "fonts/Roboto-Regular.ttf";
        String fontPathRobotoBold = "fonts/Roboto-Bold.ttf";
        // Get the id TextView
        TextView textViewNumberOfCorrect = (TextView) findViewById(R.id.number_of_correct);
        TextView textViewNumberOfIncorrect = (TextView) findViewById(R.id.number_of_incorrect);
        TextView textViewNumberOfIncomplete = (TextView) findViewById(R.id.number_of_incomplete);
        TextView textViewCorrect = (TextView) findViewById(R.id.correct_textView);
        TextView textViewIncorrect = (TextView) findViewById(R.id.incorrect_textView);
        TextView textViewIncomplete = (TextView) findViewById(R.id.incomplete_textView);
        TextView textViewYourScore = (TextView) findViewById(R.id.your_score);
        TextView textViewMessage = (TextView) findViewById(R.id.score_message);
        // Load the font as a TypeFace object
        Typeface typeFaceRobotoMedium = Typeface.createFromAsset(getAssets(), fontPathRobotoMedium);
        Typeface typeFaceRobotoRegular = Typeface.createFromAsset(getAssets(), fontPathRobotoRegular);
        Typeface typeFaceRobotoBold = Typeface.createFromAsset(getAssets(), fontPathRobotoBold);
        // Set the extra fancy font in the customFont TextView
        textViewNumberOfCorrect.setTypeface(typeFaceRobotoMedium);
        textViewNumberOfIncorrect.setTypeface(typeFaceRobotoMedium);
        textViewNumberOfIncomplete.setTypeface(typeFaceRobotoMedium);
        textViewCorrect.setTypeface(typeFaceRobotoRegular);
        textViewIncorrect.setTypeface(typeFaceRobotoRegular);
        textViewIncomplete.setTypeface(typeFaceRobotoRegular);
        textViewYourScore.setTypeface(typeFaceRobotoBold);
        textViewMessage.setTypeface(typeFaceRobotoRegular);

        // Set scores
        textViewNumberOfCorrect.setText(String.valueOf(correct));
        textViewNumberOfIncorrect.setText(String.valueOf(incorrect));
        textViewNumberOfIncomplete.setText(String.valueOf(incomplete));

        totalScore = calculateScore(correct, incomplete);
        final CircleView circleView = (CircleView) findViewById(R.id.circle_view);
        circleView.setProgressValue(totalScore);
        // TODO change format number

        // Set score message
        if (totalScore > 5) {
            textViewMessage.setText(getString(R.string.awesome_score, playerName));
        } else {
            textViewMessage.setText(getString(R.string.improve_score, playerName));
        }
    }

    /**
     * Calculate the total score over 10.
     *
     * @param correct is the number of correct answers
     * @param incomplete is the number of incomplete answers
     * @return score
     */
    private int calculateScore (int correct, int incomplete) {
        return 2*correct + incomplete;
    }

    // This method is called when share button is clicked
    public void share (View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shared_text, totalScore));
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    // This method is called when try again button is clicked
    public void tryAgain (View view) {
        finish();
    }
}
