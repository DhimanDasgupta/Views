package com.dhimandasgupta.views.ui.fragments;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.callbacks.ActionBarUpClickHandler;
import com.dhimandasgupta.views.ui.views.EquilizerView;

public class EquilizerFragment extends Fragment implements Runnable {
	private static final long TIME_INTERVAL = 50;

	private EquilizerView mEquilizerView;

	private Handler mHandler;
	private Random mRandom;

	public static EquilizerFragment newInstance() {
		final EquilizerFragment equilizerFragment = new EquilizerFragment();
		return equilizerFragment;
	}

	public EquilizerFragment() {

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
		return inflater.inflate(R.layout.fragment_equilizer, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mEquilizerView = (EquilizerView) view
				.findViewById(R.id.fragment_equilizer_equilizer_view);

		mRandom = new Random();

		mHandler = new Handler();
		mHandler.post(this);
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
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		mHandler.removeCallbacks(this);
		mHandler = null;

		mRandom = null;

		mEquilizerView = null;
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

	@Override
	public void run() {
		if (mEquilizerView != null) {
			mEquilizerView.setBarValues(mRandom.nextInt(100),
					mRandom.nextInt(100), mRandom.nextInt(100));

			mHandler.postDelayed(this, TIME_INTERVAL);
		}
	}
}
