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
import android.widget.ImageView;
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
	

	public View getView(int position, View convertView, ViewGroup parent){
		View itemView = convertView;
		ViewWrapper wrapper = null;
		
		if(itemView == null){
			itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_simple_adapter, parent, false);
			wrapper = new ViewWrapper(itemView);
			itemView.setTag(wrapper);
		}else{
			wrapper = (ViewWrapper)itemView.getTag();
		}
		
		
		ItemInfo theItem = getItem(position);
		
		wrapper.getNameText().setText(theItem.mName);
		wrapper.getValueText().setText(theItem.mValue);
		wrapper.getIcon().setImageDrawable(theItem.mIcon);
	
		if(position == mSelectedPosition){
			itemView.setBackgroundColor(0xFFFF7800);
		}else{
			itemView.setBackgroundColor(Color.TRANSPARENT);
		}
		return itemView;
	}
		
	


	// ************************************************************
	// wrapper
	// ************************************************************
	private class ViewWrapper{
		View base;
		TextView mTxtName = null;
		TextView mTxtValue = null;
		ImageView mImgIcon = null;

		
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
		
		ImageView getIcon(){
			if(mImgIcon == null){
				mImgIcon = (ImageView)base.findViewById(R.id.imgIcon);
			}
			return mImgIcon;
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



	// ************************************************************
	// ItemInfo
	// ************************************************************
	private class ItemInfo{
	    public String mName="";
	    public String mValue="";
	    public Drawable mIcon=null;
	    
		@Override
		public boolean equals(Object o) {
			ItemInfo info = (ItemInfo)o;
			return mValue.equals(info.mValue);
		}
		
		@Override
		public int hashCode() {
			return 0;
		}   
	}
	
	
}


