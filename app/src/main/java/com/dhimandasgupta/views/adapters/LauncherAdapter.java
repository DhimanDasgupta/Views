package com.dhimandasgupta.views.adapters;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.models.Launcher;

public class LauncherAdapter extends RecyclerView.Adapter<LauncherViewHolder> {
	private OnItemClickListener mOnItemClickListener;
	private List<Launcher> mLaunchers;

	public LauncherAdapter(final List<Launcher> launchers) {
		mLaunchers = launchers;
	}

	@Override
	public int getItemCount() {
		return mLaunchers.size();
	}

	@Override
	public LauncherViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		final LayoutInflater inflater = LayoutInflater
				.from(parent.getContext());
		final View view = inflater.inflate(R.layout.adapter_launcher, parent,
				false);

		final LauncherViewHolder viewHolder = new LauncherViewHolder(view);
		viewHolder.setIsRecyclable(true);

		return viewHolder;
	}

	@Override
	public void onBindViewHolder(LauncherViewHolder viewHolder,
			final int position) {
		viewHolder.getTextView().setText(mLaunchers.get(position).mTitle);

		if (mOnItemClickListener != null) {
			viewHolder.getRootView().setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							mOnItemClickListener.onItemClicked(mLaunchers
									.get(position));
						}
					});
		}
	}

	@Override
	public void onViewRecycled(LauncherViewHolder viewHolder) {
		viewHolder.getRootView().setOnClickListener(null);
		super.onViewRecycled(viewHolder);
	}

	public void setOnItemClickListener(
			final OnItemClickListener onItemClickListener) {
		mOnItemClickListener = onItemClickListener;
	}

	public static interface OnItemClickListener {
		public void onItemClicked(final Launcher launcher);
	}
}
