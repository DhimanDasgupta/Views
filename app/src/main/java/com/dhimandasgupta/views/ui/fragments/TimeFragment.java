package com.dhimandasgupta.views.ui.fragments;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.callbacks.ActionBarUpClickHandler;

public class TimeFragment extends Fragment {
	private static final long SECOND = 1000;

	private final Calendar mCalendar = Calendar.getInstance();

	private Handler mHandler = new Handler();

	private Runnable mRunnable = new Runnable() {
		@Override
		public void run() {
			animateAndSetText();

			mHandler.postDelayed(mRunnable, SECOND);
		}
	};

	private TextView mHourOneTextView;
	private TextView mHourTwoTextView;

	private TextView mMinuteOneTextView;
	private TextView mMinuteTwoTextView;

	private TextView mSecondOneTextView;
	private TextView mSecondTwoTextView;

	public static TimeFragment newInstance() {
		final TimeFragment timeFragment = new TimeFragment();
		return timeFragment;
	}

	public TimeFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_time, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mHourOneTextView = (TextView) view
				.findViewById(R.id.fragment_time_hour_text_view_one);
		mHourTwoTextView = (TextView) view
				.findViewById(R.id.fragment_time_hour_text_view_two);

		mMinuteOneTextView = (TextView) view
				.findViewById(R.id.fragment_time_minute_text_view_one);
		mMinuteTwoTextView = (TextView) view
				.findViewById(R.id.fragment_time_minute_text_view_two);

		mSecondOneTextView = (TextView) view
				.findViewById(R.id.fragment_time_second_text_view_one);
		mSecondTwoTextView = (TextView) view
				.findViewById(R.id.fragment_time_second_text_view_two);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();

		mHandler.post(mRunnable);
	}

	@Override
	public void onPause() {
		super.onPause();

		mHandler.removeCallbacks(mRunnable);
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (getActivity() != null) {
				final ActionBarUpClickHandler callback = (ActionBarUpClickHandler) getActivity();
				callback.onActionBarUpClicked();
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void animateAndSetText() {
		mCalendar.setTimeInMillis(System.currentTimeMillis());

		// For Second
		final int second = mCalendar.get(Calendar.SECOND);

		mSecondOneTextView.setText(String.valueOf(second / 10));
		mSecondTwoTextView.setText(String.valueOf(second % 10));

		// For Minute
		final int minute = mCalendar.get(Calendar.MINUTE);

		mMinuteOneTextView.setText(String.valueOf(minute / 10));
		mMinuteTwoTextView.setText(String.valueOf(minute % 10));

		// For Hour
		final int hour = mCalendar.get(Calendar.HOUR_OF_DAY);

		mHourOneTextView.setText(String.valueOf(hour / 10));
		mHourTwoTextView.setText(String.valueOf(hour % 10));
	}
}
