package com.sdu.zhiji;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sdu.zhiji.dao.Answer;
import com.sdu.zhiji.dao.Question;
import com.sdu.zhiji.data.Data;
import com.sdu.zhiji.test.MBTItest;
import com.sdu.zhiji.widget.VerticalSeekBar;

import java.util.ArrayList;

public class mbti_TestQuestionActivity extends AppCompatActivity {

    public static final String MODE = "mode";
    public static final int MODE_SHORT = 0;
    public static final int MODE_LONG = 1;
    public static final int COUNT_SHORT = 21;
    public static final int COUNT_LONG = 70;

    private VerticalSeekBar sbAnswer;
    private TextView txtQuestion;
    private TextView txtAnswer1;
    private TextView txtAnswer2;
    private TextView txtA1Num;
    private TextView txtA2Num;
    private Button btnNext;

    private int a1;
    private int a2;
    private int pos;
    private int max;
    private int mode;
    private ArrayList<Answer> answers;
    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_test_question);

        mode = getIntent().getIntExtra(MODE, 0);
        switch(mode){
            case MODE_SHORT:
                max = COUNT_SHORT;
                break;
            case MODE_LONG:
                max = COUNT_LONG;
                break;
        }
        pos = 0;
        answers = new ArrayList<>();

        sbAnswer = (VerticalSeekBar)findViewById(R.id.test_answer_bar);
        txtQuestion = (TextView)findViewById(R.id.test_q);
        txtAnswer1 = (TextView)findViewById(R.id.test_a1);
        txtAnswer2 = (TextView)findViewById(R.id.test_a2);
        txtA1Num = (TextView)findViewById(R.id.test_a1_position);
        txtA2Num = (TextView)findViewById(R.id.test_a2_position);
        btnNext = (Button)findViewById(R.id.test_next);

        fillPage();

        sbAnswer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                a1 = sbAnswer.getProgress();
                a2 = 100 - a1;
                txtA1Num.setText(Integer.toString(a1));
                txtA2Num.setText(Integer.toString(a2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos < max-1) {
                    question = Data.questions[pos];
                    a1 = sbAnswer.getProgress();
                    a2 = 100 - a1;
                    answers.add(new Answer(question.getId(), a1, a2));
                    pos++;
                    fillPage();
                }else{
                    MBTItest.testing(answers);
                    Intent intent = new Intent(mbti_TestQuestionActivity.this, mbti_ResultActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void fillPage(){
        question = Data.questions[pos];
        sbAnswer.setProgress(50);
        txtAnswer1.setText("A. "+question.getA1());
        txtAnswer2.setText("B. "+question.getA2());
        txtQuestion.setText(question.getId()+") "+question.getQ());
    }

}
