package woodward.joshua.teacherspet.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import woodward.joshua.teacherspet.R;
import woodward.joshua.teacherspet.util.ParseConstants;
import woodward.joshua.teacherspet.util.ParseQueries;

/**
 * Created by Joshua on 12/29/2014.
 */
public class ClassesFragment extends ListFragment {

    public final static String TAG="Classes Fragment";

    Button mAddClassButton;
    List<ParseObject> mClasses;

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

        //get parse query
        ParseQuery<ParseObject> allClassesQuery= ParseQueries.getAddClasses(ParseUser.getCurrentUser());
        allClassesQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> classes, ParseException e) {
                if(e==null){
                    //successful
                    //set mClasses member variable
                    mClasses=classes;

                    String[] classList=new String[mClasses.size()];
                    for(int i=0;i<mClasses.size();i++){
                        String className=mClasses.get(i).get(ParseConstants.CLASS_KEY_NAME).toString();
                        classList[i]=className;
                    }

                    ArrayAdapter<String> classesAdapter=new ArrayAdapter<String>(getListView().getContext(),
                            android.R.layout.simple_list_item_1,classList);
                    setListAdapter(classesAdapter);

                }else{
                    //unsuccessful
                    Log.d(TAG, e.getMessage());
                }
            }
        });


    }
}
