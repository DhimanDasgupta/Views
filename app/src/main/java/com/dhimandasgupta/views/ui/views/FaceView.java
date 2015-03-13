package com.dhimandasgupta.views.ui.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class FaceView extends View {
	private static final int CIRCLE_INNER_PADDING_IN_PIXEL = 10;
	private static final int STROKE_WIDTH_IN_PIXEL = 5;

	private Paint mPaint = null;

	private Path mPath = null;

	private int mMinimumDimension = -1;
	private int mMaxRadius = -1;
	private int mEyeRadius = -1;

	public FaceView(Context context) {
		super(context);
	}

	public FaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public FaceView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public FaceView(Context context, AttributeSet attrs, int defStyleAttr,
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

		mEyeRadius = mMaxRadius / 10;

		setMeasuredDimension(mMinimumDimension, mMinimumDimension);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		if (mPath == null) {
			createFace();
		}

		canvas.drawPath(mPath, mPaint);
		canvas.drawCircle(mMinimumDimension / 2, mMinimumDimension / 2,
				mMaxRadius, mPaint);

		canvas.restore();

		super.onDraw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		mPaint = null;

		super.onDetachedFromWindow();
	}

	private void createFace() {
		if (mPath == null) {
			mPath = new Path();
		}

		// Eyes
		mPath.addCircle(getWidth() / 4, getHeight() / 4, mEyeRadius,
				Direction.CW);
		mPath.addCircle(3 * getWidth() / 4, getHeight() / 4, mEyeRadius,
				Direction.CW);

		// Nose
		mPath.addRect(getWidth() / 2 - STROKE_WIDTH_IN_PIXEL, getHeight() / 4,
				getWidth() / 2 + STROKE_WIDTH_IN_PIXEL, 3 * getHeight() / 4,
				Direction.CW);

		// Mouth
		float radius = 50;
		final RectF oval = new RectF();
		oval.set(getWidth() / 4 - radius, 3 * getHeight() / 4 - radius, 3
				* getWidth() / 4 + radius, 3 * getHeight() / 4 + radius);
		mPath.arcTo(oval, 180, -(float) 180, true);
	}
}
