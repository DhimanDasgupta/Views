package com.dhimandasgupta.views.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/*
 * Launcher is a model class
 * Attributes are title string, class string (unused), drawable integer resource id
 * */

public class Launcher implements Parcelable {
	public static final int HEADER_VALUE = 0;
	public static final int NON_HEADER_VALUE = 1;

	private static final String TITLE_KEY = "title";
	private static final String CLASS_KEY = "class";
	private static final String DRAWABLE_RESOURCE_ID_KEY = "drawable_resource_id";
	private static final String HEADER_ID_KEY = "header_id";

	public String mTitle;
	public String mClass;
	public int mDrawableResourceId;
	public int mHeader;

	private Launcher() {

	}

	private Launcher(final Parcel in) {
		readFromParcel(in);
	}

	@Override
	public String toString() {
		return "[" + mTitle + ", " + mClass + mHeader + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTitle);
		dest.writeString(mClass);
		dest.writeInt(mDrawableResourceId);
		dest.writeInt(mHeader);
	}

	private void readFromParcel(final Parcel src) {
		mTitle = src.readString();
		mClass = src.readString();
		mDrawableResourceId = src.readInt();
		mHeader = src.readInt();
	}

	public static final Creator<Launcher> CREATOR = new Creator<Launcher>() {
		@Override
		public Launcher createFromParcel(Parcel source) {
			return new Launcher(source);
		}

		@Override
		public Launcher[] newArray(int size) {
			return new Launcher[size];
		}
	};

	public static class Builder {
		private String mTitle;
		private String mClass;
		private int mDrawableResourceId;
		private int mHeader;

		public Builder() {
			mTitle = null;
			mClass = null;
			mDrawableResourceId = -1;
			mHeader = -1;
		}

		public Builder title(final String title) {
			mTitle = title;
			return this;
		}

		public Builder className(final String className) {
			mClass = className;
			return this;
		}

		public Builder drawableResourceId(final int drawableResourceId) {
			mDrawableResourceId = drawableResourceId;
			return this;
		}

		public Builder header(final int header) {
			mHeader = header;
			return this;
		}

		public Launcher build() {
			final Launcher launcher = new Launcher();
			launcher.mTitle = mTitle;
			launcher.mClass = mClass;
			launcher.mDrawableResourceId = mDrawableResourceId;
			launcher.mHeader = mHeader;
			return launcher;
		}
	}

	public Bundle toBundle() {
		final Bundle args = new Bundle();

		args.putString(TITLE_KEY, mTitle);
		args.putString(CLASS_KEY, mClass);
		args.putInt(DRAWABLE_RESOURCE_ID_KEY, mDrawableResourceId);
		args.putInt(HEADER_ID_KEY, mHeader);

		return args;
	}

	public static Launcher fromBundle(@NonNull final Bundle args) {
		final Builder builder = new Builder().title(args.getString(TITLE_KEY))
				.className(args.getString(CLASS_KEY))
				.drawableResourceId(args.getInt(DRAWABLE_RESOURCE_ID_KEY))
				.header(args.getInt(HEADER_ID_KEY));

		return builder.build();
	}

	public JSONObject toJSON() throws JSONException {
		final JSONObject jsonObject = new JSONObject();

		jsonObject.put(TITLE_KEY, mTitle);
		jsonObject.put(CLASS_KEY, mClass);
		jsonObject.put(DRAWABLE_RESOURCE_ID_KEY, mDrawableResourceId);
		jsonObject.put(HEADER_ID_KEY, mHeader);

		return jsonObject;
	}

	public static Launcher fromJSON(@NonNull final JSONObject jsonObject)
			throws JSONException {
		final Builder builder = new Builder()
				.title(jsonObject.getString(TITLE_KEY))
				.className(jsonObject.getString(CLASS_KEY))
				.drawableResourceId(jsonObject.getInt(DRAWABLE_RESOURCE_ID_KEY))
				.header(jsonObject.getInt(HEADER_ID_KEY));

		return builder.build();
	}
}
