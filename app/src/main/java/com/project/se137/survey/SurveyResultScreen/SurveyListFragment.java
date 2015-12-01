package com.project.se137.survey.SurveyResultScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.project.se137.survey.MainStartScreen.MainActivity;
import com.project.se137.survey.Question;
import com.project.se137.survey.R;
import com.project.se137.survey.Survey;
import com.project.se137.survey.TakeSurveyScreen.TakeSurveyActivity;
import com.project.se137.survey.TakeSurveyScreen.TakeSurveyFragment;

import java.util.List;


/**
 * Created by Johnny on 11/23/15.
 */
public class SurveyListFragment extends Fragment {

    private List<Question> surveys;
    private RecyclerView surveyRecyclerView;
    private SurveyAdapter surveyAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_survey_list, container, false);

        // Initialize surveyRecyclerView
        surveyRecyclerView = (RecyclerView) view.findViewById(R.id.survey_recycler_view);

        // IMPORTANT:
        // RecyclerView requires a LayoutManager to work. IT WILL CRASH IF YOU FORGET ONE.
        surveyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        surveyAdapter = new SurveyAdapter();
        surveyRecyclerView.setAdapter(surveyAdapter);

        return view;
    }

    private class SurveyAdapter extends RecyclerView.Adapter<SurveyHolder> {

        @Override
        public SurveyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create the LayoutInflater that is used to inflate the CrimeHolder (ViewHolder)
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            // Inflates each individual SurveyHolder (ViewHolder) using
            //  the generic xml template list_item_survey.xml
            View view = layoutInflater.inflate(R.layout.list_item_survey, parent, false);

            return new SurveyHolder(view);
        }

        @Override
        public void onBindViewHolder(SurveyHolder holder, int position) {
            final SurveyHolder h = holder;
            final int p = position;

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Survey");

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    Log.d("ALERT:", "Connected to parse!");
                    if (e == null) {
                        for (ParseObject object: objects) {
                            if (object.getNumber("surveyNumber") == p) {
                                Survey s = new Survey(object.getString("surveyName"));
                                h.bindSurvey(s);
                            }
                        }
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class SurveyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Survey survey;
        private TextView surveyNameTextView;

        public SurveyHolder(View itemView) {
            super(itemView);
            // Initialize name of survey inside SurveyHolder
            surveyNameTextView = (TextView) itemView.findViewById(R.id.survey_name_text_view);
        }

        // Bind survey to the holder and set name accordingly
        public void bindSurvey(Survey s) {
            survey = s;
            surveyNameTextView.setText(survey.getName());
        }

        // Initializes TakeSurveyActivity when user selects a survey to take
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra(TakeSurveyFragment.SURVEY_ID, survey.getName());
            startActivity(intent);
        }
    }


}