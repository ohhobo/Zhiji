package com.sdu.zhiji;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdu.zhiji.app.DBHelper;
import com.sdu.zhiji.data.ResultObject;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

public class mbti_ResultActivity extends AppCompatActivity {

    public static final String MODE = "mode";
    public static final int MODE_NEW = 0;
    public static final int MODE_OLD = 1;

    private GraphView graph;
    private TextView txtType;
    private EditText edtUser;
    private Button btnPage;
    private Button btnSave;
    private Button btnLead;
    private Button btnDetail;
    private LinearLayout layoutSave;

    private DBHelper dbHelper;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_result);

        txtType = (TextView)findViewById(R.id.result_type);
        edtUser = (EditText)findViewById(R.id.result_user);
        graph = (GraphView)findViewById(R.id.result_graph);
        btnPage = (Button)findViewById(R.id.result_page);
        btnSave = (Button)findViewById(R.id.result_save);
        btnDetail = (Button)findViewById(R.id.result_detail);
        layoutSave = (LinearLayout)findViewById(R.id.result_save_block);

        dbHelper = new DBHelper(this);
        mode = getIntent().getIntExtra(MODE, 0);

        if(mode == MODE_OLD){
            layoutSave.setVisibility(View.GONE);
        }

        btnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mbti_ResultActivity.this, mbti_PersonTypeActivity.class);
                intent.putExtra(mbti_DescriptorActivity.ID, ResultObject.result.page);
                startActivity(intent);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("e", ResultObject.result.resValue[0][0]);
                cv.put("i", ResultObject.result.resValue[0][1]);
                cv.put("s", ResultObject.result.resValue[1][0]);
                cv.put("n", ResultObject.result.resValue[1][1]);
                cv.put("t", ResultObject.result.resValue[2][0]);
                cv.put("f", ResultObject.result.resValue[2][1]);
                cv.put("j", ResultObject.result.resValue[3][0]);
                cv.put("p", ResultObject.result.resValue[3][1]);
                cv.put("page", ResultObject.result.page);
                cv.put("person", ResultObject.result.type);
                cv.put("user", edtUser.getText().toString());
                long newRowId = db.insert(DBHelper.TABLE_RESULTS, null, cv);
                layoutSave.setVisibility(View.GONE);
            }
        });
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mbti_ResultActivity.this, mbti_DetailResultActivity.class);
                startActivity(intent);
            }
        });

        txtType.setText(ResultObject.result.type);

        int[][] points = ResultObject.result.resValue;

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-100);
        graph.getViewport().setMaxX(100);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-100);
        graph.getViewport().setMaxY(100);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(Math.abs(value), isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(Math.abs(value), isValueX);
                }
            }
        });
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(-points[0][0], points[0][1]),
                new DataPoint(points[1][0], points[1][1]),
                new DataPoint(points[2][0], -points[2][1]),
                new DataPoint(-points[3][0], -points[3][1]),
                new DataPoint(-points[0][0], points[0][1])
        });
        series.setShape(PointsGraphSeries.Shape.POINT);
        graph.addSeries(series);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(-points[0][0], points[0][1]),
                new DataPoint(points[1][0], points[1][1])
        });
        series2.setColor(Color.RED);
        graph.addSeries(series2);
        if(points[1][0]<points[2][0]){
            series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(points[1][0], points[1][1]),
                    new DataPoint(points[2][0], -points[2][1])
            });
        }else{
            series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(points[2][0], -points[2][1]),
                    new DataPoint(points[1][0], points[1][1]),
            });
        }
        series2.setColor(Color.RED);
        graph.addSeries(series2);
        series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(-points[3][0], -points[3][1]),
                new DataPoint(points[2][0], -points[2][1])
        });
        series2.setColor(Color.RED);
        graph.addSeries(series2);
        if(-points[3][0]<-points[0][0]){
            series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(-points[3][0], -points[3][1]),
                    new DataPoint(-points[0][0], points[0][1])
            });
        }else{
            series2 = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(-points[0][0], points[0][1]),
                    new DataPoint(-points[3][0], -points[3][1])
            });
        }
        series2.setColor(Color.RED);
        graph.addSeries(series2);
    }
}
