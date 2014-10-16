package com.via.animations;

import android.graphics.Point;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class CustomAnimation extends Animation {

	public static Animation translate(Point begin, Point end, int duration) {
		Animation animation = new TranslateAnimation(begin.x, end.x, begin.y, end.y);
		animation.setDuration(duration);
		animation.setFillAfter(true);
		return animation;
	}
	public static Animation fade(int begin, int end) {
		return new AlphaAnimation(begin, end);
	}
	

	public static Animation fadeOut = new AlphaAnimation(1, 0);
	public static AnimationSet animation1 = new AnimationSet(false);
	public static AnimationSet animation2 = new AnimationSet(false);
	public static AnimationSet animation11 = new AnimationSet(false);
	public static AnimationSet animation22 = new AnimationSet(false);

}
