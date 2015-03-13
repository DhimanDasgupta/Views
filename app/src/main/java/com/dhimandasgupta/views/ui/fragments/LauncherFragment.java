package com.dhimandasgupta.views.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.adapters.LauncherAdapter;
import com.dhimandasgupta.views.adapters.LauncherAdapter.OnItemClickListener;
import com.dhimandasgupta.views.adapters.decorator.LauncherDividerItemDecoration;
import com.dhimandasgupta.views.callbacks.LauncherItemClickListener;
import com.dhimandasgupta.views.data.DataBuilder;
import com.dhimandasgupta.views.models.Launcher;

public class LauncherFragment extends Fragment implements OnItemClickListener {
	public static LauncherFragment newInstance() {
		final LauncherFragment launcherFragment = new LauncherFragment();
		return launcherFragment;
	}

	public LauncherFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_launcher, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		final RecyclerView recyclerView = (RecyclerView) view
				.findViewById(R.id.fragment_launcher_recyler_view);
		if (recyclerView != null) {
			final LauncherAdapter adapter = new LauncherAdapter(
					DataBuilder.getLaunchers());
			adapter.setOnItemClickListener(this);

			final LauncherDividerItemDecoration decoration = new LauncherDividerItemDecoration(
					view.getContext(), LinearLayoutManager.VERTICAL);
			recyclerView.addItemDecoration(decoration);

			recyclerView.setLayoutManager(new LinearLayoutManager(view
					.getContext(), LinearLayoutManager.VERTICAL, false));
			recyclerView.setAdapter(adapter);
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
	public void onItemClicked(Launcher launcher) {
		if (getActivity() != null) {
			final LauncherItemClickListener callback = (LauncherItemClickListener) getActivity();
			callback.onLauncherClicked(launcher);
		}
	}
}
