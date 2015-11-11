package com.inc.haitran.five_elements.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;


public class AlertView {
	private final String tag = "AlertView";
	private Dialog dialog;
	private Context mContext;
	private ViewGroup grTitle, grContent, grButton, btnLeft, btnRight;
	private LinearLayout grAlert;
	private ImageView grMid;
	private TextView txtTitle, txtContent, txtBtnLeft, txtBtnRight;
	private OnClickListener onClickLeft, onClickRight;
	private int nBtn = 0;
	private Handler hanlder = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.what == 0 && onClickLeft != null)
				onClickLeft.onClick(null);
			if (msg.what == 1 && onClickRight != null)
				onClickRight.onClick(null);
		}

	};

	public AlertView(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.alert_layout);
		grAlert = (LinearLayout) dialog.findViewById(R.id.gr_alert);
		grTitle = (ViewGroup) dialog.findViewById(R.id.gr_title);
		grContent = (ViewGroup) dialog.findViewById(R.id.gr_content);
		grButton = (ViewGroup) dialog.findViewById(R.id.gr_button);
		btnLeft = (ViewGroup) dialog.findViewById(R.id.btn_left);
		grMid = (ImageView) dialog.findViewById(R.id.gr_mid);
		btnRight = (ViewGroup) dialog.findViewById(R.id.btn_right);
		txtTitle = (TextView) dialog.findViewById(R.id.txt_title);
		txtContent = (TextView) dialog.findViewById(R.id.txt_content);
		txtBtnLeft = (TextView) dialog.findViewById(R.id.txt_btn_left);
		txtBtnRight = (TextView) dialog.findViewById(R.id.txt_btn_right);
		// set in
		grTitle.setVisibility(View.GONE);
		txtBtnLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				hide();
				hanlder.sendEmptyMessage(0);
			}
		});
		txtBtnRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				hide();
				hanlder.sendEmptyMessage(1);
			}
		});
		dialog.setCancelable(false);

		nBtn = 0;

		dialog.setTitle("");

	}

	public void setWidth(float width) {
		if (grAlert != null) {
			android.view.ViewGroup.LayoutParams lp = grAlert.getLayoutParams();
			lp.width = (int) width;
		}
	}
	public View getView()
	{
		return grAlert;
	}
	public void setOnDismissListener(OnDismissListener dismiss) {
		dialog.setOnDismissListener(dismiss);
	}

	public void setTitle(int titleId) {
		grTitle.setVisibility(View.VISIBLE);
		txtTitle.setText(titleId);
	}

	public void setColorMessage(int resId) {
		txtContent.setTextColor(mContext.getResources().getColor(resId));
	}

	public void setTitle(String title) {
		if (title == null) {
			grTitle.setVisibility(View.GONE);
			return;
		}
		grTitle.setVisibility(View.VISIBLE);
		txtTitle.setText(title);
	}

	public void setMessage(int msgId) {
		txtContent.setVisibility(View.VISIBLE);
		txtContent.setText(msgId);
	}

	public void setMessage(String msg) {
		if (msg == null)
			return;
		txtContent.setVisibility(View.VISIBLE);
		txtContent.setText(msg);
	}
	public void setFontSizeMessage(int textSize)
	{
		if(txtContent != null)
			txtContent.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
	}
	public void setPositiveButton(int resId, OnClickListener onClick) {
		setPositiveButton(mContext.getResources().getString(resId), onClick);
	}

	public void setPositiveButton(String text, OnClickListener onClick) {
		onClickLeft = onClick;
		nBtn++;
		btnLeft.setVisibility(View.VISIBLE);
		txtBtnLeft.setText(text);
		if (nBtn != 2) {
			grMid.setVisibility(View.GONE);
			btnRight.setVisibility(View.GONE);
		} else
			grMid.setVisibility(View.VISIBLE);
	}

	public void setNegativeButton(int resId, OnClickListener onClick) {
		setNegativeButton(mContext.getResources().getString(resId), onClick);
	}

	public void setNegativeButton(OnClickListener onClick) {
		setNegativeButton(R.string.alert_tag_ok, onClick);
	}

	public void setNegativeButton(String text, OnClickListener onClick) {
		onClickRight = onClick;
		nBtn++;
		btnRight.setVisibility(View.VISIBLE);
		txtBtnRight.setText(text);
		if (nBtn != 2) {
			grMid.setVisibility(View.GONE);
			btnLeft.setVisibility(View.GONE);
		} else
			grMid.setVisibility(View.VISIBLE);
	}

	public void show() {
		try {
			if (dialog != null && !dialog.isShowing())
				dialog.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void hide() {
		try {
			if (dialog != null) {
				dialog.dismiss();
				dialog.hide();
				// dialog.dismiss();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setView(View v) {
		txtContent.setVisibility(View.GONE);
		grContent.addView(v);
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
				.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				RelativeLayout.TRUE);
		v.setLayoutParams(layoutParams);
	}


	public static AlertView showAlert(Context context, View v) {
		return showAlert(context, null, v);
	}

	public static AlertView showAlert(Context context, View v, int resId) {
		return showAlert(context, null, v, resId);

	}

	public static AlertView showAlert(Context context, View v,
			String titleButtonOk) {
		return showAlert(context, null, v, titleButtonOk);

	}

	public static AlertView showAlert(Context context, String title, View v) {
		return showAlert(context, title, v, null);

	}

	public static AlertView showAlert(Context context, View v, int resIdOk,
			OnClickListener onClick) {
		return showAlert(context, null, v, resIdOk, onClick);
	}

	public static AlertView showAlert(Context context, View v, String resIdOk,
			OnClickListener onClick) {
		return showAlert(context, null, v, resIdOk, onClick);
	}

	public static AlertView showAlert(Context context, String title, View v,
			int resIdOk, OnClickListener onClick) {
		AlertView alert = create(context);
		alert.setTitle(title);
		alert.setView(v);
		alert.setNegativeButton(resIdOk, onClick);
		alert.show();
		return alert;
	}

	public static AlertView showAlert(Context context, String title, View v,
			String resIdOk, OnClickListener onClick) {
		AlertView alert = create(context);
		alert.setTitle(title);
		alert.setView(v);
		alert.setNegativeButton(resIdOk, onClick);
		alert.show();
		return alert;
	}

	public static AlertView showAlert(Context context, String title, View v,
			int resIdOk) {
		return showAlert(context, title, v,
				context.getResources().getString(resIdOk), null);
	}

	public static AlertView showAlert(Context context, String title, View v,
			String titleButtonOk) {
		AlertView alert = showAlert(context, title, null, titleButtonOk);
		alert.setView(v);
		return alert;
	}

	public static AlertView showAlert(Context context, int messageId) {
		return showAlert(context, context.getResources().getString(messageId));
	}

	public static AlertView showAlert(Context context, String message) {
		return showAlert(context, null, message, null, null);
	}

	public static AlertView showAlert(Context context, int messageId,
			OnClickListener onClick) {
		return showAlert(context, context.getResources().getString(messageId),
				onClick);
	}

	public static AlertView showAlert(Context context, String message,
			OnClickListener onClick) {
		return showAlert(context, null, message, null, null, onClick, null);
	}

	public static AlertView showAlert(Context context, int titleId, int msgId,
			int titlOkId) {
		return showAlert(context, context.getResources().getString(titleId),
				context.getResources().getString(msgId), context.getResources()
						.getString(titlOkId), null);
	}

	public static AlertView showAlert(Context context, int titleId, int msgId,
			int titlOkId, OnClickListener listerBtnOk) {
		return showAlert(context, context.getResources().getString(titleId),
				context.getResources().getString(msgId), context.getResources()
						.getString(titlOkId), listerBtnOk);
	}

	public static AlertView showAlert(Context context, String title,
			String MSG, String titleButtonOk, OnClickListener listerBtnOk) {
		return showAlert(context, title, MSG, titleButtonOk, null, listerBtnOk,
				null);
	}

	public static AlertView showAlert(Context context, String title,
			String MSG, String titleButtonOk, String titleButtonCancel,
			OnClickListener listerBtnOk, OnClickListener listerBtnCancel) {
		AlertView alert = create(context);
		if (title != null && !title.equals(""))
			alert.setTitle(title);
		if (MSG != null)
			alert.setMessage(MSG);
		if (titleButtonOk == null && titleButtonCancel == null) {
			if (listerBtnOk != null)
				alert.setNegativeButton(R.string.alert_tag_ok, listerBtnOk);
			else
				alert.setNegativeButton(R.string.alert_tag_ok, listerBtnCancel);
		} else {
			if (titleButtonOk != null)
				alert.setNegativeButton(titleButtonOk, listerBtnOk);
			if (titleButtonCancel != null)
				alert.setPositiveButton(titleButtonCancel, listerBtnCancel);
		}
		alert.show();
		return alert;
	}

	public static AlertView create(Context context) {
		return new AlertView(context);
	}

	public void setTitleFontSize(float fontSize) {
		if (txtTitle != null) {
			txtTitle.setTextSize(fontSize);
		}
	}

	public void setContentFontSize(float fontSize) {
		if (txtContent != null) {
			txtContent.setTextSize(fontSize);
		}
	}
}
