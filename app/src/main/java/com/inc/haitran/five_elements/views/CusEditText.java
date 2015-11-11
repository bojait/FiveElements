package com.inc.haitran.five_elements.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.inc.haitran.five_elements.R;


public class CusEditText extends EditText implements OnKeyListener {
	private Context mContext;
	private String txt = "";
	public boolean isCheckDone = true;
	public int resColorOn,resColorOff;
	public CusEditText(Context context) {
		super(context);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub
	}

	public CusEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub

	}
	public CusEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs,defStyle);
		mContext = context;
		initView();
		// TODO Auto-generated constructor stub

	}
	public void setTextInst(String txt)
	{
		this.txt = txt;
		setTextColor(mContext.getResources().getColor(R.color.ocr_txt_no));
	}
	private void initView() {
		resColorOn = mContext.getResources().getColor(R.color.ocr_txt_yes);
		resColorOff = mContext.getResources().getColor(R.color.ocr_txt_no);


		if (txt.equals("")) {
			txt = getText().toString();
			setTextColor(resColorOff);
		}
		
		setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus) {

					if (txt.equals(getText().toString()))
						setText("");
					setTextColor(resColorOn);

				} else {
//					hideKeyboard();
					if (getText().toString().equals("")) {
						setText(txt);
						setTextColor(resColorOff);
					} else
						setTextColor(resColorOn);

				}
			}
		});
		setOnKeyListener(this);
		// setFocusable(false);

	}
	public void setTextOcr(String str)
	{
		if(str.equals(txt) || str.equals(""))
		{
			setTextColor(resColorOff);
			setText(txt);
		}
		else
		{
			setTextColor(resColorOn);
			setText(str);
		}
	}
	public String getTextCus()
	{
		String str =  getText().toString();
		if(str.equals(txt))
			return "";
		return str;
	}
	private void hideKeyboard() {

		InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

		// check if no view has focus:
		inputManager.hideSoftInputFromWindow(getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		

	}
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if (isCheckDone && (event.getAction() == KeyEvent.ACTION_DOWN)
				&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
		
			hideKeyboard();
			return true;
		}
		
		return false;
	}
	public static abstract interface OnChangeTextListener {
		public abstract void onChangeText(View v, String str);
	}
}
