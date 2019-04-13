package com.sochin.code.utils;

import java.io.File;
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

	private static final String TAG = "FileUtils";
	private static final boolean isLog = true;



	//************************************************************
	//             file name operation
	//************************************************************
	/** Get filename from file path */
	public static String getFileName(String filePath) {
		String name = "";
		if (filePath != null) {
			int slashPos = filePath.lastIndexOf("/");
			if (slashPos > 0) {
				name = filePath.substring(slashPos + 1);
			}else{
				log("getFileName(filePath) >>> filePath is invalid");
			}
		}else{
			log("getFileName(filePath) >>> filePath is null");
		}
		return name;
	}

	/** Get filename from file */
	public static String getFileName(File file) {
		String name = "";
		if (file != null) {
			name = file.getName();
		}else{
			log("getFileName(file) >>> file is null");
		}
		return name;
	}

	/** Get file suffix from file path */
	public static String getFileSuffix(String filePath) {
		String suffix = "";
		if (filePath != null) {
			int dotPos = filePath.lastIndexOf(".");
			int slashPos = filePath.lastIndexOf("/");
			// invalid : abcdefg.hijk/lmn
			if (dotPos > 0 && dotPos > slashPos) {
				suffix = filePath.substring(dotPos + 1, filePath.length());
			}else{
				log("getFileSuffix(filePath) >>> filePath is invalid");
			}
		}else{
			log("getFileSuffix(filePath) >>> filePath is null");
		}
		return suffix.toLowerCase();
	}

	/** Get file suffix from file path */
	public static String getFileSuffix(File file) {
		String suffix= "";
		if (file != null) {
			String filePath = file.getAbsolutePath();
			suffix = getFileSuffix(filePath);
		}else{
			log("getFileSuffix(file) >>> file is null");
		}
		return suffix.toLowerCase();
	}


	/** Get file name without suffix from file path */
	public static String getFileNameWithoutSuffix(String filePath) {
		String suffix = "";
		if (filePath != null) {
			int dotPos = filePath.lastIndexOf(".");
			int slashPos = filePath.lastIndexOf("/");
			// invalid : abcdefg.hijk/lmn
			if (dotPos > 0 && dotPos > slashPos) {
				suffix = filePath.substring(slashPos + 1, dotPos);
			}else{
				log("getFileNameWithoutSuffix(filePath) >>> filePath is invalid");
			}
		}else{
			log("getFileNameWithoutSuffix(filePath) >>> file is null");
		}
		return suffix;
	}

	/** Get file name without suffix from file */
	public static String getFileNameWithoutSuffix(File file) {
		String suffix = "";
		if (file != null) {
			String filePath = file.getAbsolutePath();
			suffix = getFileNameWithoutSuffix(filePath);
		}else{
			log("getFileNameWithoutSuffix(file) >>> file is null");
		}
		return suffix;
	}

	//************************************************************
	//             file type
	//************************************************************
	/** whether the file is apk */
	public static boolean isApk(File file) {
		String suffix = getFileSuffix(file);
		return suffix.equals("apk");
	}

	/** whether the file is audio */
	public static boolean isAudio(File file) {
		String suffix = getFileSuffix(file);
		return (suffix.equals("mp3") || suffix.equals("wma")
				|| suffix.equals("ogg") || suffix.equals("wav") || (suffix
				.equals("ape") || suffix.equals("aac")));
	}

	/** whether the file is video */
	public static boolean isVideo(File file) {
		String suffix = getFileSuffix(file);
		return (suffix.equals("avi") || suffix.equals("mp4")
				|| suffix.equals("3gp") || suffix.equals("rmvb")
				|| suffix.equals("mov") || suffix.equals("wmv")
				|| suffix.equals("mpeg") || suffix.equals("mpg")
				|| suffix.equals("rm") || suffix.equals("vob")
				|| suffix.equals("mkv") || suffix.equals("flv"));
	}

	/** whether the file is image */
	public static boolean isImage(File file) {
		String suffix = getFileSuffix(file);
		return (suffix.equals("png") || suffix.equals("jpg")
				|| suffix.equals("jpeg") || suffix.equals("bmp") || suffix
				.equals("gif"));
	}

	/** whether the fileItem is apk */
	public static boolean isApk(FileItem file) {
		String suffix = getFileSuffix(file.getName());
		return suffix.equals("apk");
	}

	/** whether the fileItem is audio */
	public static boolean isAudio(FileItem file) {
		String suffix = getFileSuffix(file.getName());
		return (suffix.equals("mp3") || suffix.equals("wma")
				|| suffix.equals("ogg") || suffix.equals("wav")
				|| suffix.equals("ape") || suffix.equals("aac"));
	}

	/** whether the fileItem is video */
	public static boolean isVideo(FileItem file) {
		String suffix = getFileSuffix(file.getName());
		return (suffix.equals("avi") || suffix.equals("mp4")
				|| suffix.equals("3gp") || suffix.equals("rmvb")
				|| suffix.equals("mov") || suffix.equals("wmv")
				|| suffix.equals("mpeg") || suffix.equals("mpg")
				|| suffix.equals("rm") || suffix.equals("vob")
				|| suffix.equals("mkv") || suffix.equals("flv"));
	}

	/** whether the fileItem is image */
	public static boolean isImage(FileItem file) {
		String suffix = getFileSuffix(file.getName());
		return (suffix.equals("png") || suffix.equals("jpg")
				|| suffix.equals("jpeg") || suffix.equals("bmp") || suffix
				.equals("gif"));
	}

	public static boolean isMedia(File file) {
		return isAudio(file) || isImage(file) || isVideo(file);
	}

	public static boolean isMedia(FileItem item) {
		return isAudio(item) || isImage(item) || isVideo(item);
	}

	public static String getMIMEType(FileItem item) {

		String fName = item.getName();
		String end = getFileSuffix(fName);

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

	public static boolean isAudioIntheFolder(File folder){
		if(folder != null && folder.exists() && folder.isDirectory()){
			File[] files = folder.listFiles();
			if(files == null || files.length < 1) {
				return false;
			}
			for(File file : files){
				if(isAudio(file))
					return true;
			}
		}
		return false;
	}


	public static boolean isVideoIntheFolder(File folder){
		if(folder != null && folder.exists() && folder.isDirectory()){
			File[] files = folder.listFiles();
			if(files == null || files.length < 1) {
				return false;
			}
			for(File file : files){
				if(isVideo(file)) {
					return true;
				}
			}
		}
		return false;
	}


	public static boolean isImageInTheFolder(File folder){
		if(folder != null && folder.exists() && folder.isDirectory()){
			File[] files = folder.listFiles();
			if(files == null || files.length < 1) {
				return false;
			}
			for(File file : files){
				if(isImage(file)) {
					return true;
				}
			}
		}
		return false;
	}

	public static ArrayList<String> getAudioList(File folder){
		if(folder != null && folder.exists() && folder.isDirectory()){
			ArrayList<String> list = new ArrayList<String>();
			File[] files = folder.listFiles();
			for (File file : files) {
				if (FileUtils.isAudio(file)) {
					list.add(file.getAbsolutePath());
				}
			}
			return list;
		}

		return null;
	}

	public static ArrayList<String> getVideoList(File folder) {
		if(folder != null && folder.exists() && folder.isDirectory()){
			ArrayList<String> list = new ArrayList<String>();
			File[] files = folder.listFiles();
			for (File file : files) {
				if (FileUtils.isVideo(file)) {
					list.add(file.getAbsolutePath());
				}
			}
			return list;
		}

		return null;
	}

	public static ArrayList<String> getImageList(File folder) {
		if(folder != null && folder.exists() && folder.isDirectory()){
			ArrayList<String> list = new ArrayList<String>();
			File[] files = folder.listFiles();
			for (File file : files) {
				if (FileUtils.isImage(file)) {
					list.add(file.getAbsolutePath());
				}
			}
			return list;
		}

		return null;
	}

	//************************************************************
	//             file size
	//************************************************************
	/**
	 * 获取文件或目录大小
	 * @param file
	 * @return
	 */
	public static long getFileSize(File file){
		long totalSize = 0;
		if(file != null && file.exists()){

			if(file.isDirectory()){
				File[] files = file.listFiles();
				if(files != null && files.length > 0){
					for(File f : files){
						totalSize += getFileSize(f);
					}
				}
			}else{
				totalSize = file.length();
			}
		}

		return totalSize;
	}


	/** get available space from file */
	public static long getAvailableSpace(File file) {
		StatFs statFs = new StatFs(file.getAbsolutePath());
		long nAvailable = statFs.getAvailableBytes();
		return nAvailable;
	}

	/** get available space from filepath */
	public static long getAvailableSpace(String path) {
		StatFs statFs = new StatFs(path);
		long nAvailable = statFs.getAvailableBytes();
		return nAvailable;
	}
	
	/** get total space from file */
	public static long getTotalSpace(File file) {
		StatFs statFs = new StatFs(file.getAbsolutePath());
		long nTotal = (long) statFs.getTotalBytes();
		return nTotal;
	}

	/** get total space from filepath*/
	public static long getTotalSpace(String path) {
		StatFs statFs = new StatFs(path);
		long nTotal = (long) statFs.getTotalBytes();
		return nTotal;
	}
	
	/** check if sdcard is mounted */
	public static boolean isSDCardExist() {
		String status = Environment.getExternalStorageState();
		boolean isExist = status.equals(Environment.MEDIA_MOUNTED);
		log("isSDCardExist() >>>>> " + isExist);
		return isExist;
	}


	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getfileTimeString(File file) {
		if(file != null && file.exists()){
			return dateFormat.format(new Date(file.lastModified()));
		}else{
			return null;
		}
	}


	public static final int UNIT_NONE = 0; //自动选择单位
	public static final int UNIT_K = 1; //K Byte
	public static final int UNIT_M = 2; //M Byte
	public static final int UNIT_G = 3; //G Byte

	public static String getFileSizeString(File file){
		if(file != null && file.exists()){
			return getSizeString(file.length(), UNIT_NONE);
		}
		return "";
	}

	public static String getSizeString(long length, int unitType) {

		double size = length;
		String unit = " KB";

		switch (unitType) {
			case UNIT_NONE:
				return getSizeString(length);

			case UNIT_K:
				unit = " KB";
				size = size / (1 << 10);
				Log.d(TAG, "size KB = " + size);
				break;

			case UNIT_M:
				unit = " MB";
				size = size / (1 << 20);
				Log.d(TAG, "size MB = " + size);
				break;

			case UNIT_G:
				unit = " GB";
				size = size / (1 << 30);
				Log.d(TAG, "size GB = " + size);
				break;
		}

		return String.format("%.1f", size) + unit;

	}


	public static String getSizeString(long length) {
		String unit = null;
		length = length / 1024;

		if(length < 1024){
			unit = " KB";
		}else{
			length = length / 1024;
			if (length < 1024) {
				unit = " MB";
			} else {
				length = length / 1024;
				unit = " GB";
			}
		}
		return length + unit;
	}





	//************************************************************
	//             get file
	//************************************************************
	/**
	 * 根据路径构建文件，如果路径对应的文件不存在，返回null
	 * @param dirFile
	 * @param fileName
	 * @return
	 */
	public static File getFile(File dirFile, String fileName){
		if(dirFile == null || fileName == null){
			log("getFile(dirFile,fileName) >>> dirFile or fileName is null");
			return null;
		}
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
		if(dirPath == null || fileName == null){
			log("getFile(dirPath,fileName) >>> dirPath or fileName is null");
			return null;
		}
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
		if(filePath == null){
			log("getFile(filePath) >>> filePath is null");
			return null;
		}
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
		if (folderPath == null) {
			log("getFolder(folderPath) >>> folderPath is null");
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


	//************************************************************
	//             create file
	//************************************************************
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
				if(pos < 0){
					log("createFile(filePath) >>> filePath is invalid, return");
					return null;
				}
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



	//************************************************************
	//             copy file with channel
	//************************************************************
	/**
	 * copy the content of source to target.
	 * with channel
	 *
	 * @param source
	 * @param target
	 */
	public static void copyFileChannel(File source, File target) {
		log("copyFileChannel() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFileChannel() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyFileChannel() >>>>> source is not exist, return");
			return;
		}

		if(!target.exists()){
			log("copyFileChannel() >>>>> source is not exist, return");
			try {
				target.createNewFile();
			} catch (IOException e) {
				log("copyFileChannel() >>> [IOException]");
			}
		}

		if(!source.isFile() || !target.isFile()){
			log("copyFileChannel() >>>>> source or target is not a file");
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
	 * copy all files in the source folder to the target folder, source itself is not included.
	 * with channel
	 * 
	 * @param source
	 * @param target
	 */

	public static void copyDirChannel(File source, File target) {
		log("copyDirChannel() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyDirChannel() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyDirChannel() >>>>> source is not exist, return");
			return;
		}

		if (!target.exists()) {
			log("copyDirChannel() >>>>> target is not exist, mkdirs");
			target.mkdirs();
		}

		if(!source.isDirectory() || !target.isDirectory()){
			log("copyDirChannel() >>>>> source or target is not a directory");
		}

		File[] files = source.listFiles();

		for (File theFile : files) {

			File srcFile = theFile;
			File tarFile = new File(target, theFile.getName());

			if (theFile.isFile()) {
				copyFileChannel(srcFile, tarFile);
			}else if(theFile.isDirectory()) {
				copyDirChannel(srcFile, tarFile);
			}
		}
	}

	/**
	 * copy the source file or directory to the target directory ,source is included.
	 * with channel
	 *
	 * @param source
	 * @param target
	 */
	public static void copyFileToDirChannel(File source, File target) {
		log("copyFileToDirChannel() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFileToDirChannel() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyFileToDirChannel() >>>>> source is not exist, return");
			return;
		}

		if (!target.exists()) {
			log("copyFileToDirChannel() >>>>> target is not exist, mkdirs");
			target.mkdirs();
		}

		if(!target.isDirectory()){
			log("copyFileToDirChannel() >>>>> target is not a directory, return");
			return;
		}

		File srcFile = source;
		File tarDir = new File(target, srcFile.getName());

		if(srcFile.isDirectory()){
			copyDirChannel(srcFile, tarDir);
		}else{
			copyFileChannel(srcFile, tarDir);
		}

	}

	//************************************************************
	//             copy file normal
	//************************************************************
	/**
	 * copy the content of source to target.
	 *
	 * @param source
	 * @param target
	 */
	public static void copyFile(File source, File target) {
		log("copyFile() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFile() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyFile() >>>>> source is not exist, return");
			return;
		}

		if(!target.exists()){
			log("copyFile() >>>>> source is not exist, return");
			try {
				target.createNewFile();
			} catch (IOException e) {
				log("copyFile() >>> [IOException]");
			}
		}

		if(!source.isFile() || !target.isFile()){
			log("copyFile() >>>>> source or target is not a file");
			return;
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		int len = -1;
		byte[] buffer = new byte[4096];
		try{
			fis = new FileInputStream(source);
			fos = new FileOutputStream(target);
			while((len = fis.read(buffer)) != -1){
				fos.write(buffer, 0, len);
			}
		}catch(FileNotFoundException e){
			Log.d(TAG, "copyFile() >>>>> FileNotFoundException");
		}catch(IOException e){
			Log.d(TAG, "copyFile() >>>>> IOException");
		}finally{
			try{
				fis.close();
				fos.close();
			}catch(IOException e){
				Log.d(TAG, "copyFile() >>>>> IOException, close");
			}
		}
	}

	/**
	 * copy all files in the source folder to the target folder, source itself is not included.
	 *
	 * @param source
	 * @param target
	 */

	public static void copyDir(File source, File target) {
		log("copyDir() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyDir() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyDir() >>>>> source is not exist, return");
			return;
		}

		if (!target.exists()) {
			log("copyDir() >>>>> target is not exist, mkdirs");
			target.mkdirs();
		}

		if(!source.isDirectory() || !target.isDirectory()){
			log("copyDir() >>>>> source or target is not a directory");
		}

		File[] files = source.listFiles();

		for (File theFile : files) {

			File srcFile = theFile;
			File tarFile = new File(target, theFile.getName());

			if (theFile.isFile()) {
				copyFile(srcFile, tarFile);
			}else if(theFile.isDirectory()) {
				copyDir(srcFile, tarFile);
			}
		}
	}

	/**
	 * copy the source file or directory to the target directory ,source is included.
	 *
	 * @param source
	 * @param target
	 */
	public static void copyFileToDir(File source, File target) {
		log("copyFileToDir() >>>>> src : " + source + " ---> tar : " + target);

		if (source == null || target == null) {
			log("copyFileToDir() >>>>> source or target is null, return");
			return;
		}

		if(!source.exists()){
			log("copyFileToDir() >>>>> source is not exist, return");
			return;
		}

		if (!target.exists()) {
			log("copyFileToDir() >>>>> target is not exist, mkdirs");
			target.mkdirs();
		}

		if(!target.isDirectory()){
			log("copyFileToDir() >>>>> target is not a directory, return");
			return;
		}

		File srcFile = source;
		File tarDir = new File(target, srcFile.getName());

		if(srcFile.isDirectory()){
			copyDir(srcFile, tarDir);
		}else{
			copyFile(srcFile, tarDir);
		}

	}
	//--------------------------------------------------------





	//************************************************************
	//             zip file
	//************************************************************

	/**
	 * zip file
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

	//--------------------------------------------------------

	/**
	 * 删除文件或文件夹
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		log("deleteFile(file) >>> " + file);
		if (file == null) {
			log("deleteFile(file) >>> file is null，return");
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










	private static void log(String message) {
		if (isLog)
			Log.d(TAG, message);
	}

	private static void log(String tag, String message) {
		if (isLog)
			Log.d(tag, message);
	}


	public interface Callback{
		public void onCopyProgress(int total, int progress);
	}

}