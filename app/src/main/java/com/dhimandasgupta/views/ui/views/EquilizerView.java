package com.dhimandasgupta.views.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class EquilizerView extends View {
	private static final int SPACE_BETWEEN_BARS_IN_PIXEL = 2;
	
	private Paint mPaint = null;

	private Rect mFirstBarRect = null;
	private Rect mSecondBarRect = null;
	private Rect mThirdBarRect = null;

	private int mFirstBarPercentageValue = 0;
	private int mSecondBarPercentageValue = 0;
	private int mThirdBarPercentageValue = 0;

	private int mEachBarWidth;
	private int mEachBarHeight;

	private int mSpaceBetweenBars;

	public EquilizerView(Context context) {
		super(context);
	}

	public EquilizerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public EquilizerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public EquilizerView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(getContext().getResources().getColor(
				R.color.primary));
		mPaint.setStyle(Style.FILL_AND_STROKE);

		mFirstBarRect = new Rect();
		mFirstBarRect.set(0, 0, 0, 0);

		mSecondBarRect = new Rect();
		mSecondBarRect.set(0, 0, 0, 0);

		mThirdBarRect = new Rect();
		mThirdBarRect.set(0, 0, 0, 0);
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

		mEachBarWidth = width / 3;
		mEachBarHeight = height;
		mSpaceBetweenBars = ViewUtils.pxToDp(SPACE_BETWEEN_BARS_IN_PIXEL);

		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		mFirstBarRect.set(0 + mSpaceBetweenBars, mEachBarHeight
				- mFirstBarPercentageValue, mEachBarWidth - mSpaceBetweenBars,
				mEachBarHeight);
		mSecondBarRect.set(mEachBarWidth + mSpaceBetweenBars, mEachBarHeight
				- mSecondBarPercentageValue, 2 * mEachBarWidth
				- mSpaceBetweenBars, mEachBarHeight);
		mThirdBarRect.set(2 * mEachBarWidth + mSpaceBetweenBars, mEachBarHeight
				- mThirdBarPercentageValue, 3 * mEachBarWidth
				- mSpaceBetweenBars, mEachBarHeight);

		canvas.save();

		canvas.drawRect(mFirstBarRect, mPaint);
		canvas.drawRect(mSecondBarRect, mPaint);
		canvas.drawRect(mThirdBarRect, mPaint);

		canvas.restore();

		super.onDraw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		mPaint = null;

		mFirstBarRect = null;
		mSecondBarRect = null;
		mThirdBarRect = null;

		super.onDetachedFromWindow();
	}

	public void setBarValues(final int firstBarPercentageValue,
			final int secondBarPercentageValue,
			final int thirdBarPercentageValue) {
		if (firstBarPercentageValue < 0 || firstBarPercentageValue > 100) {
			throw new NumberFormatException(
					"First Bar Value should be between 0 to 100");
		}

		if (secondBarPercentageValue < 0 || secondBarPercentageValue > 100) {
			throw new NumberFormatException(
					"Second Bar Value should be between 0 to 100");
		}

		if (thirdBarPercentageValue < 0 || thirdBarPercentageValue > 100) {
			throw new NumberFormatException(
					"Third Bar Value should be between 0 to 100");
		}

		mFirstBarPercentageValue = firstBarPercentageValue * mEachBarHeight
				/ 100;
		mSecondBarPercentageValue = secondBarPercentageValue * mEachBarHeight
				/ 100;
		mThirdBarPercentageValue = thirdBarPercentageValue * mEachBarHeight
				/ 100;

		invalidate(mFirstBarRect);
		invalidate(mSecondBarRect);
		invalidate(mThirdBarRect);
	}
}
