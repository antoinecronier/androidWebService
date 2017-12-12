package com.tactfactory.webservicetester.mywstester.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.mywstester.fragments.JsonAdaptableFragment;
import com.tactfactory.webservicetester.mywstester.fragments.UseWSFragment;

import org.json.JSONObject;

public class UseWSActivity extends AppCompatActivity implements UseWSFragment.OnFragmentInteractionListener, JsonAdaptableFragment.OnFragmentInteractionListener {

    private JsonAdaptableFragment adaptableJsonFragment;
    private UseWSFragment useWSFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_ws);

        adaptableJsonFragment = (JsonAdaptableFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_use_ws_adaptable_json);
        useWSFragment = (UseWSFragment) this.getSupportFragmentManager().findFragmentById(R.id.fragment_use_ws);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentRestUpdate(JSONObject object) {
        adaptableJsonFragment.update(object);
    }
}
