package JieDongZ.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_CHEAT = 0;

    private TextView mTextView;
    private TextView ScoreTextView;
    private EditText mEditText;


    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankContainer;
    private LinearLayout mMultipleChoiceContainer;


    private Button mCheckButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mHintButton;

    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private ImageButton mMultiButton;


    private Question[] mQuestions;
    private int mIndex;
    private int mScore;

    private Button mCheatButton;
    private boolean mCheated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mCheckButton = (Button) findViewById(R.id.check_button);
        mAButton = (Button) findViewById(R.id.a_button);
        mBButton = (Button) findViewById(R.id.b_button);
        mCButton = (Button) findViewById(R.id.c_button);
        mDButton = (Button) findViewById(R.id.d_button);
        mMultiButton = (ImageButton) findViewById(R.id.Multi_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankContainer = (LinearLayout) findViewById(R.id.fill_the_blank_container);
        mMultipleChoiceContainer = (LinearLayout) findViewById(R.id.multiple_choice_container);

        mEditText = (EditText) findViewById(R.id.edit_text);

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mHintButton = (Button) findViewById(R.id.hint_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);

        mAButton.setOnClickListener(this);
        mBButton.setOnClickListener(this);
        mCButton.setOnClickListener(this);
        mDButton.setOnClickListener(this);
        mMultiButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);
        ScoreTextView = (TextView) findViewById(R.id.score_view);

        //array
        mQuestions = new Question[7];
        mIndex = 0;

        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, R.string.question_1_hint, true);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, R.string.question_2_hint, true);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3, R.string.question_3_hint, true);
        mQuestions[3] = new TrueFalseQuestion(R.string.question_4, R.string.question_4_hint, true);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5, R.string.question_5_hint, true);


        String[] q6Answers = getResources().getStringArray(R.array.question_6_answer);
        mQuestions[5] = new FillTheBlankQuestion(R.string.question_6, R.string.question_6_hint, q6Answers);

        mQuestions[6] = new MultipleChoiceQuestion(R.string.question_7, R.string.question_7_hint, R.array.question_7_answer, 4);

        //setup the first question.
        setupQuestion();
        ScoreTextView.setText("Score" + mScore);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }

        if (requestCode ==REQUEST_CODE_CHEAT && resultData != null)
        {
            mCheated = CheatActivity.didCheat(resultData);
        }
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button) {
            checkAnswer(true);
            mTrueButton.setClickable(false);
            mFalseButton.setClickable(false);
        } else if (view.getId() == R.id.false_button) {
            checkAnswer(false);
            mTrueButton.setClickable(false);
            mFalseButton.setClickable(false);
        } else if (view.getId() == R.id.check_button) {
            checkAnswer(mEditText.getText().toString());
        } else if (view.getId() == R.id.next_button) {
            //change the variable by one
            mIndex++;
            mTrueButton.setClickable(true);
            mFalseButton.setClickable(true);
            mAButton.setClickable(true);
            mBButton.setClickable(true);
            mCButton.setClickable(true);
            mDButton.setClickable(true);
            mMultiButton.setClickable(true);
        }
        //DO IF STATEMENT HERE:

        else if (view.getId() == R.id.previous_button)
        {
            mIndex--;
            mTrueButton.setClickable(true);
            mFalseButton.setClickable(true);
            mAButton.setClickable(true);
            mBButton.setClickable(true);
            mCButton.setClickable(true);
            mDButton.setClickable(true);
            mMultiButton.setClickable(true);
        }

        else if (view.getId() == R.id.a_button)
        {
            checkAnswer(0);
            mAButton.setClickable(false);
            mBButton.setClickable(false);
            mCButton.setClickable(false);
            mDButton.setClickable(false);
        }

        else if (view.getId() == R.id.b_button)
        {
            checkAnswer(1);
            mAButton.setClickable(false);
            mBButton.setClickable(false);
            mCButton.setClickable(false);
            mDButton.setClickable(false);
        }

        else if (view.getId() == R.id.c_button)
        {
            checkAnswer(2);
            mAButton.setClickable(false);
            mBButton.setClickable(false);
            mCButton.setClickable(false);
            mDButton.setClickable(false);
        }

        else if (view.getId() == R.id.d_button)
        {
            checkAnswer(3);
            mAButton.setClickable(false);
            mBButton.setClickable(false);
            mCButton.setClickable(false);
            mDButton.setClickable(false);
        }

        else if (view.getId() == R.id.Multi_button)
        {
            checkAnswer(4);
            mAButton.setClickable(false);
            mBButton.setClickable(false);
            mCButton.setClickable(false);
            mDButton.setClickable(false);
            mMultiButton.setClickable(false);

        }

        else if (view.getId() ==R.id.cheat_button)
        {
            //launch cheat
            Intent cheatIntent = CheatActivity.newIntent(this, mQuestions[mIndex]);
            startActivityForResult(cheatIntent, REQUEST_CODE_CHEAT);

        }

        else if (view.getId() == R.id.hint_button)
        {
            Toast myToast = Toast.makeText(this, mQuestions[mIndex].getHintTextResId(), Toast.LENGTH_LONG);
            myToast.show();
        }
        //change text in view
        setupQuestion();

        if (mIndex > mQuestions.length || mIndex < 0) {
            mIndex = 0;
        }
    }

    private boolean checkAnswer(String userInput)
    {
        if(mCheated)
        {
            Toast.makeText(this,R.string.cheat_shame, Toast.LENGTH_LONG).show();
            return false;
        }
        else if (mQuestions[mIndex].checkAnswer(userInput))
        {
            mScore++;
            ScoreTextView.setText("Score" + mScore);

            Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        } else
            {
            mScore--;
            ScoreTextView.setText("Score" + mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }

    }

    public void setupQuestion() {
        mTextView.setText(mQuestions[mIndex].getTextResId());

        if (mQuestions[mIndex].isTrueFalseQuestion()) {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.GONE);

        } else if (mQuestions[mIndex].isFillTheBlankQuestion()) {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.VISIBLE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        } else if (mQuestions[mIndex].isMultipleChoiceQuestion()) {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);

            int optionsResId = ((MultipleChoiceQuestion) mQuestions[mIndex]).getOptionsResId();
            String[] options = getResources().getStringArray(optionsResId);

            mAButton.setText(options[0]);
            mBButton.setText(options[1]);
            mCButton.setText(options[2]);
            mDButton.setText(options[3]);


        }
    }


    public boolean checkAnswer(boolean userInput)
    {
        if(mCheated)
        {
            Toast.makeText(this,R.string.cheat_shame, Toast.LENGTH_LONG).show();
            return false;
        }

        else if (mQuestions[mIndex].checkAnswer(userInput)) {
            mScore++;
            ScoreTextView.setText("Score" + mScore);

            Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        } else {
            mScore--;
            ScoreTextView.setText("Score" + mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }

    }

    public boolean checkAnswer(int userInput)
    {
        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            mScore++;
            ScoreTextView.setText("Score" + mScore);

            Toast myToast = Toast.makeText(this, "You are correct", Toast.LENGTH_SHORT);
            myToast.show();
            return true;
        } else {
            mScore--;
            ScoreTextView.setText("Score" + mScore);
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.show();
            return false;
        }




    }


}








