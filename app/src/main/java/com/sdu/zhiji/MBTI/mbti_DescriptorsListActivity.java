package com.sdu.zhiji.MBTI;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdu.zhiji.R;

public class mbti_DescriptorsListActivity extends AppCompatActivity {

    private ListView lstDescriptors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_descriptors);

        lstDescriptors = (ListView)findViewById(R.id.descriptors_list);
        ArrayAdapter<String> descriptors = new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.descriptor_title));
        lstDescriptors.setAdapter(descriptors);
        setListViewHeightBasedOnChildren(lstDescriptors);

        lstDescriptors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mbti_DescriptorsListActivity.this, mbti_DescriptorActivity.class);
                intent.putExtra(mbti_DescriptorActivity.ID, i);
                startActivity(intent);
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter listAdapter = (ArrayAdapter) listView.getAdapter();

        int totalHeight = 0;
        // проходимся по элементам коллекции
        for(int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // получаем высоту
            totalHeight += listItem.getMeasuredHeight();
        }

        // устанавливаем новую высоту для контейнера
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
