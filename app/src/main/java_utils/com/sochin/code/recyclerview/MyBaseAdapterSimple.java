package com.sochin.code.recyclerview;


import java.util.ArrayList;
import java.util.List;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sochin.R;



public class MyBaseAdapterSimple extends BaseAdapter{

	private Context mContext;
	private List<ItemInfo> mListItem;
	private int mSelectedPosition;
	
	public MyBaseAdapterSimple(Context context){
		this.mContext = context;
		this.mListItem = new ArrayList<ItemInfo>();
		mSelectedPosition = -1;
	}

	public MyBaseAdapterSimple(Context context, List<ItemInfo> listItem){
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
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
//		View itemView = convertView;
		ViewWrapper wrapper = null;
		
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_simple_adapter, parent, false);
			wrapper = new ViewWrapper();
			wrapper.mTxtName = (TextView)convertView.findViewById(R.id.txtName);
			wrapper.mTxtValue = (TextView)convertView.findViewById(R.id.txtValue);
			convertView.setTag(wrapper);
		}else{
			wrapper = (ViewWrapper)convertView.getTag();
		}
		
		
		ItemInfo theItem = getItem(position);
		
		wrapper.mTxtName.setText(theItem.mName);
		wrapper.mTxtValue.setText(String.valueOf(theItem.mValue));
//		wrapper.mImgIcon.setImageDrawable(theItem.mIcon);
	
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
		TextView mTxtName = null;
		TextView mTxtValue = null;
		ImageView mImgIcon = null;
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


