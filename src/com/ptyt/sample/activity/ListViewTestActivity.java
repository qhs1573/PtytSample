package com.ptyt.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ptyt.sample.adapter.ListAdapter;
import com.ptyt.sample.bean.Person;
import com.ptyt.sample.utils.PrintLog;

public class ListViewTestActivity extends BaseActivity implements OnItemClickListener{
    private ListView mListView;
    private ListAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Person person =  (Person) getIntent().getSerializableExtra("person");
        String str = getIntent().getStringExtra("str");
        PrintLog.d("YGC", person.toString()+"---------->>"+person.getTeacher().toString()+"   "+str);
        mListView = (ListView)findViewById(R.id.lv_list_sample);
        mAdapter = new  ListAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }
     
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  
        mAdapter.changeImageVisable(view, position);  
    }  
}
