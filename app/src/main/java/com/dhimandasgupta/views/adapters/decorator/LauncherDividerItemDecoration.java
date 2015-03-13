package com.dhimandasgupta.views.adapters.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhimandasgupta.views.R;

public class LauncherDividerItemDecoration extends RecyclerView.ItemDecoration {
	private static final int DIVIDER_WIDTH_PIXEL = 2;
	private static final int DIVIDER_HEIGHT_PIXEL = 2;

	public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
	public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

	private Rect mRect;
	private Paint mPaint;

	private int mOrientation;

	public LauncherDividerItemDecoration(Context context, int orientation) {
		setOrientation(orientation);

		mRect = new Rect();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(context.getResources().getColor(R.color.primaryDark));
	}

	public void setOrientation(int orientation) {
		if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
			throw new IllegalArgumentException("invalid orientation");
		}
		mOrientation = orientation;
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent) {
		if (mOrientation == VERTICAL_LIST) {
			drawVertical(c, parent);
		} else {
			drawHorizontal(c, parent);
		}
	}

	public void drawVertical(Canvas c, RecyclerView parent) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth() - parent.getPaddingRight();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int top = child.getBottom() + params.bottomMargin
					+ Math.round(ViewCompat.getTranslationY(child));
			final int bottom = top + DIVIDER_WIDTH_PIXEL;

			mRect.set(left, top, right, bottom);
			c.drawRect(mRect, mPaint);
		}
	}

	public void drawHorizontal(Canvas c, RecyclerView parent) {
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight() - parent.getPaddingBottom();

		final int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
					.getLayoutParams();
			final int left = child.getRight() + params.rightMargin
					+ Math.round(ViewCompat.getTranslationX(child));
			final int right = left + DIVIDER_HEIGHT_PIXEL;

			mRect.set(left, top, right, bottom);
			c.drawRect(mRect, mPaint);
		}
	}

	@Override
	public void getItemOffsets(Rect outRect, int itemPosition,
			RecyclerView parent) {
		if (mOrientation == VERTICAL_LIST) {
			outRect.set(0, 0, 0, DIVIDER_HEIGHT_PIXEL);
		} else {
			outRect.set(0, 0, DIVIDER_WIDTH_PIXEL, 0);
		}
	}
}
