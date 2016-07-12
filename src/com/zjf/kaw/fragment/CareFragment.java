package com.zjf.kaw.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zjf.kaw.R;

public class CareFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.fragment_care, null);
		
		return view;
	}
}
