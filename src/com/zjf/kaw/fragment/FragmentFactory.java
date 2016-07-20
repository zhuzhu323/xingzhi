package com.zjf.kaw.fragment;

import java.util.HashMap;


/**
 * 
 */
public class FragmentFactory {

	private static HashMap<Integer, BaseFragment> sFragmentMap = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int position) {
		BaseFragment fragment = sFragmentMap.get(position);

		if (fragment == null) {
			switch (position) {
			case 0:
				fragment = new PopularNewsFragment();
				break;
			case 1:
				fragment = new SpotsNewsFragment();
				break;
			case 2:
				fragment = new TechNewsFragment();
				break;
			case 3:
				fragment = new AutoNewsFragment();
				break;
			}

			sFragmentMap.put(position, fragment);
		}

		return fragment;
	}
}
