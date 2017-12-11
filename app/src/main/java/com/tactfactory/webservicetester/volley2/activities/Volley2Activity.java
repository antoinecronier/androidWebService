package com.tactfactory.webservicetester.volley2.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.volley2.fragments.Volley2Fragment;

public class Volley2Activity extends AppCompatActivity implements Volley2Fragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
