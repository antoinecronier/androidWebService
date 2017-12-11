package com.tactfactory.webservicetester.httpclientapache.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.httpclientapache.fragments.HttpClientFragment;

public class HttpClientActivity extends AppCompatActivity implements HttpClientFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
