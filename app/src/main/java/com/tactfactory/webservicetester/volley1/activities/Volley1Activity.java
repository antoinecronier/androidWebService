package com.tactfactory.webservicetester.volley1.activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.volley1.fragments.Volley1Fragment;

public class Volley1Activity extends AppCompatActivity implements Volley1Fragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley1);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
