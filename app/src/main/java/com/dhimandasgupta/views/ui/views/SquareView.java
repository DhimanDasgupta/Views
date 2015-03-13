package com.dhimandasgupta.views.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class SquareView extends View {
	private static final int STROKE_WIDTH_IN_PIXEL = 5;

	private Paint mPaint = null;
	private Path mPath = null;

	public SquareView(Context context) {
		super(context);
	}

	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public SquareView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(getContext().getResources().getColor(
				R.color.primaryDark));
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(ViewUtils.pxToDp(STROKE_WIDTH_IN_PIXEL));

		mPath = new Path();
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

		final int minimumDimension = Math.min(width, height)
				- ViewUtils.pxToDp(STROKE_WIDTH_IN_PIXEL);
		setMeasuredDimension(minimumDimension, minimumDimension);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		if (mPath.isEmpty()) {
			createSquare();
		}
		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.onDraw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		mPaint = null;

		if (mPath != null) {
			mPath.reset();
			mPath = null;
		}

		super.onDetachedFromWindow();
	}

	private void createSquare() {
		mPath.moveTo(0, 0);
		mPath.lineTo(getWidth(), 0);
		mPath.lineTo(getWidth(), getHeight());
		mPath.lineTo(0, getHeight());
		mPath.lineTo(0, 0);
	}
}
