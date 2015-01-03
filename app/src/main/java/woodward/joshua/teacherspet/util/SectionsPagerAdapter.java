package woodward.joshua.teacherspet.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

import woodward.joshua.teacherspet.application.ClassesFragment;
import woodward.joshua.teacherspet.application.StudentsFragment;

/**
 * Created by Joshua on 12/29/2014.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    protected Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fragmentManager){
        super(fragmentManager);
        mContext=context;
    }


    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new StudentsFragment();
            case 1:
                return new ClassesFragment();
        }
        return new ClassesFragment();
    }

    @Override
    public int getCount() {
        //show two total tabs
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        Locale l= Locale.getDefault();
        switch(position){
            case 0:
                return "Students";
            case 1:
                return "Classes";
        }
        return null;
    }
}
