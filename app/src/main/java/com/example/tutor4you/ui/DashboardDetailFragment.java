package com.example.tutor4you.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutor4you.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardDetailFragment extends Fragment {
    @BindView(R.id.tv_heading) TextView mHeadingTextView;
    private String heading;


    public DashboardDetailFragment() {
        // Required empty public constructor
    }

    public static void newInstance(String heading) {
        DashboardDetailFragment dashboardDetailFragment = new DashboardDetailFragment();
        Bundle args = new Bundle();
//        args.putParcelable("heading", Parcelable.wrap(heading));
//        DashboardDetailFragment.setArg
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard_detail, container, false);
    }

}
