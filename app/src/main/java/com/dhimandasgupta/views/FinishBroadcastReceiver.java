package com.dhimandasgupta.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FinishBroadcastReceiver extends BroadcastReceiver {
	private FinishBroadcastCallback mCallback;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (FinishBroadcastCallback.ACTION_NAME.equals(intent.getAction())) {
			if (mCallback != null) {
				mCallback.onReceive(intent);
			}
		}
	}

	public void registerCallback(final FinishBroadcastCallback callback) {
		mCallback = callback;
	}

	public void unregisterCallback() {
		mCallback = null;
	}

	public static interface FinishBroadcastCallback {
		public static final String ACTION_NAME = "com.dhiman.views.finish.action";

		public void onReceive(final Intent intent);
	}
}
