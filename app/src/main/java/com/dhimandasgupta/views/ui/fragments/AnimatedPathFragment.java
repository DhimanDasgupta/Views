package com.dhimandasgupta.views.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.callbacks.ActionBarUpClickHandler;
import com.dhimandasgupta.views.ui.views.AnimatedPathView;

public class AnimatedPathFragment extends Fragment {
	private AnimatedPathView mAnimatedPathView;

	public static AnimatedPathFragment newInstance() {
		final AnimatedPathFragment animatedPathFragment = new AnimatedPathFragment();
		return animatedPathFragment;
	}

	public AnimatedPathFragment() {

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
		return inflater.inflate(R.layout.fragment_animated_path, container,
				false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mAnimatedPathView = (AnimatedPathView) view
				.findViewById(R.id.fragment_animated_path_animated_path_view);
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_animated_path, menu);
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

		case R.id.menu_action_refresh:
			if (mAnimatedPathView != null) {
				mAnimatedPathView.reDraw();
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
