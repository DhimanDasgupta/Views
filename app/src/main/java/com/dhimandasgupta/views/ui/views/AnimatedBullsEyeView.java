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

public class AnimatedBullsEyeView extends View {
	private static final int CIRCLE_INNER_PADDING_IN_PIXEL = 10;
	private static final int STROKE_WIDTH_IN_PIXEL = 5;
	private static final int NUMBER_OF_CIRCLES = 10;

	private Paint mPaint = null;

	private int mMinimumDimension = -1;
	private int mMaxRadius = -1;
	private int[] mRadii = null;

	private boolean mIncrement = false;

	public AnimatedBullsEyeView(Context context) {
		super(context);
	}

	public AnimatedBullsEyeView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public AnimatedBullsEyeView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public AnimatedBullsEyeView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
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

		int width = MeasureSpec.getSize(widthMeasureSpec)
				- (getPaddingLeft() + getPaddingRight());
		int height = MeasureSpec.getSize(heightMeasureSpec)
				- (getPaddingTop() + getPaddingBottom());

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
		mMaxRadius = mMinimumDimension / 2
				- ViewUtils.pxToDp(CIRCLE_INNER_PADDING_IN_PIXEL);

		final int equiDistance = mMaxRadius / mRadii.length;
		for (int i = 0; i < mRadii.length; i++) {
			mRadii[i] = mMaxRadius - i * equiDistance;
		}

		setMeasuredDimension(mMinimumDimension, mMinimumDimension);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		int left = 0;
		int top = 0;
		int right = 0;
		int bottom = 0;
		int radius = 0;

		for (int i = 0; i < mRadii.length; i++) {
			if (i == mRadii.length - 1) {
				mPaint.setStyle(Style.FILL);

				if (mIncrement) {
					radius = mRadii[i] + ViewUtils.pxToDp(2);
					canvas.drawCircle(mMinimumDimension / 2,
							mMinimumDimension / 2, radius, mPaint);
					mIncrement = false;
				} else {
					radius = mRadii[i] - ViewUtils.pxToDp(2);
					canvas.drawCircle(mMinimumDimension / 2,
							mMinimumDimension / 2, radius, mPaint);
					mIncrement = true;
				}

				left = mMinimumDimension / 2 - radius;
				top = mMinimumDimension / 2 - radius;
				right = mMinimumDimension / 2 + radius;
				bottom = mMinimumDimension / 2 + radius;

			} else {
				mPaint.setStyle(Style.STROKE);
				canvas.drawCircle(mMinimumDimension / 2, mMinimumDimension / 2,
						mRadii[i], mPaint);
			}

		}

		canvas.restore();

		super.onDraw(canvas);

		postInvalidateDelayed(64, left, top, right, bottom);
	}

	@Override
	protected void onDetachedFromWindow() {
		mPaint = null;
		mRadii = null;

		super.onDetachedFromWindow();
	}
}
