package com.sdu.zhiji;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdu.zhiji.data.Data;

public class mbti_DescriptorActivity extends AppCompatActivity {

    public static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_descriptor);
        int id = getIntent().getIntExtra(ID, 0);
        ((ImageView)findViewById(R.id.descriptor_image)).setImageResource(Data.descriptorCards[id].getImg());
        ((TextView)findViewById(R.id.descriptor_title)).setText(getResources()
                .getStringArray(R.array.descriptor_title)[id]);
        ((TextView)findViewById(R.id.descriptor_text)).setText(getResources()
                .getStringArray(R.array.descriptor_text)[id]);
    }
}
