package com.project.se137.survey.SurveyResultScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.se137.survey.R;
import com.project.se137.survey.SurveyListScreen.SurveyListActivity;
import com.project.se137.survey.TakeSurveyScreen.TakeSurveyActivity;
import com.project.se137.survey.TakeSurveyScreen.TakeSurveyFragment;

/**
 * Created by Johnny on 12/2/15.
 */
public class SurveyResultFragment extends Fragment {

    private String surveyName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_result, container, false);

        Bundle args = getArguments();
        surveyName = args.getString(SurveyListActivity.SURVEY_ID);



        return v;
    }
}
