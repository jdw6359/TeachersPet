package woodward.joshua.teacherspet.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import woodward.joshua.teacherspet.R;

/**
 * Created by Joshua on 12/29/2014.
 */
public class ClassesFragment extends ListFragment {

    Button mAddClassButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_classes, container, false);

        mAddClassButton=(Button)rootView.findViewById(R.id.add_new_class_button);
        mAddClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bring the user to AddClassActivity
                Intent newClassIntent=new Intent(rootView.getContext(),AddClassActivity.class);
                startActivity(newClassIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
