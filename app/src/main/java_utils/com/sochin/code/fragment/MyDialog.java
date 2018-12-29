package com.sochin.code.fragment;



import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.sochin.R;

public class MyDialog extends Dialog {
	public MyDialog(Context context, int theme) {
		super(context, theme);
	}

	public MyDialog(Context context) {
		super(context);
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {
		private int dialogWidth = 0;
		private Context mContext;
		private Drawable mIcon;
		private String mTitle;
		private String mMessage;
		private String mPositiveButtonText;
		private String mNegativeButtonText;

		private View mContentView;
		private OnClickListener mPositiveButtonClickListener, mNegativeButtonClickListener;

		public Builder(Context context) {
			this.mContext = context;
		}

		public Builder setMessage(String message) {
			this.mMessage = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.mMessage = mContext.getString(message);
			return this;
		}

		public Builder setTitle(String title) {
			this.mTitle = title;
			return this;
		}

		public Builder setTitle(int title) {
			this.mTitle = mContext.getString(title);
			return this;
		}

		public Builder setView(View v) {
			this.mContentView = v;
			return this;
		}

		public Builder setIcon(Drawable iconDrawable) {
			mIcon = iconDrawable;
			return this;
		}

		public Builder setIcon(int resId) {
			mIcon = mContext.getResources().getDrawable(resId);
			return this;
		}

		public Builder setWidth(int width) {
			dialogWidth = width;
			return this;
		}

		public Builder setPositiveButton(int positiveButtonText, OnClickListener listener) {
			this.mPositiveButtonText = mContext.getString(positiveButtonText);
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
			this.mPositiveButtonText = positiveButtonText;
			this.mPositiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
			this.mNegativeButtonText = mContext.getString(negativeButtonText);
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
			this.mNegativeButtonText = negativeButtonText;
			this.mNegativeButtonClickListener = listener;
			return this;
		}

		

		 
		
		public MyDialog create() {
			final MyDialog dialog = new MyDialog(mContext, R.style.MyAlertDialog);
			View layout = LayoutInflater.from(mContext).inflate(R.layout.dialog_custom, null);
			
			// buttom layout
			if (mPositiveButtonText != null) {
				((Button) layout.findViewById(R.id.btnPositive)).setText(mPositiveButtonText);
				if (mPositiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.btnPositive)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
						}
					});
				}
			} else {
				layout.findViewById(R.id.btnPositive).setVisibility(View.GONE);
			}
			
			if (mNegativeButtonText != null) {
				((Button) layout.findViewById(R.id.btnNegative)).setText(mNegativeButtonText);
				if (mNegativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.btnNegative)).setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							mNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
						}
					});
				}
			} else {
				layout.findViewById(R.id.btnNegative).setVisibility(View.GONE);
			}


			if (mPositiveButtonText == null && mNegativeButtonText == null) {
				layout.findViewById(R.id.llButton).setVisibility(View.GONE);
				layout.findViewById(R.id.dividerBottom).setVisibility(View.GONE);
			}else if(mPositiveButtonText != null && mNegativeButtonText != null){
				
			}else{
				layout.findViewById(R.id.dividerBottomCenter).setVisibility(View.GONE);
			}

			// title layout
			if(mIcon != null){
				((ImageView) layout.findViewById(R.id.imgIcon)).setImageDrawable(mIcon);
			}else{
				layout.findViewById(R.id.imgIcon).setVisibility(View.GONE);
			}
			
			if(mTitle != null){
				((TextView) layout.findViewById(R.id.txtTitle)).setText(mTitle);
			}else{
				layout.findViewById(R.id.txtTitle).setVisibility(View.GONE);
			}
			
			if(mIcon == null && mTitle == null){
				layout.findViewById(R.id.llTitle).setVisibility(View.GONE);
				layout.findViewById(R.id.dividerTop).setVisibility(View.GONE);
			}
			
			// center layout
			if (mMessage != null){
				((TextView) layout.findViewById(R.id.txtMessage)).setText(mMessage);
			}else{
				layout.findViewById(R.id.txtMessage).setVisibility(View.GONE);
			}
			
			if (mContentView != null) {
				((LinearLayout) layout.findViewById(R.id.llContent)).addView(mContentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			}

			dialog.setContentView(layout);

			WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
			if (dialogWidth == 0) {
				params.width = (int) mContext.getResources().getDimension(R.dimen.width_alert_dialog);
			} else {
				params.width = (int) (mContext.getResources().getDisplayMetrics().density * dialogWidth);
			}

			params.verticalMargin = 0.02f;
			params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			dialog.getWindow().setAttributes(params);
			return dialog;
		}

		public MyDialog show() {
			MyDialog dialog = create();
			dialog.show();
			return dialog;
		}
	}
}