package com.sochin.code.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;

public class BitmapUtils {

    private static final String TAG = "BitmapUtils";

    private static enum ScalingLogic {
        CROP, INSIDE
    }
    
    // ************************************************************
    // with no scalingLogic argument
    // ************************************************************

    public static Bitmap decodeResource(Context context, int resId, int dstWidth, int dstHeight) {
        
        Resources res = context.getResources();
        
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight,
                dstWidth, dstHeight, ScalingLogic.INSIDE);
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);

        return unscaledBitmap;
    }

    public static Bitmap decodeFile(String pathName, int dstWidth, int dstHeight) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, ScalingLogic.INSIDE);
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathName, options);

        return unscaledBitmap;
    }

    public static Bitmap decodeUri(Context context, Uri uri, int dstWidth, int dstHeight) {
        try {
            ContentResolver cr = context.getContentResolver();
            
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                    dstWidth, dstHeight, ScalingLogic.INSIDE);
            Bitmap unscaledBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
            
            return unscaledBitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap decodeStream(InputStream is, int dstWidth, int dstHeight) {

        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, ScalingLogic.INSIDE);
        Bitmap unscaledBitmap = BitmapFactory.decodeStream(is, null, options);
        
        return unscaledBitmap;

    }

    public static Bitmap decodeByteArray(byte[] data, int dstWidth, int dstHeight) {

        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, ScalingLogic.INSIDE);
        Bitmap unscaledBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        
        return unscaledBitmap;

    }
    
    // ************************************************************
    // with  scalingLogic argument
    // ************************************************************

    public static Bitmap decodeResource(Context context, int resId, int dstWidth, int dstHeight,
            ScalingLogic logic) {
        
        Resources res = context.getResources();
        
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight,
                dstWidth, dstHeight, logic);
        Bitmap unscaledBitmap = BitmapFactory.decodeResource(res, resId, options);

        return unscaledBitmap;
    }

    public static Bitmap decodeFile(String pathName, int dstWidth, int dstHeight,
            ScalingLogic logic) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, logic);
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathName, options);

        return unscaledBitmap;
    }

    public static Bitmap decodeUri(Context context, Uri uri, int dstWidth, int dstHeight,
            ScalingLogic logic) {
        try {
            ContentResolver cr = context.getContentResolver();
            
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);

            options.inJustDecodeBounds = false;
            options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                    dstWidth, dstHeight, logic);
            Bitmap unscaledBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri), null, options);
            
            return unscaledBitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Bitmap decodeStream(InputStream is, int dstWidth, int dstHeight,
            ScalingLogic logic) {

        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, logic);
        Bitmap unscaledBitmap = BitmapFactory.decodeStream(is, null, options);
        
        return unscaledBitmap;

    }

    public static Bitmap decodeByteArray(byte[] data, int dstWidth, int dstHeight,
            ScalingLogic logic) {

        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth,options.outHeight,
                dstWidth, dstHeight, logic);
        Bitmap unscaledBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        
        return unscaledBitmap;

    }
    
    
    // ************************************************************
    //
    // ************************************************************
    
    public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight) {
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(),
                unscaledBitmap.getHeight(), dstWidth, dstHeight,
                ScalingLogic.INSIDE);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(),
                unscaledBitmap.getHeight(), dstWidth, dstHeight,
                ScalingLogic.INSIDE);
        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(),
                dstRect.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(
                Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }
    
    
    public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(),
                unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(),
                unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(),
                dstRect.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(
                Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    
    public static Bitmap getApkIcon(PackageManager pm, String path) {

        PackageInfo info = pm.getPackageArchiveInfo(path,
                PackageManager.GET_ACTIVITIES);

        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = path;
            appInfo.publicSourceDir = path;
            BitmapDrawable drawable = (BitmapDrawable) appInfo.loadIcon(pm);

            if (drawable != null) {
                return drawable.getBitmap();
            }
        }

        return null;
    }



    public static int calculateSampleSize(int srcWidth, int srcHeight,
            int dstWidth, int dstHeight, ScalingLogic scalingLogic) {

        int widthRatio = srcWidth / dstWidth;
        // int widthRatio = (int)Math.ceil((float)srcWidth/dstWidth);
        Log.d(TAG, "calculateSampleSize() >>>>> widthRatio = " + srcWidth + "/"
                + dstWidth + " = " + (float) srcWidth / dstWidth
                + " widthRatio = " + widthRatio);
        int heightRatio = srcHeight / dstHeight;
        // int heightRatio = (int)Math.ceil((float)srcHeight/dstHeight);
        Log.d(TAG, "calculateSampleSize() >>>>> heightRatio = " + srcHeight
                + "/" + dstHeight + " = " + (float) srcHeight / dstHeight
                + " heightRatio = " + heightRatio);
        int ratio = 1;

        if (scalingLogic == ScalingLogic.INSIDE) {
            ratio = Math.max(widthRatio, heightRatio);
        } else {
            ratio = Math.min(widthRatio, heightRatio);
        }

        if (ratio < 1)
            ratio = 1;

        if (ratio >= 3)
            ratio = 4;

        return ratio;
    }

    public static Rect calculateSrcRect(int srcWidth, int srcHeight,
            int dstWidth, int dstHeight, ScalingLogic scalingLogic) {

        if (scalingLogic == ScalingLogic.CROP) {
            final float ratioSrc = (float) srcWidth / (float) srcHeight;
            final float ratioDst = (float) dstWidth / (float) dstHeight;

            if (ratioSrc > ratioDst) {
                final int widthSrcRect = (int) (srcHeight * ratioDst);
                final int leftSrcRect = (srcWidth - widthSrcRect) / 2;
                return new Rect(leftSrcRect, 0, leftSrcRect + widthSrcRect,
                        srcHeight);
            } else {
                final int heightSrcRect = (int) (srcWidth / ratioDst);
                final int topSrcRect = (int) (srcHeight - heightSrcRect) / 2;
                return new Rect(0, topSrcRect, srcWidth, topSrcRect
                        + heightSrcRect);
            }
        } else {
            return new Rect(0, 0, srcWidth, srcHeight);
        }
    }

    public static Rect calculateDstRect(int srcWidth, int srcHeight,
            int dstWidth, int dstHeight, ScalingLogic scalingLogic) {

        if (scalingLogic == ScalingLogic.INSIDE) {
            final float ratioSrc = (float) srcWidth / (float) srcHeight;
            final float ratioDst = (float) dstWidth / (float) dstHeight;

            if (ratioSrc > ratioDst) {
                return new Rect(0, 0, dstWidth, (int) (dstWidth / ratioSrc));
            } else {
                return new Rect(0, 0, (int) (dstHeight * ratioSrc), dstHeight);
            }
        } else {
            return new Rect(0, 0, dstWidth, dstHeight);
        }
    }

}