package com.codepath.apps.basictwitter.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Created by yzhang29 on 6/29/14.
 */
public class UserTimelineFragment extends TweetsListFragment {
    String userScreenName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userScreenName = getArguments().getString("userScreenName");
        populateTimeline();
    }

    public static UserTimelineFragment newInstance(String userScreenName) {
        UserTimelineFragment fragmentUser = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("userScreenName", userScreenName);
        fragmentUser.setArguments(args);
        return fragmentUser;
    }

    public void populateTimeline(){
        populateTimeline(null);
    }

    public void populateTimeline(String maxId){
        TwitterApplication.getRestClient().getUserTimeline(userScreenName, maxId, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(JSONArray json) {
                Log.d("json", json.toString());
                addAll(Tweet.fromJSONArray(json));
            }

            @Override
            public void onFailure(Throwable e, String s) {
                super.onFailure(e, s);
                Log.d("debug", e.toString());
                Log.d("debug", s.toString());
            }
        });
    }

}
