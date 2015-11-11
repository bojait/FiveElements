package com.inc.haitran.five_elements.utils;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;

import com.inc.haitran.five_elements.db.Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by apple on 9/8/15.
 */
public class DataManager {
    public static int MAX_EQUIP = 10;
    public static final String key_equip_id = "equip_id";
    public static final String key_file_data ="my_data_equip.txt";
    private static DataManager mInst;
    private String cachePath ;
    private Context mContext;
    public static DataManager getInst()
    {
        return mInst;
    }
    public static void getInstall(Context context)
    {
        if(mInst == null)
            new DataManager(context);
    }
    public DataManager(Context context)
    {
        mInst = this;
        mContext = context;
//        cachePath =
//                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
//                        !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() :
//                        context.getCacheDir().getPath();
        cachePath = context.getCacheDir().getPath();
        Database.getInstall(context);
        MyData.getInstall(context);


    }
    public static String onReadFileTextInAsset(Context context,String namefile)
    {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(namefile);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int i;
            try {
                i = inputStream.read();
                while (i != -1)
                {
                    byteArrayOutputStream.write(i);
                    i = inputStream.read();
                }
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return byteArrayOutputStream.toString();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public  String onReadData()
    {
        String dataPush ="";
        String path = cachePath+"/"+key_file_data;
        File file = new File(path);
        if(!file.exists())
        {
            return "";
        }
        else
        {
            try {
                FileReader fileReader = new FileReader(path);
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader =
                        new BufferedReader(fileReader);
                String line = null;
                while((line = bufferedReader.readLine()) != null) {
                    dataPush += line;
                }

                // Always close files.
                bufferedReader.close();
                fileReader.close();
            }
            catch(FileNotFoundException ex) {
                ex.printStackTrace();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        return dataPush;
    }
    public boolean onWriteData(String data)
    {
        String path = cachePath+"/"+key_file_data;

        BufferedWriter output = null;
        try {
            File file = new File(path);
            output = new BufferedWriter(new FileWriter(file));
            output.write(data);
            if ( output != null )
                output.close();
            return true;
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return context.getExternalCacheDir();
        }

        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }
}
