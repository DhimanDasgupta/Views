package com.dhimandasgupta.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v7.app.ActionBarActivity;

import com.dhimandasgupta.views.callbacks.ActionBarUpClickHandler;
import com.dhimandasgupta.views.callbacks.LauncherItemClickListener;
import com.dhimandasgupta.views.data.DataBuilder;
import com.dhimandasgupta.views.models.Launcher;
import com.dhimandasgupta.views.ui.fragments.LauncherFragment;

public class LauncherActivity extends ActionBarActivity implements
		OnBackStackChangedListener, LauncherItemClickListener,
		ActionBarUpClickHandler {
	private static final String CURRENT_LAUNCHER_TAG = "current_launcher";
	private static final String FRAGMENT_TAG = "current_fragment";

	private Launcher mCurrentLauncher = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		getSupportFragmentManager().addOnBackStackChangedListener(this);

		if (savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.container, LauncherFragment.newInstance(),
							FRAGMENT_TAG).commit();
		} else {
			mCurrentLauncher = savedInstanceState
					.getParcelable(CURRENT_LAUNCHER_TAG);
			if (mCurrentLauncher != null) {
				getSupportActionBar().setTitle(mCurrentLauncher.mTitle);
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(CURRENT_LAUNCHER_TAG, mCurrentLauncher);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackStackChanged() {
		if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
			if (mCurrentLauncher != null) {
				getSupportActionBar().setTitle(mCurrentLauncher.mTitle);
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			}
		} else {
			mCurrentLauncher = null;
			getSupportActionBar().setTitle(R.string.app_name);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
	}

	@Override
	public void onLauncherClicked(final Launcher launcher) {
		final Fragment fragment = DataBuilder.getInstance(launcher);

		if (fragment != null) {
			mCurrentLauncher = launcher;
			getSupportActionBar().setTitle(mCurrentLauncher.mTitle);

			getSupportFragmentManager()
					.beginTransaction()
					.addToBackStack(FRAGMENT_TAG)
					.setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
							R.anim.fade_in, R.anim.fade_out)
					.replace(R.id.container, fragment, FRAGMENT_TAG).commit();
		}
	}

	@Override
	public void onActionBarUpClicked() {
		final int count = getSupportFragmentManager().getBackStackEntryCount();

		if (count > 0) {
			getSupportFragmentManager().popBackStack();
		}
	}
}
