package com.sochin.code.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * 
 * @author Zhoushaoqing
 * 
 */
public class FileUtils {

	private static final String TAG = "log";
	private static final boolean isLog = true;

	/** get available space from file */
	public static long getAvailableSpace(File file) {
		StatFs statFs = new StatFs(file.getAbsolutePath());
		int blockSize = statFs.getBlockSize();
		long nAvailable = (long) statFs.getAvailableBlocks() * (long) blockSize;
		return nAvailable;
	}

	/** get available space from filepath */
	public static long getAvailableSpace(String path) {
		StatFs statFs = new StatFs(path);
		int blockSize = statFs.getBlockSize();
		long nAvailable = (long) statFs.getAvailableBlocks() * (long) blockSize;
		return nAvailable;
	}
	
	/** get total space from file */
	public static long getTotalSpace(File file) {
		StatFs statFs = new StatFs(file.getAbsolutePath());
		int blockSize = statFs.getBlockSize();
		long nTotal = (long) statFs.getBlockCount() * (long) blockSize;
		return nTotal;
	}

	/** get total space from filepath*/
	public static long getTotalSpace(String path) {
		StatFs statFs = new StatFs(path);
		int blockSize = statFs.getBlockSize();
		long nTotal = (long) statFs.getBlockCount() * (long) blockSize;
		return nTotal;
	}
	
	/** check if sdcard is mounted */
	public static boolean isSDCardExist() {
		String status = Environment.getExternalStorageState();
		boolean isExist = status.equals(Environment.MEDIA_MOUNTED);
		log("isSDCardExist() >>>>> " + isExist);
		return isExist;
	}

	public static Bitmap getImage(String filePathName) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		Bitmap scaledBitmap = null;
		Bitmap iconBitmap = BitmapFactory.decodeFile(filePathName, options);

		if (iconBitmap != null) {
			scaledBitmap = Bitmap.createScaledBitmap(iconBitmap, 65, 50, false);
			iconBitmap.recycle();
		}

		return scaledBitmap;

	}

	/** Get filename from file path */
	public static String getFileName(String filePath) {
		String name = "";

		if (filePath != null) {
			int surfixPos = filePath.lastIndexOf("/");
			if (surfixPos > 0)
				name = filePath.substring(surfixPos + 1, filePath.length());
		}

		return name;
	}

	/** Get filename from file */
	public static String getFileName(File file) {
		String name = "";

		if (file != null)
			name = file.getName();

		return name;
	}

	/** Get file suffix from file path */
	public static String getFileSurfix(String filePath) {
		String surfix = "";

		if (filePath != null) {
			int surfixPos = filePath.lastIndexOf(".");
			int surfixPosDivider = filePath.lastIndexOf("/");
			// invalid : abcdefg.hijk/lmn
			if (surfixPos > 0 && surfixPos > surfixPosDivider)
				surfix = filePath.substring(surfixPos + 1, filePath.length());
		}

		return surfix.toLowerCase();
	}

	/** Get file suffix from file path */
	public static String getFileSurfix(File file) {
		String surfix = "";

		if (file != null) {
			String filePath = file.getAbsolutePath();
			surfix = getFileSurfix(filePath);
		}
		return surfix.toLowerCase();
	}

	
	/** Get file name without suffix from file path */
	public static String getFileNameWithoutSurfix(String filePath) {
		String surfix = "";

		if (filePath != null) {
			int surfixPos = filePath.lastIndexOf(".");
			int surfixPosDivider = filePath.lastIndexOf("/");
			// invalid : abcdefg.hijk/lmn
			if (surfixPos > 0 && surfixPos > surfixPosDivider)
				surfix = filePath.substring(surfixPosDivider + 1, surfixPos);
		}

		return surfix;
	}
	
	/** Get file name without suffix from file */
	public static String getFileNameWithoutSurfix(File file) {
		String surfix = "";

		if (file != null) {
			String filePath = file.getAbsolutePath();
			surfix = getFileNameWithoutSurfix(filePath);
		}

		return surfix;
	}
	
	public static String getfileTimeString(File f) {
		long nt = f.lastModified();
		String st = "";

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		st = dateFormat.format(new Date(nt));

		return st;
	}

	public static String getFileSizeString(long length) {

		double size = length;
		String unit = " B";

		size = size / 1024;
		if (size < 1024) {
			unit = " KB";
		} else {
			size = size / 1024;
			if (size < 1024) {
				unit = " MB";
			} else {
				size = size / 1024;
				unit = " GB";
			}
		}

		return String.format("%.1f", size) + unit;
	}

	/** get the file size in string, ie.B, kB, MB, GB */
	public static String getFileSizeString(File file) {
		if (file == null || file.isDirectory())
			return "";

		long length = file.length();

		double size = length;
		String unit = " B";

		size = size / 1024;
		if (size < 1024) {
			unit = " KB";
		} else {
			size = size / 1024;
			if (size < 1024) {
				unit = " MB";
			} else {
				size = size / 1024;
				unit = " GB";
			}
		}

		return String.format("%.1f", size) + unit;
	}

	public static boolean isMedia(File file) {
		return isAudio(file) || isImage(file) || isVideo(file);
	}

	public static boolean isMedia(FileItem item) {
		return isAudio(item) || isImage(item) || isVideo(item);
	}

	
	

	public static boolean isAudioIntheFolder(File folder){
		File[] files = folder.listFiles();
		if(files == null || files.length < 1)
			return false;
		
		for(File file : files){
			if(isAudio(file))
				return true;
		}
		
		return false;
	}
	

	public static boolean isVideoIntheFolder(File folder){
		File[] files = folder.listFiles();
		if(files == null || files.length < 1)
			return false;
		
		for(File file : files){
			if(isVideo(file))
				return true;
		}
		
		return false;
	}


	public static boolean isImageInTheFolder(File folder){
		File[] files = folder.listFiles();
		if(files == null || files.length < 1)
			return false;
		
		for(File file : files){
			if(isImage(file))
				return true;
		}
		
		return false;
	}
	
	
	public static ArrayList<String> getAudioList(File folder){
		ArrayList<String> list = new ArrayList<String>();
		File[] files = folder.listFiles();
		for (File file : files) {
			if (FileUtils.isAudio(file))
				list.add(file.getAbsolutePath());
		}
		return list;
	}

	public static ArrayList<String> getVideoList(File folder) {
		ArrayList<String> list = new ArrayList<String>();
		File[] files = folder.listFiles();
		for (File file : files) {
			if (FileUtils.isVideo(file))
				list.add(file.getAbsolutePath());
		}
		return list;
	}

	public static ArrayList<String> getImageList(File folder) {
		ArrayList<String> list = new ArrayList<String>();
		File[] files = folder.listFiles();
		for (File file : files) {
			if (FileUtils.isImage(file))
				list.add(file.getAbsolutePath());
		}
		return list;
	}

	/** whether the file is apk */
	public static boolean isApk(File file) {
		String surfix = getFileSurfix(file);
		return surfix.equals("apk");
	}

	/** whether the file is audio */
	public static boolean isAudio(File file) {
		String surfix = getFileSurfix(file);
		return (surfix.equals("mp3") || surfix.equals("wma")
				|| surfix.equals("ogg") || surfix.equals("wav") || (surfix
				.equals("ape") || surfix.equals("aac")));
	}

	/** whether the file is video */
	public static boolean isVideo(File file) {
		String surfix = getFileSurfix(file);
		return (surfix.equals("avi") || surfix.equals("mp4")
				|| surfix.equals("3gp") || surfix.equals("rmvb")
				|| surfix.equals("mov") || surfix.equals("wmv")
				|| surfix.equals("mpeg") || surfix.equals("mpg")
				|| surfix.equals("rm") || surfix.equals("vob")
				|| surfix.equals("mkv") || surfix.equals("flv"));
	}

	/** whether the file is image */
	public static boolean isImage(File file) {
		String surfix = getFileSurfix(file);
		return (surfix.equals("png") || surfix.equals("jpg")
				|| surfix.equals("jpeg") || surfix.equals("bmp") || surfix
					.equals("gif"));
	}

	/** whether the fileItem is apk */
	public static boolean isApk(FileItem file) {
		String surfix = getFileSurfix(file.getName());
		return surfix.equals("apk");
	}

	/** whether the fileItem is audio */
	public static boolean isAudio(FileItem file) {
		String suffix = getFileSurfix(file.getName());
		return (suffix.equals("mp3") || suffix.equals("wma")
				|| suffix.equals("ogg") || suffix.equals("wav")
				|| suffix.equals("ape") || suffix.equals("aac"));
	}

	/** whether the fileItem is video */
	public static boolean isVideo(FileItem file) {
		String surfix = getFileSurfix(file.getName());
		return (surfix.equals("avi") || surfix.equals("mp4")
				|| surfix.equals("3gp") || surfix.equals("rmvb")
				|| surfix.equals("mov") || surfix.equals("wmv")
				|| surfix.equals("mpeg") || surfix.equals("mpg")
				|| surfix.equals("rm") || surfix.equals("vob")
				|| surfix.equals("mkv") || surfix.equals("flv"));
	}

	/** whether the fileItem is image */
	public static boolean isImage(FileItem file) {
		String surfix = getFileSurfix(file.getName());
		return (surfix.equals("png") || surfix.equals("jpg")
				|| surfix.equals("jpeg") || surfix.equals("bmp") || surfix
					.equals("gif"));
	}

	public static String getMIMEType(FileItem item) {

		String fName = item.getName();
		String end = getFileSurfix(fName);

		if (end.equals("png") || end.equals("jpg") || end.equals("jpeg")
				|| end.equals("bmp") || end.equals("gif")) {
			return "image/*";
		} else if (end.equals("mp3") || end.equals("wma") || end.equals("ogg")
				|| end.equals("wav") || end.equals("ape") || end.equals("aac")) {
			return "audio/*";
		} else if (end.equals("avi") || end.equals("mp4") || end.equals("3gp")
				|| end.equals("rmvb") || end.equals("mov") || end.equals("wmv")
				|| end.equals("mpeg") || end.equals("mpg") || end.equals("rm")
				|| end.equals("vob") || end.equals("mkv") || end.equals("flv")) {
			return "video/*";
		} else if (end.equals("flv") || end.equals("swf")) {
			return "application/octet-stream";
		} else if (end.equals("htm") || end.equals("html")) {
			return "text/html";
		} else if (end.equals("txt")) {
			return "text/plain";
		} else if (end.equals("pdf")) {
			return "application/pdf";
		} else if (end.equals("doc") || end.equals("docx")) {
			return "application/msword";
		} else if (end.equals("ppt") || end.equals("pptx")) {
			return "application/vnd.ms-powerpoint";
		} else if (end.equals("xls") || end.equals("xlsx")) {
			return "application/vnd.ms-excel";
		} else if (end.equals("zip") || end.equals("rar")) {
			return "application/zip";
		} else if (end.equals("apk")) {
			return "application/vnd.android.package-archive";
		} else {
			return "*/*";
		}
	}

	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，返回null
	 * @param dirFile
	 * @param fileName
	 * @return
	 */
	public static File getFile(File dirFile, String fileName){
		File file = new File(dirFile, fileName);
		if(file.exists()){
			return file;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，返回null
	 * @param dirPath
	 * @param fileName
	 * @return
	 */
	public static File getFile(String dirPath, String fileName){
		File file = new File(dirPath, fileName);
		if(file.exists()){
			return file;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，返回null
	 * @param filePath
	 * @return
	 */
	public static File getFile(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			return file;
		}else{
			return null;
		}
	}
	
	
	/**
	 * 根据路径构建文件夹，如果对应文件夹不存在返回null
	 * @param folderPath
	 * @return
	 */
	public static File getFolder(String folderPath){
		log("getFolder(folderPath) >>> " + folderPath);
		if (folderPath == null) {
			log("getFolder(folderPath) >>> folderPath is null, return");
			return null;
		}
		
		File newFolder = new File(folderPath);
		
		if(!newFolder.exists()){
			return null;
			
		}else{ 
			
			if(!newFolder.isDirectory()){ 
				return null;
			}else{
				return newFolder;
			}
		}
	}

	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，则新建
	 * @param dirFile
	 * @param fileName
	 * @return
	 */
	public static File createFile(File dirFile, String fileName) {
		log("createFile(dirFile, fileName) >>>");
		if (dirFile == null || fileName == null) {
			log("createFile(dirFile, fileName) >>> dirFile or fileName is null, return");
			return null;
		}

		File newFile = new File(dirFile, fileName);
		if (!newFile.exists()) { 
			
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				log("createFile(dirFile, fileName) >>> [IOException]");
			}

			if (!newFile.exists()){
				
				if(!dirFile.exists()){
					if (!dirFile.mkdirs()){
						return null;
					}else{
						return createFile(dirFile, fileName);
					}
				}else{
					return null;
				}
				
			}else{
				return newFile;
			}
			
		} else { 
			return newFile;
		}
	}

	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，则新建
	 * @param dirPath
	 * @param fileName
	 * @return
	 */
	public static File createFile(String dirPath, String fileName) {
		log("createFile(dirPath, fileName) >>>");
		if (dirPath == null || fileName == null) {
			log("createFile(dirPath, fileName) >>> dirPath or fileName is null, return");
			return null;
		}

		File newFile = new File(dirPath, fileName);

		if (!newFile.exists()) { 

			try {
				newFile.createNewFile();
			} catch (IOException e) {
				log("createFile(dirPath, fileName) >>> [IOException]");
			}

			if (!newFile.exists()) {

				File dir = new File(dirPath);
				if (!dir.exists()) { 
					if (!dir.mkdirs()){
						return null;
					}else{
						return createFile(dir, fileName);
					}
				} else { 
					return createFile(dir, fileName);
				}
				
			} else { 
				return newFile;
			}

		} else { 
			return newFile;
		}
	}

	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，则新建
	 * @param filePath
	 * @return
	 */
	public static File createFile(String filePath) {
		log("createFile(filePath) >>> " + filePath);
		if (filePath == null) {
			log("createFile(filePath) >>> filePath is null, return");
			return null;
		}
		
		File newFile = new File(filePath);
		
		if (!newFile.exists()) {
			
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				log("createFile(filePath) >>> [IOException]");
			}
			
			if(!newFile.exists()){
				int pos = filePath.lastIndexOf("/");
				String dirPath = filePath.substring(0, pos);
				String fileName = filePath.substring(pos + 1);
				return createFile(dirPath, fileName);
			}else{ 
				return newFile;
			}
			
		} else {
			return newFile;
		}
	}

	/**
	 * 创建文件夹
	 * @param folderPath
	 * @return
	 */
	public static File createFolder(String folderPath){
		log("createFolder(folderPath) >>> " + folderPath);
		if (folderPath == null) {
			log("createFile(folderPath) >>> folderPath is null, return");
			return null;
		}
		
		File newFolder = new File(folderPath);
		
		if(!newFolder.exists()){
			
			newFolder.mkdirs();
			if(!newFolder.exists()){
				return null; 
			}else{
				return newFolder;
			}
			
		}else{ 
			
			if(!newFolder.isDirectory()){ 
				return null;
			}else{
				return newFolder;
			}
			
		}
		
	}

	/**
	 * 删除文件或文件夹
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		log("deleteFile(file) >>> " + file);
		if (file == null) {
			log("deleteFile(file) >>> file is null， return");
			return false;
		}
		
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files)
				deleteFile(f);
			return file.delete();
		} else {
			return file.delete();
		}
	}

	/**
	 * copy the content fo a file to another file
	 * 
	 * 
	 * @param source
	 * @param target
	 */
	public static void copyFileChannel(File source, File target) {
		log("copyFileChannel() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFileChannel() >>>>> source or target is null");
			return;
		}

		try {
			FileChannel inC = new FileInputStream(source).getChannel();
			FileChannel outC = new FileOutputStream(target).getChannel();
			outC.transferFrom(inC, 0, inC.size());
			outC.close();
			inC.close();
		} catch (FileNotFoundException e) {
			log("copyFileChannel() >>>>> FileNotFoundException");
		} catch (IOException e) {
			log("copyFileChannel() >>>>> IOException");
		}
	}

	/**
	 * copy all in the source folder to the target folder source is not included
	 * 
	 * @param source
	 * @param target
	 */

	public static void copyDirChannel(File source, File target) {
		log("copyDirChannel() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyDirChannel() >>>>> source or target is null");
			return;
		}

		if (!target.exists())
			target.mkdirs();

		File[] files = source.listFiles();

		for (File theFile : files) {

			File srcFile = theFile;
			File tarFile = new File(target, theFile.getName());

			if (theFile.isFile())
				copyFileChannel(srcFile, tarFile);

			if (theFile.isDirectory())
				copyDirChannel(srcFile, tarFile);
		}
	}

	/**
	 * copy the source to the target folder ,source is included
	 * 
	 * @param source
	 * @param target
	 */
	public static void copyDirToDir(File source, File target) {
		log("copyDirToDir() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyDirToDir() >>>>> source or target is null");
			return;
		}

		File src = source;
		File tar = new File(target, src.getName());
		copyDirChannel(src, tar);
	}

	/**
	 * copy a file to a folder
	 * 
	 * @param source
	 * @param target
	 */
	public static void copyFileToDir(File source, File target) {
		log("copyFileToDir() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFileToDir() >>>>> source or target is null");
			return;
		}

		File src = source;
		File tar = new File(target, src.getName());
		if (!tar.exists()) {
			try {
				tar.createNewFile();
			} catch (IOException e) {
				log("copyFileToDir() >>>>> IOException");
				return;
			}
		}
		copyFileChannel(src, tar);
	}

	
	/**
	 * zip file
	 * @param message
	 */
	public static void zipFile(String pathSrcFolder, String pathTargetZipFile){
		

	}
	
	
	public static void unzipFile(String pathSrcZipFile, String pathTargetFolder){
		Log.d(TAG, "unzipFile() >>> pathZipFile = " + pathSrcZipFile + " pathTargetFolder = " + pathTargetFolder);
		
		File srcZipFile = FileUtils.createFile(pathSrcZipFile);
		File targetFolder = FileUtils.createFolder(pathTargetFolder);
		
		if(srcZipFile != null && targetFolder != null){

			try {
				FileInputStream fis = new FileInputStream(srcZipFile);
				ZipInputStream zis = new ZipInputStream(fis);
				ZipEntry zipEntry = null;
				
				while ((zipEntry = zis.getNextEntry()) != null){
				
					FileOutputStream fos = new FileOutputStream(FileUtils.createFile(targetFolder,zipEntry.getName()));

					byte[] buffer = new byte[8192];
					int count = 0;
					while ((count = zis.read(buffer)) >= 0)
					{
						fos.write(buffer, 0, count);
					}
					zis.closeEntry(); 
					fos.close();
				}
				
				zis.close();
				
			} catch (FileNotFoundException e) {
				Log.d(TAG, "[FileNotFoundException]");
			} catch (IOException e) {
				Log.d(TAG, "[IOException]");
			}
			
		}else{
			Log.d(TAG, "unzipFile() >>> file is null");
		}
	}
	
	
	
	private static void log(String message) {
		if (isLog)
			Log.d(TAG, message);
	}

	private static void log(String tag, String message) {
		if (isLog)
			Log.d(tag, message);
	}

	public static long parseLong(String str) {
		if (str == null)
			return 0;

		long value = 0;
		try {
			value = Long.parseLong(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}

	public static int parseInt(String str) {
		if (str == null)
			return 0;

		int value = 0;
		try {
			value = Integer.parseInt(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}

	public static double parseDouble(String str) {
		if (str == null)
			return 0;

		double value = 0;
		try {
			value = Double.parseDouble(str);
		} catch (NumberFormatException e) {

		}
		return value;
	}

}