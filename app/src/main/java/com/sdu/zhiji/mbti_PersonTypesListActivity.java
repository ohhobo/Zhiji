package com.sdu.zhiji;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class mbti_PersonTypesListActivity extends AppCompatActivity {

    private ListView lstPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_person_types_list);

        lstPersons = (ListView)findViewById(R.id.persons_list);
        ArrayAdapter<String> persons = new ArrayAdapter<>(this
                , android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.persons_title));
        lstPersons.setAdapter(persons);
        setListViewHeightBasedOnChildren(lstPersons);

        lstPersons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mbti_PersonTypesListActivity.this, mbti_PersonTypeActivity.class);
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
