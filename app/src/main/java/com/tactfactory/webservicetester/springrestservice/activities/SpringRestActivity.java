package com.tactfactory.webservicetester.springrestservice.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.springrestservice.fragments.SpringRestFragment;

public class SpringRestActivity extends AppCompatActivity implements SpringRestFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_rest);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
