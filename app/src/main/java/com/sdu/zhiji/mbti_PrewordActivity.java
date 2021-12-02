package com.sdu.zhiji;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class mbti_PrewordActivity extends AppCompatActivity {

    private Button btnNext;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_preword);
        mode = getIntent().getIntExtra(mbti_TestQuestionActivity.MODE, 0);

        btnNext = (Button)findViewById(R.id.preword_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mbti_PrewordActivity.this, mbti_TestQuestionActivity.class);
                intent.putExtra(mbti_TestQuestionActivity.MODE, mode);
                startActivity(intent);
                finish();
            }
        });
    }
}
