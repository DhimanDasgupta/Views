package com.dhimandasgupta.views.ui.views;

import java.util.Random;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dhimandasgupta.views.R;

public class BlurKenBurnsLayout extends FrameLayout implements Runnable {
	private static final long SWAP_MILI_SECONDS = 10000;
	private static final long FADE_IN_OUT_MILI_SECONDS = 400;

	private static final float MAX_SCALE_FACTOR = 1.5f;
	private static final float MIN_SCALE_FACTOR = 1.1f;

	private Handler mHandler = null;
	private Bitmap[] mBitmaps;
	private ImageView[] mImageViews;
	private ProgressBar mProgressBar;
	private int mActiveImageIndex = -1;

	private Random mRandom = null;

	private BitmapBlurAsyncTask mBitmapBlurAsyncTask;

	public BlurKenBurnsLayout(Context context) {
		super(context);
	}

	public BlurKenBurnsLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public BlurKenBurnsLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public BlurKenBurnsLayout(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		mRandom = new Random();

		mHandler = new Handler();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		final View view = inflate(getContext(), R.layout.layout_blur_ken_burns,
				this);

		mImageViews = new ImageView[2];
		mImageViews[0] = (ImageView) view
				.findViewById(R.id.layout_blur_ken_burns_image_view_one);
		mImageViews[1] = (ImageView) view
				.findViewById(R.id.layout_blur_ken_burns_image_view_two);

		mProgressBar = (ProgressBar) view
				.findViewById(R.id.layout_blur_ken_burns_progress_bar);
	}

	private void fillImageViews() {
		if (mImageViews != null) {
			for (int i = 0; i < mImageViews.length; i++) {
				mImageViews[i].setImageBitmap(mBitmaps[i]);
			}
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mHandler.removeCallbacks(this);

		mHandler = null;
		mRandom = null;

		mBitmapBlurAsyncTask.cancel(true);
		mBitmapBlurAsyncTask = null;

		if (mBitmaps != null) {
			for (int i = 0; i < mBitmaps.length; i++) {
				if (mBitmaps[i] != null) {
					mBitmaps[i].recycle();
				}
				mImageViews[0].setImageBitmap(null);
			}

			mBitmaps = null;
		}

		mImageViews = null;
	}

	@Override
	public void run() {
		swapImage();
		mHandler.postDelayed(this, SWAP_MILI_SECONDS - FADE_IN_OUT_MILI_SECONDS
				* 2);
	}

	public void setBitmaps(final Bitmap... bitmaps) {
		mBitmaps = new Bitmap[bitmaps.length];
		mBitmapBlurAsyncTask = new BitmapBlurAsyncTask();
		mBitmapBlurAsyncTask.execute(bitmaps);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void swapImage() {
		if (mActiveImageIndex == -1) {
			mActiveImageIndex = 1;
			animate(mImageViews[mActiveImageIndex]);
			return;
		}

		int inactiveIndex = mActiveImageIndex;
		mActiveImageIndex = (1 + mActiveImageIndex) % mImageViews.length;

		final ImageView activeImageView = mImageViews[mActiveImageIndex];
		ViewCompat.setAlpha(activeImageView, 0.0f);
		ImageView inactiveImageView = mImageViews[inactiveIndex];

		animate(activeImageView);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.setDuration(FADE_IN_OUT_MILI_SECONDS);
			animatorSet.playTogether(ObjectAnimator.ofFloat(inactiveImageView,
					"alpha", 1.0f, 0.0f), ObjectAnimator.ofFloat(
					activeImageView, "alpha", 0.0f, 1.0f));
			animatorSet.start();
		}
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void start(View view, long duration, float fromScale,
			float toScale, float fromTranslationX, float fromTranslationY,
			float toTranslationX, float toTranslationY) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			view.setScaleX(fromScale);
			view.setScaleY(fromScale);
			view.setTranslationX(fromTranslationX);
			view.setTranslationY(fromTranslationY);
			ViewPropertyAnimator propertyAnimator = view.animate()
					.translationX(toTranslationX).translationY(toTranslationY)
					.scaleX(toScale).scaleY(toScale).setDuration(duration);
			propertyAnimator.start();
		}
	}

	private float pickScale() {
		return MIN_SCALE_FACTOR + mRandom.nextFloat()
				* (MAX_SCALE_FACTOR - MIN_SCALE_FACTOR);
	}

	private float pickTranslation(int value, float ratio) {
		return value * (ratio - 1.0f) * (mRandom.nextFloat() - 0.5f);
	}

	public void animate(View view) {
		float fromScale = pickScale();
		float toScale = pickScale();
		float fromTranslationX = pickTranslation(view.getWidth(), fromScale);
		float fromTranslationY = pickTranslation(view.getHeight(), fromScale);
		float toTranslationX = pickTranslation(view.getWidth(), toScale);
		float toTranslationY = pickTranslation(view.getHeight(), toScale);
		start(view, SWAP_MILI_SECONDS, fromScale, toScale, fromTranslationX,
				fromTranslationY, toTranslationX, toTranslationY);
	}

	private class BitmapBlurAsyncTask extends AsyncTask<Bitmap, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			if (mProgressBar != null) {
				mProgressBar.setVisibility(View.VISIBLE);
			}
		};

		@Override
		protected Void doInBackground(Bitmap... params) {
			final Random random = new Random();

			for (int i = 0; i < params.length; i++) {
				mBitmaps[i] = ViewUtils.blurImage(getContext(), params[i],
						random.nextInt(25));
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			fillImageViews();
			mHandler.post(BlurKenBurnsLayout.this);

			if (mProgressBar != null) {
				mProgressBar.setVisibility(View.GONE);
			}
		}
	}
}
