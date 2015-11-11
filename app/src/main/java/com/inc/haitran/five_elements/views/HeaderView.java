package com.inc.haitran.five_elements.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;


public class HeaderView extends LinearLayout implements OnClickListener {
	protected ViewGroup mRoot;
	private ViewGroup anchorView;
	private Context mContext;
	private TextView txtTitle,txtOk,txtBack;
	private ImageView imgBack;
	private OnClickListener onClickOk,onClickBack;
	public HeaderView(Context context) {
		super(context);
		mContext = context;
		makeControllerView(context);
		// TODO Auto-generated constructor stub
	}
	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		makeControllerView(context);
		// TODO Auto-generated constructor stub
	}
	protected ViewGroup makeControllerView(Context context) {
		mContext = context;
		LinearLayout ln = new LinearLayout(getContext());
		ln.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		addView(ln);
		setInitView(context,ln);
		ln.addView(mRoot);
		
		return mRoot;
	}
	
	protected void setInitView(Context context, ViewGroup view) {
		mRoot = (ViewGroup) LayoutInflater.from(context).inflate(
				R.layout.header_view_layout, view, false);
		txtOk = (TextView)mRoot.findViewById(R.id.txt_ok);
		txtBack = (TextView)mRoot.findViewById(R.id.txt_back);
		txtTitle = (TextView)mRoot.findViewById(R.id.txt_title);
		imgBack = (ImageView)mRoot.findViewById(R.id.img_back);
		
		txtTitle.setEnabled(false);
		txtTitle.setTextColor(context.getResources().getColor(R.color.white));

		txtOk.setVisibility(View.GONE);
		txtBack.setVisibility(View.GONE);
		imgBack.setVisibility(View.GONE);
		txtOk.setOnClickListener(this);
		txtBack.setOnClickListener(this);
		imgBack.setOnClickListener(this);
	}
	public void setTitle(String title)
	{
		txtTitle.setText(title);
	}
	public void setTitle(int resIdTitle)
	{
		txtTitle.setText(resIdTitle);
	}
	public void setButtonBackTitle(int resIdBack,OnClickListener onBack)
	{
		txtBack.setText(resIdBack);
		txtBack.setVisibility(View.VISIBLE);
		imgBack.setVisibility(View.GONE);
		onClickBack = onBack;
	}
	public void setButtonBackImage(OnClickListener onListenBack)
	{
		imgBack.setVisibility(View.VISIBLE);
		txtBack.setVisibility(View.GONE);
		onClickBack = onListenBack;
	}
	public void setButtonBackImage(int resIdBack,OnClickListener onListenBack)
	{
		imgBack.setImageResource(resIdBack);
		imgBack.setVisibility(View.VISIBLE);
		txtBack.setVisibility(View.GONE);
		onClickBack = onListenBack;
	}
	public void setButtonOk(int resId,OnClickListener onListenOk)
	{
		txtOk.setText(resId);
		txtOk.setVisibility(View.VISIBLE);
		onClickOk = onListenOk;
	}
	public View getButtonOk()
	{
		return txtOk;
	}
	public View getButtonBack()
	{
		if(imgBack.getVisibility() == View.VISIBLE)
			return imgBack;
		return txtBack;
	}
	public void setEnableButton(boolean isEnable)
	{
		txtOk.setEnabled(isEnable);
		txtBack.setEnabled(isEnable);
		imgBack.setEnabled(isEnable);
	}
	public void setPressedButton(boolean isEnable)
	{
		txtOk.setPressed(isEnable);
		txtBack.setPressed(isEnable);
		imgBack.setPressed(isEnable);
	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId()) {
		
		case R.id.txt_back:
		case R.id.img_back:
			if(onClickBack != null)
				onClickBack.onClick(v);
			break;
		case R.id.txt_ok:
			if(onClickOk != null)
				onClickOk.onClick(v);
			break;
		default:
			break;
		}
	}
}
