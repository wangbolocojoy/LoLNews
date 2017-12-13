package com.rhino.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.List;
import com.rhino.ui.utils.Log;
/**
 * Created by wb
 * on 2017/11/21 17:37
 *
 */
public class PagerFragmentAdapter extends FragmentPagerAdapter {

	private FragmentManager mFragmentManager;
	private List<Fragment> mFragmentList;

	public PagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		mFragmentManager = fm;
		mFragmentList = new ArrayList<>();
		mFragmentList.addAll(fragments);
	}

	@Override
	public Fragment getItem(int position) {
		return mFragmentList.get(position % mFragmentList.size());
	}

	@Override
	public int getCount() {
		return mFragmentList.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void updateData(List<Fragment> fragments) {
		if(mFragmentManager.isDestroyed()){
			Log.e("the fragmentManager is destoryed...");
			return;
		}
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for(Fragment f : mFragmentList){
            fragmentTransaction.remove(f);
        }
        fragmentTransaction.commitAllowingStateLoss();
        mFragmentManager.executePendingTransactions();

		mFragmentList.clear();
		mFragmentList.addAll(fragments);
	}
}
