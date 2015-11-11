package com.inc.haitran.five_elements.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;

public class Constanst {

	public static final String PREFS_NAME = "MyAddressApp";
	public static final int SET_NO = 0;
	public static final int SET_KEY = 1;
	public static final int SET_INAPP = 2;
	public static final int NUMBER_CARD_FREE = 10;
	public static int verUseApp = 0;
	public static long mDataTimeReset;
	public static long getCurrDate() {
		long dt;
		java.util.Date cal = Calendar.getInstance().getTime();
		dt = cal.getTime();
		return dt;
	}

	public static int getVersionUseApp(Context context) {
		verUseApp = getInt(context, "verUseNameCard",0);
		return verUseApp;
	}
	public static long getDateTimeReset(Context context,long value)
	{
		mDataTimeReset = getLong(context, "DateReset", value); 
		return mDataTimeReset;
	}
	public static void setDateTimeReset(Context context,long value)
	{
		mDataTimeReset = value;
		setLong(context, "DateReset", value);
				
	}
	public static int getNumberCardUse(Context context)
	{
		return getInt(context, "numCardUse", 0);
	}
	public static void setNumberCardUse(Context context,int value)
	{
		setInt(context, "numCardUse", value);
	}
	public static void setVersionUseApp(Context context,int value) {
		verUseApp = value;
		setInt(context, "verUseNameCard", value);
	}

	public static int getInt(Context context, String nameKey,int value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		return mPrefs.getInt(nameKey, value);
	}

	public static void setInt(Context context, String nameKey, int value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt(nameKey, value);
		editor.commit();
	}
	public static float getFloat(Context context, String nameKey,float value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		return mPrefs.getFloat(nameKey, value);
	}

	public static void setFloat(Context context, String nameKey, float value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putFloat(nameKey, value);
		editor.commit();
	}
	public static long getLong(Context context, String nameKey,long value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		return mPrefs.getLong(nameKey, value);
	}

	public static void setLong(Context context, String nameKey, long value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putLong(nameKey, value);
		editor.commit();
	}
	public static String getString(Context context, String nameKey) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		return mPrefs.getString(nameKey, "");
	}

	public static void setString(Context context, String nameKey, String value) {
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.putString(nameKey, value);
		editor.commit();
	}
	public static void deleteKey(Context context, String nameKey)
	{
		SharedPreferences mPrefs = context.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = mPrefs.edit();
		editor.remove(nameKey);
		editor.commit();
	}

}
