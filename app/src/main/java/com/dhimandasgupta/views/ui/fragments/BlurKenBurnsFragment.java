package com.dhimandasgupta.views.ui.fragments;

import java.util.Random;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.callbacks.ActionBarUpClickHandler;
import com.dhimandasgupta.views.ui.views.BlurKenBurnsLayout;

public class BlurKenBurnsFragment extends Fragment {
	public static BlurKenBurnsFragment newInstance() {
		final BlurKenBurnsFragment blurKenBurnsFragment = new BlurKenBurnsFragment();
		return blurKenBurnsFragment;
	}

	public BlurKenBurnsFragment() {

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
		return inflater.inflate(R.layout.fragment_blur_ken_burns, container,
				false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		final BlurKenBurnsLayout blurKenBurnsLayout = (BlurKenBurnsLayout) view
				.findViewById(R.id.fragment_blur_ken_burns_blur_ken_burns_layout);
		if (blurKenBurnsLayout != null) {
			final Random random = new Random();

			switch (random.nextInt(4)) {
			case 0:
				blurKenBurnsLayout.setBitmaps(BitmapFactory.decodeResource(
						getResources(), R.drawable.picture0), BitmapFactory
						.decodeResource(getResources(), R.drawable.picture1));
				break;

			case 1:
				blurKenBurnsLayout.setBitmaps(BitmapFactory.decodeResource(
						getResources(), R.drawable.picture0), BitmapFactory
						.decodeResource(getResources(), R.drawable.picture2));
				break;

			case 2:
				blurKenBurnsLayout.setBitmaps(BitmapFactory.decodeResource(
						getResources(), R.drawable.picture3), BitmapFactory
						.decodeResource(getResources(), R.drawable.picture1));
				break;

			case 3:
				blurKenBurnsLayout.setBitmaps(BitmapFactory.decodeResource(
						getResources(), R.drawable.picture3), BitmapFactory
						.decodeResource(getResources(), R.drawable.picture2));
				break;
			}
		}
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
}
