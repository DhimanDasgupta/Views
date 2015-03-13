package com.dhimandasgupta.views.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class BullsEyeView extends View {
	private static final int CIRCLE_INNER_PADDING_IN_PIXEL = 10;
	private static final int STROKE_WIDTH_IN_PIXEL = 5;
	private static final int NUMBER_OF_CIRCLES = 10;

	private Paint mPaint = null;

	private int mMinimumDimension = -1;
	private int mMaxRadius = -1;
	private int[] mRadii = null;

	public BullsEyeView(Context context) {
		super(context);
	}

	public BullsEyeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public BullsEyeView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public BullsEyeView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		mPaint.setColor(getContext().getResources().getColor(
				R.color.primaryDark));
		mPaint.setStrokeWidth(ViewUtils.pxToDp(STROKE_WIDTH_IN_PIXEL));
		mPaint.setStyle(Style.STROKE);

		mRadii = new int[NUMBER_OF_CIRCLES];
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = MeasureSpec.getSize(widthMeasureSpec) - (getPaddingLeft() + getPaddingRight());
		int height = MeasureSpec.getSize(heightMeasureSpec) - (getPaddingTop() + getPaddingBottom());

		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		switch (widthMode) {
		case MeasureSpec.AT_MOST:

			break;

		case MeasureSpec.EXACTLY:

			break;

		case MeasureSpec.UNSPECIFIED:

			break;
		}

		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		switch (heightMode) {
		case MeasureSpec.AT_MOST:

			break;

		case MeasureSpec.EXACTLY:

			break;

		case MeasureSpec.UNSPECIFIED:

			break;
		}

		mMinimumDimension = Math.min(width, height);
		mMaxRadius = mMinimumDimension / 2 - ViewUtils.pxToDp(CIRCLE_INNER_PADDING_IN_PIXEL);

		final int equiDistance = mMaxRadius / mRadii.length;
		for (int i = 0; i < mRadii.length; i++) {
			mRadii[i] = mMaxRadius - i * equiDistance;
		}

		setMeasuredDimension(mMinimumDimension, mMinimumDimension);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		for (int i = 0; i < mRadii.length; i++) {
			if (i == mRadii.length - 1) {
				mPaint.setStyle(Style.FILL);
			} else {
				mPaint.setStyle(Style.STROKE);
			}
			canvas.drawCircle(mMinimumDimension / 2, mMinimumDimension / 2,
					mRadii[i], mPaint);
		}

		canvas.restore();

		super.onDraw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		mPaint = null;
		mRadii = null;

		super.onDetachedFromWindow();
	}
}
