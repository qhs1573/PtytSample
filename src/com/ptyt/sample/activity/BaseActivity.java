package com.ptyt.sample.activity;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.ptyt.sample.annotation.EventListener;
import com.ptyt.sample.annotation.Select;
import com.ptyt.sample.annotation.ViewInject;

@SuppressLint("NewApi") 
public abstract class BaseActivity extends Activity {
	private static final String TAG = "BaseActivity";
	private boolean isShowActionBar = true;
	private LinearLayout rootView;
	private TextView mTextView;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void setContentView(int layoutResID) {
		if(!isShowActionBar){
			super.setContentView(layoutResID);
		}else{
			super.setContentView(R.layout.base_activity);
			rootView = (LinearLayout) findViewById(R.id.ll_rootview);
			mTextView = (TextView) findViewById(R.id.tv_base_title);
			View view = LayoutInflater.from(this).inflate(layoutResID, null);
			rootView.removeAllViews();
			rootView.addView(view);
		}
		initView();
	}


	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		initView();
	}

	public void setContentView(View view) {
		super.setContentView(view);
		initView();
	}

	private void initView(){
		Field[] fields = getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				ViewInject viewInject = field.getAnnotation(ViewInject.class);
				if(viewInject!=null){
					int viewId = viewInject.id();
					try {
						field.setAccessible(true);
						field.set(this,findViewById(viewId));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String clickMethod = viewInject.click();
					if(!TextUtils.isEmpty(clickMethod))
						setViewClickListener(field,clickMethod);
					
					String longClickMethod = viewInject.longClick();
					if(!TextUtils.isEmpty(longClickMethod))
						setViewLongClickListener(field,longClickMethod);
					
					String itemClickMethod = viewInject.itemClick();
					if(!TextUtils.isEmpty(itemClickMethod))
						setItemClickListener(field,itemClickMethod);
					
					String itemLongClickMethod = viewInject.itemLongClick();
					if(!TextUtils.isEmpty(itemLongClickMethod))
						setItemLongClickListener(field,itemLongClickMethod);
					
					String itemHoverClickMethod = viewInject.hover();
					if(!TextUtils.isEmpty(itemHoverClickMethod))
					setItemHoverClickListener(field,itemHoverClickMethod);
					
					String itemonDragMethod = viewInject.OnDrag();
					if(!TextUtils.isEmpty(itemonDragMethod)){
						setItemOnDragListener(field,itemHoverClickMethod);
					}
					
					Select select = viewInject.select();
					if(!TextUtils.isEmpty(select.selected()))
						setViewSelectListener(field,select.selected(),select.noSelected());
					
				}
			}
		}
	}
	
	
	private void setItemOnDragListener(Field field, String itemDragMethod) {
		try {
			Object obj = field.get(this);
			if(obj instanceof View){
				((View)obj).setOnDragListener(new EventListener(this).itemDrag(itemDragMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void setViewClickListener(Field field,String clickMethod){
		try {
			Object obj = field.get(this);
			if(obj instanceof View){
				((View)obj).setOnClickListener(new EventListener(this).click(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setViewLongClickListener(Field field,String clickMethod){
		try {
			Object obj = field.get(this);
			if(obj instanceof View){
				((View)obj).setOnLongClickListener(new EventListener(this).longClick(clickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setItemClickListener(Field field,String itemClickMethod){
		try {
			Object obj = field.get(this);
			if(obj instanceof AbsListView){
				((AbsListView)obj).setOnItemClickListener(new EventListener(this).itemClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setItemLongClickListener(Field field,String itemClickMethod){
		try {
			Object obj = field.get(this);
			if(obj instanceof AbsListView){
				((AbsListView)obj).setOnItemLongClickListener(new EventListener(this).itemLongClick(itemClickMethod));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setItemHoverClickListener(Field field,String itemHoverClickMethod) {
		try {
			Object obj = field.get(this);
			if(obj instanceof View){
				((View)obj).setOnHoverListener((new EventListener(this).itemHover(itemHoverClickMethod)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setViewSelectListener(Field field,String select,String noSelect){
		try {
			Object obj = field.get(this);
			if(obj instanceof View){
				((AbsListView)obj).setOnItemSelectedListener(new EventListener(this).select(select).noSelect(noSelect));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TextView getmTextView() {
		return mTextView;
	}

	public void setShowActionBar(boolean isShowActionBar) {
		this.isShowActionBar = isShowActionBar;
	}
}
