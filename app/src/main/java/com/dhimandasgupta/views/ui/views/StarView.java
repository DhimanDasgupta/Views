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

public class StarView extends View {
	private Paint mPaint = null;
	private Path mPath = null;

	public StarView(Context context) {
		super(context);
	}

	public StarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public StarView(Context context, AttributeSet attrs, int defStyleAttr,
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

		final int minimumDimension = Math.min(width, height);
		setMeasuredDimension(minimumDimension, minimumDimension);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();

		if (mPath.isEmpty()) {
			createStar();
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

	private void createStar() {
		mPath.moveTo(getWidth()/2, 0);
		mPath.lineTo(0, getHeight());
		mPath.moveTo(getWidth()/2, 0);
		mPath.lineTo(getWidth(), getHeight());
		
		mPath.moveTo(0, getHeight()/3);
		mPath.lineTo(getWidth(), getHeight()/3);
		mPath.moveTo(0, getHeight()/3);
		mPath.lineTo(getWidth(), getHeight());
		
		mPath.moveTo(getWidth(), getHeight()/3);
		mPath.lineTo(0, getHeight());
	}
}
