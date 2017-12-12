package com.tactfactory.webservicetester.mywstester.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.mywstester.fragments.SelectWSFragment;

public class SelectWSActivity extends AppCompatActivity implements SelectWSFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ws);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
