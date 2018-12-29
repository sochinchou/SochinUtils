package com.sochin.code.utils;

import java.io.File;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * 
 * @author Zhoushaoqing
 *
 */
public class FileItem implements Comparable<FileItem>{
	

	String mName = ""; // file name
	String mPath = ""; // file path
	String mSize = "";// file size	 
	boolean isFolder = false; // file type
	
	private boolean isChecked = false; // is checked in edit mode
	private boolean isIconSet = false;  // is the icon is defined
	Bitmap mIcon = null; //file icon
	
	String strAlias;
	
	public FileItem(){
		
	}

	public FileItem(String name, String path, String size, boolean type){
		this.mName = name;
		this.mPath = path;
		this.mSize = size;
		this.isFolder = type;
	}
	
	
	public FileItem(FileItem item){
		this(item.getName(), item.getPath(), item.getSize(), item.isFolder());
	}


	public void init(){
		mName = "";
		mPath = "";
		mSize = "";
		isFolder = false;
		
		isChecked = false;
		isIconSet = false;
		mIcon = null;
	}
	
	public void setAlias(String alias){
		this.strAlias = alias;
	}
	
	public String getAlias(){
		return this.strAlias;
	}
	
	/** whether the fileItem is Folder. true: folder, false : file */
	public boolean isFolder(){
		return isFolder;
	}
	
	/** set the file type, true: folder, false:file*/
	public void setIsFolder(boolean type){
		isFolder = type;
	}
	
	/** whether the fileItem is checked */
	public boolean isChecked(){
		return isChecked;
	}
	
	/** set the fileItem checked */
	public void setCheckedState(boolean state){
		isChecked = state;
	}

	/** to see whether the fileItem's icon is set */
	public boolean isIconSet(){
		return isIconSet;
	}
	
	/**  set the fileItem's icon state */
	public void setIconState(boolean state){
		isIconSet = state;
	}
	
	/** get the name of the fileItem */
	public String getName(){
		return mName;
	}
	
	/** set the fileItem's name */
	public void setName(String text){
		mName = text;
	}

	/** get the path of fileItem */
	public String getPath(){
		return mPath;
	}
	
	/** set the path of the fileItem */
	public void setPath(String path){
		mPath = path;
	}	
	
	/** get the size of the fileItem */
	public String getSize(){
		return mSize;
	}

	/**  set the size of the fileItem */
	public void setSize(String size){
		mSize = size;
	}
	
	/** get the icon of the fileItem */
	public Bitmap getIcon(){
		return mIcon;
	}	
	
	/** set the icon of the fileItem */
	public void setIcon(Bitmap icon){
		mIcon = icon;
	}
		
	public Uri getUri(){
		return Uri.fromFile(new File(mPath));
	}
	
	public int compareTo(FileItem other){
		if(isFolder && !other.isFolder()){
			return -1;
		}else if(!isFolder && other.isFolder()){
			return 1;
		}else{
			if( mName != null)
				return mName.compareTo(other.getName());
			else
				throw new IllegalArgumentException();
		}
		
	}


	
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FileItem){
			FileItem item = (FileItem)o;
			return mName.equals(item.mName) && (isFolder == item.isFolder);
		}else{
			return false;
		}

	}


	@Override
	public int hashCode() {
		return 0;
	}



	@Override
	public String toString() {
		return mName;
	}
	
	
}