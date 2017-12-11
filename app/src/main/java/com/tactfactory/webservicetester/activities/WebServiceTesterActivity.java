package com.tactfactory.webservicetester.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.entities.WebServiceEntity;
import com.tactfactory.webservicetester.fragments.WebServiceEntityFragment;

public class WebServiceTesterActivity extends AppCompatActivity implements WebServiceEntityFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service_tester);
    }

    @Override
    public void onListFragmentInteraction(WebServiceEntity item) {

    }
}
