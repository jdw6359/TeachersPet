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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
public class EvaluationsFragment extends ListFragment {

    //Tag variable
    public final static String TAG="Evaluations Fragment";

    //fragment member variables
    Button mAddNewEvaluationButton;
    List<ParseObject> mEvaluationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_evaluations, container, false);

        mAddNewEvaluationButton=(Button)rootView.findViewById(R.id.add_new_evaluation_button);
        mAddNewEvaluationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bring the user to the AddEvaluationsActivity
                Intent addEvaluationIntent=new Intent(rootView.getContext(), AddEvaluationActivity.class);
                startActivity(addEvaluationIntent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        //get static query for all evaluations belonging to teacher
        ParseQuery<ParseObject> allEvaluationsQuery=ParseQueries.getAllEvaluations(ParseUser.getCurrentUser());

        allEvaluationsQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> evaluationList, ParseException e) {

                Log.d(TAG,"Query returned");

                if(e==null){

                    Log.d(TAG,"successful query");

                    //successful query
                    mEvaluationList=evaluationList;

                    //generate list of names to be adapted
                    String[] evaluationNames=new String[mEvaluationList.size()];
                    for(int i=0;i<mEvaluationList.size();i++){
                        String evaluationName=mEvaluationList.get(i).get(ParseConstants.EVALUATION_KEY_NAME).toString();
                        evaluationNames[i]=evaluationName;
                    }

                    Log.d(TAG,"string list completed");

                    ArrayAdapter<String> evaluationNamesAdapter=new ArrayAdapter<String>(getListView().getContext(),
                            android.R.layout.simple_list_item_1,evaluationNames);
                    setListAdapter(evaluationNamesAdapter);

                    Log.d(TAG,"adapter created and set");

                }else{
                    //failed query, debug log
                    Log.d(TAG,e.getMessage());
                }
            }
        });
    }
}