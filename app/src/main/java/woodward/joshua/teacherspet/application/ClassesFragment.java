package woodward.joshua.teacherspet.application;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import woodward.joshua.teacherspet.R;

/**
 * Created by Joshua on 12/29/2014.
 */
public class ClassesFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classes, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}