package com.via.customizes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class CustomCheckbox extends CheckBox {

	public CustomCheckbox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CustomCheckbox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomCheckbox(Context context) {
		super(context);
		init();
	}

	private void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"museo.ttf");
			setTypeface(tf);
		}
	}

}