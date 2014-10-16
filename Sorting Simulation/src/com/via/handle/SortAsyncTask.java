package com.via.handle;

import java.util.ArrayList;

import uet.via.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.via.activities.Couple;
import com.via.customizes.CustomTextView;
import com.via.fragments.SimulationFragment;
import com.via.saved.SavedElements;

@SuppressLint("ResourceAsColor")
public class SortAsyncTask extends AsyncTask<Void, Integer, Void> implements
		AnimationListener {
	Activity activity;
	SimulationFragment sort;
	public int[] array;
	public CustomTextView[] column;
	public CustomTextView[] checking;
	public int n;
	public int step;
	public int max;
	public int ii;
	public int jj;
	public CustomTextView state;
	public LinearLayout sorting;
	public CustomTextView arrayView;
	public ArrayList<Couple> couples;
	public String notify = "";
	public SortAsyncTask(Activity activity, SimulationFragment sort, int[] array, CustomTextView[] column, int n, int max, int step, CustomTextView arrayView, LinearLayout sorting, CustomTextView state) {
		this.activity = activity;
		this.sort = sort;
		this.array = array;
		this.column = column;
		this.max = max;
		this.step = step;
		this.n = n;
		this.arrayView = arrayView;
		this.sorting = sorting;
		this.state = state;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		
		if (SavedElements.mode == 1) {
			if (SavedElements.sortType == 1)
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (array[j - 1] > array[j]) {
							int temp = array[j - 1];
							array[j - 1] = array[j];
							array[j] = temp;
							ii = j-1;
							jj = j;
							SystemClock.sleep(SavedElements.delay + 300);
							publishProgress();
						}
					}
				}
			else {
				for (int i = 0; i < n; i++) {
					for (int j = 1; j < (n - i); j++) {
						if (array[j - 1] < array[j]) {
							int temp = array[j - 1];
							array[j - 1] = array[j];
							array[j] = temp;
							ii = j-1;
							jj = j;
							SystemClock.sleep(SavedElements.delay + 300);
							publishProgress();
						}
					}
				}
			}
		} else 
		if (SavedElements.mode == 2){
			if (SavedElements.sortType == 1) {
				for (int i = 0; i < n - 1; i++)
					for (int j = i + 1; j < n; j++)
						if (array[i] > array[j]) {
							int temp = array[i];
							array[i] = array[j];
							array[j] = temp;
							ii = i;
							jj = j;
							SystemClock.sleep(SavedElements.delay + 300);
							publishProgress();
						}
			} else {
				for (int i = 0; i < n - 1; i++)
					for (int j = i + 1; j < n; j++)
						if (array[i] < array[j]) {
							int temp = array[i];
							array[i] = array[j];
							array[j] = temp;
							ii = i;
							jj = j;
							SystemClock.sleep(SavedElements.delay + 300);
							publishProgress();
						}
			}
		} else {
			if(SavedElements.sortType == 1) {
				for (int i = 1; i < n; i++) {
					int j = i;
					int B = array[i];
					while ((j > 0) && (array[j - 1] > B)) {
						ii = i;
						jj = i;
						SystemClock.sleep(SavedElements.delay + 300);
						publishProgress();
						array[j] = array[j - 1];
						j--;
					}
					array[j] = B;
				}
			} else {
				for (int i = 1; i < n; i++) {
					int j = i;
					int B = array[i];
					while ((j > 0) && (array[j - 1] < B)) {
						ii = i;
						jj = i;
						SystemClock.sleep(SavedElements.delay + 300);
						publishProgress();
						array[j] = array[j - 1];
						j--;
					}
					array[j] = B;
				}
			}
		}
		SavedElements.canReset = true;
		return null;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		
		String s = "";
		for(int k=0; k<n; k++) s = s + array[k] + " ";
		arrayView.setText(s);
		if(SavedElements.mode != 3) state.setText("Swap between " + array[ii] + ", " + array[jj]);
		else state.setText("");
		
		TranslateAnimation anim1 = new TranslateAnimation(0, (jj - ii) * step, 0, 0);
		TranslateAnimation anim2 = new TranslateAnimation(0, (ii - jj) * step, 0, 0);
		anim1.setFillAfter(true);
		anim1.setDuration(SavedElements.delay);
		anim2.setFillAfter(true);
		anim2.setDuration(SavedElements.delay);

		column[ii].startAnimation(anim1);
		column[jj].startAnimation(anim2);
		
		column = new CustomTextView[n];
		sorting.removeAllViews();
		for(int l=0; l<n; l++) {
			column[l] = new CustomTextView(activity);
			column[l].setGravity(Gravity.BOTTOM);
			column[l].setBackgroundResource(R.drawable.border_dialog);
			column[l].setLayoutParams(new LayoutParams(step, array[l]));
			((LinearLayout) sorting).addView(column[l]);
        }
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}

	@Override
	public void onAnimationEnd(Animation arg0) {

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {

	}

	@Override
	public void onAnimationStart(Animation arg0) {

	}

}
