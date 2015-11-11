package com.inc.haitran.five_elements.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inc.haitran.five_elements.R;

public class DialogView {
	private final String tag = "AlertView";
	private Dialog dialog;
	private Context mContext;
	private ImageView imgIcon;
	private TextView txtTitle;
	private OnDismissListener onDismiss;
	private ProgressBar prLoading;
	private static DialogView inst;
	private boolean isClickHide = false;
	public DialogView(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialog_success_layout);
		imgIcon = (ImageView) dialog.findViewById(R.id.img_icon);
		prLoading = (ProgressBar) dialog.findViewById(R.id.pr_loading);
		txtTitle = (TextView) dialog.findViewById(R.id.txt_title);
		((ViewGroup) dialog.findViewById(R.id.gr_title))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						if(isClickHide)
							hide();
					}
				});
		 dialog.setCancelable(false);

		if(inst != null)
			inst.hide();
		inst = this;
	}
	public void setClickHide(boolean isHide)
	{
		isClickHide = isHide;
				
	}
	public void setOnDismissListener(OnDismissListener dismiss) {
		dialog.setOnDismissListener(dismiss);
		onDismiss = dismiss;
		setClickHide(true);
	}

	public void setTitle(int titleId) {
		txtTitle.setText(titleId);
	}

	public void setTitle(String title) {
		if (title == null) {

			return;
		}
		txtTitle.setText(title);
	}

	public void setIcon(int resId) {
		if (imgIcon != null)
			imgIcon.setImageResource(resId);
	}

	public void show() {
		if (dialog != null && !dialog.isShowing())
			dialog.show();
		Log.d("Dialog", "Show Dialog");
	}

	public void hide() {

		if (dialog != null) {
			try {
				dialog.dismiss();
				dialog.hide();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dialog.cancel();
			dialog = null;
			// dialog.dismiss();
		}

	}
	public static void hideDialog()
	{
		if(inst != null)
			inst.hide();
		inst = null;
	}
//complete
	public static DialogView showDialogComplete(Context context,
			int resIdTitle) {
		return showDialogComplete(context, resIdTitle, null);
	}

	public static DialogView showDialogComplete(Context context,int resIdTitle, OnDismissListener dismiss) {
		return showDialogComplete(context, context.getResources().getString(resIdTitle), dismiss);
	}

	public static DialogView showDialogComplete(Context context,
			String title) {
		return showDialogComplete(context, title, null);
	}

	public static DialogView showDialogComplete(Context context,String title, OnDismissListener dismiss) {
		DialogView alert = create(context);
		if (dismiss == null)
		{
			alert.dialog.setCancelable(false);
			
		}
		else
			alert.setOnDismissListener(dismiss);
		alert.setTitle(title);
		alert.prLoading.setVisibility(View.GONE);
		alert.imgIcon.setVisibility(View.VISIBLE);
		alert.show();
		return alert;
	}
	public static DialogView showDialogLoading(Context context,
			int resIdTitle) {
		return showDialogLoading(context, resIdTitle, null);
	}

	public static DialogView showDialogLoading(Context context,int resIdTitle, OnDismissListener dismiss) {
		return showDialogLoading(context, context.getResources().getString(resIdTitle), dismiss);
	}

	public static DialogView showDialogLoading(Context context,
			String title) {
		return showDialogLoading(context, title, null);
	}

	public static DialogView showDialogLoading(Context context,String title, OnDismissListener dismiss) {
		try {
			DialogView alert = create(context);
			if (dismiss == null) {
				alert.dialog.setCancelable(false);

			} else
				alert.setOnDismissListener(dismiss);
			alert.setTitle(title);
			alert.prLoading.setVisibility(View.VISIBLE);
			alert.imgIcon.setVisibility(View.GONE);
			alert.show();
			return alert;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static DialogView create(Context context) {
		return new DialogView(context);
	}

}
