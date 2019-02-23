package com.sochin.code.dialog;


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

public class CustomDialog extends Dialog {

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialog(Context context) {
		super(context);
	}


	public static class Builder {
		private Context mContext;
		private int mStyleRes = 0;
		private float mAlpha = -1;

		private String mTitle;
		private String mBodyMessage;
		private Drawable mBodyIcon;

		private int mDialogWidth = 0;
		private int mDialogHeight = 0;
		private View mContentView;
		private int mContentLayoutRes = 0;

		private String mPositiveButtonText,mNeutralButtonText,mNegativeButtonText;
		private OnClickListener mPositiveButtonClickListener, mNeutralButtonClickListener,mNegativeButtonClickListener;


		public Builder(Context context){
			this(context, 0, R.style.CustomDialog);
		}

		/**
		 *
		 * @param context
		 * @param resContentLayout
		 */
		public Builder(Context context, int resContentLayout){
			this(context, resContentLayout, R.style.CustomDialog);
		}

		/**
		 *
		 * @param context
		 * @param resContentLayout
		 * @param resStyle
		 */
		public Builder(Context context, int resContentLayout, int resStyle){
			this.mContext = context;
			this.mContentLayoutRes = resContentLayout;
			this.mStyleRes = resStyle;
		}



		public Builder setTitle(String title) {
			this.mTitle = title;
			return this;
		}

		public Builder setTitle(int title) {
			this.mTitle = mContext.getString(title);
			return this;
		}

		public Builder setBodyIcon(Drawable iconDrawable) {
			mBodyIcon = iconDrawable;
			return this;
		}

		public Builder setBodyIcon(int resId) {
			mBodyIcon = mContext.getResources().getDrawable(resId);
			return this;
		}

		public Builder setMessage(String message) {
			this.mBodyMessage = message;
			return this;
		}

		public Builder setMessage(int message) {
			this.mBodyMessage = mContext.getString(message);
			return this;
		}

		/**
		 * 不能与bodyMessage 和 bodyIcon 共存
		 * @param v
		 * @return
		 */
		public Builder setView(View v) {
			this.mContentView = v;
			return this;
		}

		/**
		 * 不能与bodyMessage 和 bodyIcon 共存
		 * @param v
		 * @return
		 */
		public Builder setView(int layoutRes){
			this.mContentLayoutRes = layoutRes;
			return this;
		}

		public Builder setWidth(int width) {
			mDialogWidth = width;
			return this;
		}

		public Builder setHeight(int height) {
			mDialogHeight = height;
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

		public Builder setNeutralButton(int neutralButtonText, OnClickListener listener) {
			this.mNeutralButtonText = mContext.getString(neutralButtonText);
			this.mNeutralButtonClickListener = listener;
			return this;
		}

		public Builder setNeutralButton(String neutralButtonText, OnClickListener listener) {
			this.mNeutralButtonText = neutralButtonText;
			this.mNeutralButtonClickListener = listener;
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



		public CustomDialog create() {
			final CustomDialog dialog = new CustomDialog(mContext, mStyleRes);
			View dialogLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_custom, null);
			LinearLayout llTitle = dialogLayout.findViewById(R.id.llTitle);
			LinearLayout llContent = dialogLayout.findViewById(R.id.llContent);
			LinearLayout llButton = dialogLayout.findViewById(R.id.llButton);
			TextView txtTitle = dialogLayout.findViewById(R.id.txtTitle);
			ImageView imgBodyIcon = dialogLayout.findViewById(R.id.imgBodyIcon);
			TextView txtBodyMessage = dialogLayout.findViewById(R.id.txtBodyMessage);
			Button btnPositive = dialogLayout.findViewById(R.id.btnPositive);
			Button btnNeutral = dialogLayout.findViewById(R.id.btnNeutral);
			Button btnNegtive = dialogLayout.findViewById(R.id.btnNegative);


			//***********************
			// title
			//***********************
			if(mTitle == null){
				llTitle.setVisibility(View.GONE);
			}else{
				txtTitle.setText(mTitle);
				llTitle.setVisibility(View.VISIBLE);
			}


			//***********************
			// button
			//***********************
			if (mPositiveButtonText == null && mNeutralButtonText == null && mNegativeButtonText == null) {
				llButton.setVisibility(View.GONE);
			}else{

				if (mPositiveButtonText != null) {
					btnPositive.setText(mPositiveButtonText);
					if (mPositiveButtonClickListener != null) {
						btnPositive.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								mPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
							}
						});
					}
				} else {
					btnPositive.setVisibility(View.GONE);
				}

				if (mNeutralButtonText != null) {
					btnNeutral.setText(mNeutralButtonText);
					if (mNeutralButtonClickListener != null) {
						btnNeutral.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								mNeutralButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
							}
						});
					}
				} else {
					btnNeutral.setVisibility(View.GONE);
				}

				if (mNegativeButtonText != null) {
					btnNegtive.setText(mNegativeButtonText);
					if (mNegativeButtonClickListener != null) {
						btnNegtive.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								mNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
							}
						});
					}
				} else {
					btnNegtive.setVisibility(View.GONE);
				}

				llButton.setVisibility(View.VISIBLE);
			}


			//***********************
			// content
			//***********************
			if(mContentLayoutRes != 0){
				if(mContentView == null) {
					mContentView = LayoutInflater.from(mContext).inflate(mContentLayoutRes, null);
				}
			}


			if (mContentView != null) {
				llContent.addView(mContentView,
						new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}else{
				if(mBodyIcon != null){
					imgBodyIcon.setImageDrawable(mBodyIcon);
					imgBodyIcon.setVisibility(View.VISIBLE);
				}else{
					imgBodyIcon.setVisibility(View.GONE);
				}

				if(mBodyMessage != null){
					txtBodyMessage.setText(mBodyMessage);
					txtBodyMessage.setVisibility(View.VISIBLE);
				}else{
					txtBodyMessage.setVisibility(View.GONE);
				}
			}


			//***********************
			// property
			//***********************

			dialog.setContentView(dialogLayout);

			WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
			
			if(mAlpha >=0){
				params.alpha = mAlpha;
			}else{
				params.alpha = 1f;
			}
			
			if(mDialogWidth > 0){
				params.width = mDialogWidth;
			}else{
				params.width = WindowManager.LayoutParams.WRAP_CONTENT;
			}
			
			if(mDialogHeight > 0){
				params.height = mDialogHeight;
			}else{
				params.height = WindowManager.LayoutParams.WRAP_CONTENT;
			}

			dialog.getWindow().setAttributes(params);
			return dialog;
		}

		public CustomDialog show() {
			CustomDialog dialog = create();
			dialog.show();
			return dialog;
		}
	}
}