package com.dhimandasgupta.views.ui.views;

import java.util.Random;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class PathView extends View {
	private static final int NUMBER_OF_POINTS = 500;

	private Paint mPaint = null;
	private Path mPath = null;

	public PathView(Context context) {
		super(context);
	}

	public PathView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public PathView(Context context, AttributeSet attrs, int defStyleAttr,
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

		mPath = new Path();
		mPath.setFillType(FillType.EVEN_ODD);
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

		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		if (mPath.isEmpty()) {
			createPath();
		}

		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.onDraw(canvas);
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mPath != null) {
			mPath.reset();
			mPath = null;
		}

		mPaint = null;

		super.onDetachedFromWindow();
	}

	private void createPath() {
		final Random randomX = new Random();
		final Random randomY = new Random();

		for (int i = 0; i < NUMBER_OF_POINTS; i++) {
			if (i == 0) {
				mPath.moveTo(randomX.nextInt(getWidth()),
						randomY.nextInt(getHeight()));
			} else {
				mPath.lineTo(randomX.nextInt(getWidth()),
						randomY.nextInt(getHeight()));
			}
		}
	}

	public void reDraw() {
		mPath.reset();

		invalidate();
	}
}
