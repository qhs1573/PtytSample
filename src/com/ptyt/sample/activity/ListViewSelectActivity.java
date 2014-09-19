package com.ptyt.sample.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.ptyt.sample.adapter.ListViewSelect;

public class ListViewSelectActivity extends ListActivity {
	private ListView mListView;
	private ListViewSelect listViewSelectAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewselect_activity);
		mListView = getListView();
		listViewSelectAdapter = new ListViewSelect(this);
		setListAdapter(listViewSelectAdapter);
		getListView().setTextFilterEnabled(true);
	    // Tell the list view to show one checked/activated item at a time.
	    getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    // Start with first item activated.
	    // Make the newly clicked item the currently selected one.
	    getListView().setItemChecked(0, true);
	}

	 @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // Make the newly clicked item the currently selected one.
        getListView().setItemChecked(position, true);
        Log.d("ygc", "position"+getListView().getCheckedItemPosition()+"position"+position);
    }
}
