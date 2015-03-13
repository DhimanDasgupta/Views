package com.dhimandasgupta.views.adapters;

import com.dhimandasgupta.views.R;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class LauncherHeaderViewHolder extends ViewHolder {
	private View mRootView;
	private TextView mTextView;

	public LauncherHeaderViewHolder(View itemView) {
		super(itemView);

		mRootView = itemView;
		mTextView = (TextView) itemView
				.findViewById(R.id.adapter_launcher_header_text_view);
	}

	public View getRootView() {
		return mRootView;
	}

	public TextView getTextView() {
		return mTextView;
	}
}
