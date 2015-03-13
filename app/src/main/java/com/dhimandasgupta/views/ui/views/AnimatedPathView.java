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
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.dhimandasgupta.views.R;

public class AnimatedPathView extends View implements Runnable {
	private static final int NUMBER_OF_POINTS = 500;
	
	private Paint mPaint = null;
	private Path mPath = null;

	private Random mRandomX = null;
	private Random mRandomY = null;

	private Handler mHandler = null;
	
	private int mNumberOfPoints = 0;

	public AnimatedPathView(Context context) {
		super(context);
	}

	public AnimatedPathView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public AnimatedPathView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public AnimatedPathView(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
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

		mRandomX = new Random();
		mRandomY = new Random();

		mHandler = new Handler();
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

		addPointToPath();
		canvas.drawPath(mPath, mPaint);

		canvas.restore();

		super.onDraw(canvas);

		if (mNumberOfPoints < NUMBER_OF_POINTS) {
			mHandler.postDelayed(this, 100);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mPath != null) {
			mPath.reset();
			mPath = null;
		}

		mPaint = null;

		mRandomX = null;
		mRandomY = null;
		
		mHandler.removeCallbacks(this);
		mHandler = null;

		super.onDetachedFromWindow();
	}

	@Override
	public void run() {
		invalidate();
	}

	private void addPointToPath() {
		mNumberOfPoints++;
		if (mPath.isEmpty()) {
			mPath.moveTo(mRandomX.nextInt(getWidth()),
					mRandomY.nextInt(getHeight()));
		} else {
			mPath.lineTo(mRandomX.nextInt(getWidth()),
					mRandomY.nextInt(getHeight()));
		}
	}

	public void reDraw() {
		mHandler.removeCallbacks(this);
		
		mNumberOfPoints = 0;
		mPath.reset();
		
		invalidate();
	}
}
