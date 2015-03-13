package com.dhimandasgupta.views.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;

import com.dhimandasgupta.views.R;
import com.dhimandasgupta.views.models.Launcher;
import com.dhimandasgupta.views.models.Launcher.Builder;
import com.dhimandasgupta.views.ui.fragments.AnimatedBullsEyeFragment;
import com.dhimandasgupta.views.ui.fragments.AnimatedCircleFragment;
import com.dhimandasgupta.views.ui.fragments.AnimatedPathFragment;
import com.dhimandasgupta.views.ui.fragments.BlurKenBurnsFragment;
import com.dhimandasgupta.views.ui.fragments.BullsEyeFragment;
import com.dhimandasgupta.views.ui.fragments.EquilizerFragment;
import com.dhimandasgupta.views.ui.fragments.FaceFragment;
import com.dhimandasgupta.views.ui.fragments.KenBurnsFragment;
import com.dhimandasgupta.views.ui.fragments.PathFragment;
import com.dhimandasgupta.views.ui.fragments.SquareFragment;
import com.dhimandasgupta.views.ui.fragments.StarFragment;
import com.dhimandasgupta.views.ui.fragments.TimeFragment;

public class DataBuilder {
	private static final ArrayMap<String, Fragment> sMap = new ArrayMap<String, Fragment>();
	static {
		sMap.put("Path", PathFragment.newInstance());
		sMap.put("Bulls Eye", BullsEyeFragment.newInstance());
		sMap.put("Animated Path", AnimatedPathFragment.newInstance());
		sMap.put("Equilizer", EquilizerFragment.newInstance());
		sMap.put("Star", StarFragment.newInstance());
		sMap.put("Animated Bulls Eye", AnimatedBullsEyeFragment.newInstance());
		sMap.put("Animated Circle", AnimatedCircleFragment.newInstance());
		sMap.put("Square", SquareFragment.newInstance());
		sMap.put("Face", FaceFragment.newInstance());
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			sMap.put("Ken Burns", KenBurnsFragment.newInstance());
			sMap.put("Blur Ken Burns", BlurKenBurnsFragment.newInstance());
		}
		sMap.put("Time", TimeFragment.newInstance());
		//sMap.put("Header Non Animated", null);
		//sMap.put("Header Animated", null);
	}

	public static List<Launcher> getLaunchers() {
		final List<Launcher> launchers = new ArrayList<Launcher>();
		final Builder builder = new Builder();

		final Set<Entry<String, Fragment>> set = sMap.entrySet();
		final Iterator<Entry<String, Fragment>> iterator = set.iterator();
		Entry<String, Fragment> entry = null;
		while (iterator.hasNext()) {
			entry = iterator.next();
			if (entry.getKey() != null) {
				if ("Header Non Animated".equalsIgnoreCase(entry.getKey())
						|| "Header Animated".equalsIgnoreCase(entry.getKey())) {
					launchers.add(builder.title(entry.getKey()).className("")
							.drawableResourceId(R.drawable.ic_launcher)
							.header(Launcher.HEADER_VALUE).build());
				} else {
					launchers.add(builder.title(entry.getKey()).className("")
							.drawableResourceId(R.drawable.ic_launcher)
							.header(Launcher.NON_HEADER_VALUE).build());
				}

			}

		}

		return launchers;
	}

	public static Fragment getInstance(final Launcher launcher) {
		return sMap.get(launcher.mTitle);
	}
}
