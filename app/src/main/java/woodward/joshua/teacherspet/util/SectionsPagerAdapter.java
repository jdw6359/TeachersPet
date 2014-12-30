package woodward.joshua.teacherspet.util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

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
                return new ClassworkFragment();
            case 1:
                return new ClassworkFragment();
        }
        return new ClassworkFragment();
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
                return "Tab 1";
            case 1:
                return "Tab 2";
        }
        return null;
    }
}
