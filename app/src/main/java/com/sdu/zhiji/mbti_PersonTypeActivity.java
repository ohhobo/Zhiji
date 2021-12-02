package com.sdu.zhiji;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class mbti_PersonTypeActivity extends AppCompatActivity {

    public static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_person_type);
        int id = getIntent().getIntExtra(ID, 0);
        ((TextView)findViewById(R.id.person_title)).setText(getResources()
                .getStringArray(R.array.persons_title)[id]);
        ((TextView)findViewById(R.id.person_text)).setText(getResources()
                .getStringArray(R.array.persons_text)[id]);
    }
}
