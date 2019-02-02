package com.sochin.code.recyclerview;


import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sochin.R;


public class MyBaseAdapter extends BaseAdapter{

	private Context mContext;
	private List<ItemInfo> mListItem;
	private int mSelectedPosition;
	
	public MyBaseAdapter(Context context){
		this.mContext = context;
		this.mListItem = new ArrayList<ItemInfo>();
		mSelectedPosition = -1;
	}

	public MyBaseAdapter(Context context, List<ItemInfo> listItem){
		this.mContext = context;
		this.mListItem = listItem;
		mSelectedPosition = -1;
	}
	
	@Override
	public int getCount(){
		
		if(mListItem != null){
			return mListItem.size();
		}else{
			return 0;
		}
	}
	
	@Override
	public ItemInfo getItem(int position){
		
		if(mListItem != null){
			
			if(position < 0 || position > mListItem.size() -1){
				return null;
			}
			
			return mListItem.get(position);
		}else{
			return null;
		}
	}
	
	@Override
	public long getItemId(int position){
		return position;
	}
	

	public View getView(int position, View convertView, ViewGroup parent){
//		View itemView = convertView;
		ViewWrapper wrapper = null;

		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_adapter, parent, false);
			wrapper = new ViewWrapper(convertView);
			convertView.setTag(wrapper);
			
			wrapper.getCheck().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Integer position = (Integer)buttonView.getTag();
					ItemInfo item = getItem(position);
					item.setChecked(isChecked);
				}
			});	

		}else{
			wrapper = (ViewWrapper)convertView.getTag();
		}
		
		wrapper.getCheck().setTag(Integer.valueOf(position));
		
		ItemInfo theItem = getItem(position);
		wrapper.getNameText().setText(theItem.mName);
		wrapper.getValueText().setText(String.valueOf(theItem.mValue));
		wrapper.getCheck().setChecked(theItem.isChecked);

		if(position == mSelectedPosition){
			convertView.setBackgroundColor(0xFFFF7800);
		}else{
			convertView.setBackgroundColor(Color.TRANSPARENT);
		}
		return convertView;
	}
		
	


	// ************************************************************
	// wrapper
	// ************************************************************
	private class ViewWrapper{
		View base;
		TextView mTxtName = null;
		TextView mTxtValue = null;
		CheckBox mCheck = null;


		ViewWrapper(View base){
			this.base = base;
		}

		TextView getNameText(){
			if(mTxtName == null){
				mTxtName = (TextView)base.findViewById(R.id.txtName);
			}
			return mTxtName;
		}

		TextView getValueText(){
			if(mTxtValue == null){
				mTxtValue = (TextView)base.findViewById(R.id.txtValue);
			}
			return mTxtValue;
		}

		CheckBox getCheck(){
			if(mCheck == null){
				mCheck = (CheckBox)base.findViewById(R.id.chk1);
			}
			return mCheck;
		}
	}
	
	
	// ************************************************************
	// Methods
	// ************************************************************
	public int getSelectedPosition(){
		return mSelectedPosition;
	}
	
	public void setSelectedPosition(int position){
		this.mSelectedPosition = position;
	}
	
	public List<ItemInfo> getList(){
		return mListItem;
	}
	
	public void setList(List<ItemInfo> list){
		this.mListItem = list;
	}
	
	
	//===================   item
	public ItemInfo getSelectedItem(){
		
		if(mListItem != null){
			
			if(mSelectedPosition < 0 || mSelectedPosition > mListItem.size() -1){
				return null;
			}
			
			return mListItem.get(mSelectedPosition);
		}else{
			return null;
		}
	}
	
	public void addItem(ItemInfo item){
		if(mListItem != null){
			mListItem.add(item);
		}
	}

	
	public void removeItem(ItemInfo item){
		if(mListItem != null){
			mListItem.remove(item);
		}
	}
	
	public void removeItem(int position){
		
		if(mListItem != null){
			
			if (position < 0 || position > mListItem.size() - 1){
				return;
			}
			
			mListItem.remove(position);
		}
	}
	
	public void clearItem(){
		if(mListItem != null){
			mListItem.clear();
		}
	}
	
}


