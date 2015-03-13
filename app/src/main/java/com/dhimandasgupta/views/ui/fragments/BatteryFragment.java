package com.dhimandasgupta.views.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhimandasgupta.views.R;

@SuppressLint("CutPasteId")
public class BatteryFragment extends Fragment {
	private TextView mChargingTextView;
	private TextView mChargingUSBTextView;
	private TextView mChargingACTextView;

	public static BatteryFragment newInstance() {
		final BatteryFragment batteryFragment = new BatteryFragment();
		return batteryFragment;
	}

	public BatteryFragment() {

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
		return inflater.inflate(R.layout.fragment_battery, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mChargingTextView = (TextView) view
				.findViewById(R.id.fragment_battery_is_usb_charging_value_text_view);
		mChargingUSBTextView = (TextView) view
				.findViewById(R.id.fragment_battery_is_usb_charging_value_text_view);
		mChargingACTextView = (TextView) view
				.findViewById(R.id.fragment_battery_is_ac_charging_value_text_view);
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

	public void onPowerStateChanged(final boolean isCharging,
			final boolean isUsbCharge, final boolean isAcCharge) {
		if (mChargingTextView != null) {
			mChargingTextView.setText(String.valueOf(isCharging));
		}

		if (mChargingUSBTextView != null) {
			mChargingUSBTextView.setText(String.valueOf(isUsbCharge));
		}

		if (mChargingACTextView != null) {
			mChargingACTextView.setText(String.valueOf(isAcCharge));
		}
	}
}
