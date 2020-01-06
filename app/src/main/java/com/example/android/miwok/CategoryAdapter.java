package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by MR.Laptop Point on 7/9/2017.
 */


public class CategoryAdapter extends FragmentPagerAdapter {

    private Context context;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return context.getString(R.string.category_numbers);
        }
        else if(position == 1){
            return context.getString(R.string.category_family);
        }
        else {
            if (position == 2) {
                return context.getString(R.string.category_phrases);
            } else {
                return context.getString(R.string.category_colors);
            }
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new NumberFragment();
        }
        else if(position == 1){
            return new FamilyFragment();
        }
        else if(position == 2){
            return new PhrasesFragment();
        }
        else{
            return new ColorFragment();
        }
    }
}
