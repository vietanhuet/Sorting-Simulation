package com.via.fragments;
import uet.via.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.via.customizes.CustomCheckbox;
import com.via.customizes.CustomEditText;
import com.via.customizes.CustomTextView;
import com.via.handle.SortAsyncTask;
import com.via.saved.SavedElements;

@SuppressLint({ "NewApi", "ValidFragment" })
public class SimulationFragment extends Fragment implements OnClickListener {

	private LinearLayout mainProcess;
	private LinearLayout topBlock;
	private LinearLayout bottomBlock;
	private CustomTextView creatNow;
	private static LinearLayout sorting;
	public static CustomTextView state;
	private CustomTextView start;
	private CustomTextView reset;
	private CustomTextView arrayView;
	private CustomCheckbox insertionSort;
	private CustomCheckbox selectionSort;
	private CustomCheckbox bubbleSort;
	private CustomCheckbox increase;
	private CustomCheckbox reduce;
	private CustomEditText quantity;
	private CustomEditText maximum;
	private CustomEditText delay;
	
	 public SavedElements x = new SavedElements();
     public DisplayMetrics displayMetrics = new DisplayMetrics();
     public WindowManager wm;
     public static int height;
	
    private static CustomTextView[] column = new CustomTextView[50];
    @SuppressWarnings("unused")
	private static CustomTextView[] check = new CustomTextView[50];
	public static SortAsyncTask sort;

	private static SimulationFragment mInstance;
	public static SimulationFragment getInstance() {
		if (mInstance == null) {
			mInstance = new SimulationFragment();
		}
		return mInstance;
	}
	private SimulationFragment () {
		
	}
	
	@SuppressLint("ResourceAsColor")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_simulation , container, false);
        initViews(rootView);

        wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels - 500;
        maximum.setHint("Maximum 1~" + height  + " (" + height / 3 * 2 + " recommend)");
        
        mainProcess.setVisibility(View.GONE);
        bubbleSort.setChecked(true);
        increase.setChecked(true);
        SavedElements.mode = 1;
        SavedElements.sortType = 1;    
        return rootView;
    }
	
	private void initViews(View v) {
		sorting = (LinearLayout) v.findViewById(R.id.fragment_simulation_view_sorting);
		start = (CustomTextView) v.findViewById(R.id.fragment_simulation_start);
		state = (CustomTextView) v.findViewById(R.id.fragment_simulation_stateView);
		start.setOnClickListener(this);
		reset = (CustomTextView) v.findViewById(R.id.fragment_simulation_reset);
		reset.setOnClickListener(this);
		state = (CustomTextView) v.findViewById(R.id.fragment_simulation_stateView);
		arrayView = (CustomTextView) v.findViewById(R.id.fragment_simulation_arrayView);
		mainProcess = (LinearLayout) v.findViewById(R.id.fragment_simulation_main_process);
		topBlock = (LinearLayout) v.findViewById(R.id.fragment_simulation_top_block);
		bottomBlock = (LinearLayout) v.findViewById(R.id.fragment_simulation_bottom_block);
		creatNow = (CustomTextView) v.findViewById(R.id.fragment_simulation_creat_now);
		creatNow.setOnClickListener(this);
		quantity = (CustomEditText) v.findViewById(R.id.fragment_simulation_quantity);
		maximum = (CustomEditText) v.findViewById(R.id.fragment_simulation_maximum);
		delay = (CustomEditText) v.findViewById(R.id.fragment_simulation_delay);
		insertionSort = (CustomCheckbox) v.findViewById(R.id.fragment_simulation_insertionsort);
		insertionSort.setOnClickListener(this);
		selectionSort = (CustomCheckbox) v.findViewById(R.id.fragment_simulation_selectionsort);
		selectionSort.setOnClickListener(this);
		bubbleSort = (CustomCheckbox) v.findViewById(R.id.fragment_simulation_bubblesort);
		bubbleSort.setOnClickListener(this);
		increase = (CustomCheckbox) v.findViewById(R.id.fragment_simulation_increase);
		increase.setOnClickListener(this);
		reduce = (CustomCheckbox) v.findViewById(R.id.fragment_simulation_reduce);
		reduce.setOnClickListener(this);
	}
	
	@SuppressLint("ResourceAsColor")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_simulation_start:
			SavedElements.canReset = false;
			Toast.makeText(getActivity(), "Start sorting", Toast.LENGTH_SHORT).show();
			sort = new SortAsyncTask(getActivity(), this,
					SavedElements.array, column, SavedElements.quantity,
					SavedElements.max, SavedElements.step, arrayView,
					sorting, state);
			sort.execute();
			break;
		case R.id.fragment_simulation_reset:
			if(SavedElements.canReset) {
				SavedElements.array = new int[50];
				sorting.removeAllViews();
				SavedElements.max = 0;
				SavedElements.delay = 0;
				SavedElements.quantity = 0;
				quantity.setText("");
				delay.setText("");
				maximum.setText("");
				TranslateAnimation anim11 = new TranslateAnimation(0, 0, -1000, 0);
				TranslateAnimation anim12 = new TranslateAnimation(0, 0, 1000, 0);
				TranslateAnimation anim13 = new TranslateAnimation(0, -1000, 0, 0);
				anim11.setFillAfter(true);
				anim11.setDuration(1000);
				anim12.setFillAfter(true);
				anim12.setDuration(1000);
				anim13.setFillAfter(true);
				anim13.setDuration(800);
				mainProcess.setAnimation(anim13);
				mainProcess.setVisibility(View.GONE);
				topBlock.setAnimation(anim11);
				bottomBlock.setAnimation(anim12);
				topBlock.setVisibility(View.VISIBLE);
				bottomBlock.setVisibility(View.VISIBLE);
			} else Toast.makeText(getActivity(), "Cant reset simulation when progressing.", Toast.LENGTH_SHORT).show();
			break;
		case R.id.fragment_simulation_creat_now:
			SavedElements.canReset = true;
			if(quantity.getText().toString().equals("")) SavedElements.quantity = 0; else
			SavedElements.quantity = Integer.parseInt(quantity.getText().toString());
			if(maximum.getText().toString().equals("")) SavedElements.max = 0; else
			SavedElements.max = Integer.parseInt(maximum.getText().toString());
			if(delay.getText().toString().equals("")) SavedElements.delay = 0; else
			SavedElements.delay = Integer.parseInt(delay.getText().toString());
			String toastString = "";
			if(SavedElements.quantity <= 0 || SavedElements.quantity >= 51) toastString += "Quantity must be >0 and <50\n";
			if(SavedElements.max <= 0 || SavedElements.max > height) toastString += "Maximum must be >0 and <" + height + "\n";
			if(SavedElements.delay <= 0) toastString += "Delay time must be >0";
			if(toastString.equals("")) {
				DisplayMetrics displayMetrics = new DisplayMetrics();
		        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
		        wm.getDefaultDisplay().getMetrics(displayMetrics);
		        SavedElements.step = (displayMetrics.widthPixels - 40) / SavedElements.quantity;
		        arrayView.setText("");
				for(int i=0; i<SavedElements.quantity; i++) {
					int k = (int) (Math.random() * SavedElements.max + 50);
					SavedElements.array[i] = k;
					column[i] = new CustomTextView(getActivity());
					column[i].setGravity(Gravity.BOTTOM);
					column[i].setBackgroundResource(R.drawable.border_dialog);
					column[i].setLayoutParams(new LayoutParams(SavedElements.step, SavedElements.array[i]));
					((LinearLayout) sorting).addView(column[i]);
					arrayView.setText(arrayView.getText().toString() + SavedElements.array[i] + " ");
		        }
				TranslateAnimation anim1 = new TranslateAnimation(0, 0, 0, -1000);
				TranslateAnimation anim2 = new TranslateAnimation(0, 0, 0, 1000);
				TranslateAnimation anim3 = new TranslateAnimation(-1000, 0, 0, 0);
				anim1.setFillAfter(true);
				anim1.setDuration(1000);
				anim2.setFillAfter(true);
				anim2.setDuration(1000);
				anim3.setFillAfter(true);
				anim3.setDuration(800);
				mainProcess.setVisibility(View.VISIBLE);
				mainProcess.setAnimation(anim3);
				topBlock.setAnimation(anim1);
				bottomBlock.setAnimation(anim2);
				topBlock.setVisibility(View.GONE);
				bottomBlock.setVisibility(View.GONE);
			} else Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
			break;
		case R.id.fragment_simulation_bubblesort:
			insertionSort.setChecked(false);
			selectionSort.setChecked(false);
			bubbleSort.setChecked(true);
			SavedElements.mode = 1;
			break;
		case R.id.fragment_simulation_selectionsort:
			insertionSort.setChecked(false);
			selectionSort.setChecked(true);
			bubbleSort.setChecked(false);
			SavedElements.mode = 2;
			break;
		case R.id.fragment_simulation_insertionsort:
			insertionSort.setChecked(true);
			selectionSort.setChecked(false);
			bubbleSort.setChecked(false);
			SavedElements.mode = 3;
			break;
		case R.id.fragment_simulation_increase:
			increase.setChecked(true);
			reduce.setChecked(false);
			SavedElements.sortType = 1;
			break;
		case R.id.fragment_simulation_reduce:
			increase.setChecked(false);
			reduce.setChecked(true);
			SavedElements.sortType = 2;
			break;
		default:
			break;
		}
	}
	
}
