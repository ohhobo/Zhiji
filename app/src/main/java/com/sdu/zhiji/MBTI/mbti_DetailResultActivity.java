package com.sdu.zhiji.MBTI;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.sdu.zhiji.R;
import com.sdu.zhiji.data.ResultObject;

public class mbti_DetailResultActivity extends AppCompatActivity {

    private TextView txtE;
    private TextView txtI;
    private TextView txtS;
    private TextView txtN;
    private TextView txtT;
    private TextView txtF;
    private TextView txtJ;
    private TextView txtP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_detail_result);
        txtE = (TextView)findViewById(R.id.detail_e);
        txtI = (TextView)findViewById(R.id.detail_i);
        txtS = (TextView)findViewById(R.id.detail_s);
        txtN = (TextView)findViewById(R.id.detail_n);
        txtT = (TextView)findViewById(R.id.detail_t);
        txtF = (TextView)findViewById(R.id.detail_f);
        txtJ = (TextView)findViewById(R.id.detail_j);
        txtP = (TextView)findViewById(R.id.detail_p);

        txtE.setText(ResultObject.result.resValue[0][0]+"%");
        txtI.setText(ResultObject.result.resValue[0][1]+"%");
        txtS.setText(ResultObject.result.resValue[1][0]+"%");
        txtN.setText(ResultObject.result.resValue[1][1]+"%");
        txtT.setText(ResultObject.result.resValue[2][0]+"%");
        txtF.setText(ResultObject.result.resValue[2][1]+"%");
        txtJ.setText(ResultObject.result.resValue[3][0]+"%");
        txtP.setText(ResultObject.result.resValue[3][1]+"%");
    }
}
