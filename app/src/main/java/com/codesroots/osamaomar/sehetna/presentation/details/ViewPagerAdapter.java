package com.codesroots.osamaomar.sehetna.presentation.details;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        super.setPrimaryItem(container, position, object);

        Fragment f = ((Fragment)object);
        String activeFragmentTag = f.getTag();
        View view = f.getView();

        if (view != null) {
            View nestedView = view.findViewWithTag("nested");
            if ( nestedView != null && nestedView instanceof NestedScrollView) {
                ((NestedScrollView)nestedView).setNestedScrollingEnabled(true);
            }
        }

        FragmentManager fm = f.getFragmentManager();

        for(Fragment frag : fm.getFragments()) {

            if (frag.getTag() != activeFragmentTag) {
                View v = frag.getView();
                if (v!= null) {

                    View nestedView = v.findViewWithTag("nested");

                    if (nestedView!= null && nestedView instanceof NestedScrollView) {
                        ((NestedScrollView)nestedView).setNestedScrollingEnabled(false);
                    }
                }
            }
        }

        container.requestLayout();
    }
}