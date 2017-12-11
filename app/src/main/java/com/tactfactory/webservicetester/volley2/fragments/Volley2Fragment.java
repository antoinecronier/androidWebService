package com.tactfactory.webservicetester.volley2.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.volley2.controllers.Volley2Controller;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Volley2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Volley2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Volley2Fragment extends Fragment {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=ariana,tn&appid=2156e2dd5b92590ab69c0ae1b2d24586&units=metric";
    //the loading Dialog
    ProgressDialog pDialog;
    // Textview to show temperature and description
    TextView temperature, description;
    // background image
    ImageView weatherBackground;
    // JSON object that contains weather information
    JSONObject jsonObj;

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Volley2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Volley2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Volley2Fragment newInstance(String param1, String param2) {
        Volley2Fragment fragment = new Volley2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_volley2, container, false);

        // link graphical items to variables
        temperature = (TextView) view.findViewById(R.id.temperature);
        description = (TextView) view.findViewById(R.id.description);
        weatherBackground = (ImageView) view.findViewById(R.id.weatherbackground);

        // Implementation
        implementation();

        return view;
    }

    private void implementation() {
        // prepare and show the loading Dialog
        pDialog = new ProgressDialog(view.getContext());
        pDialog.setMessage("Please wait while retrieving the weather condition ...");
        pDialog.setCancelable(false);
        pDialog.show();

// make HTTP request to retrieve the weather
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                weatherWebserviceURL, (JSONObject) null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parsing json object response
                    // response will be a json object


                    jsonObj = (JSONObject) response.getJSONArray("weather").get(0);
                    // display weather description into the "description textview"
                    description.setText(jsonObj.getString("description"));
                    // display the temperature
                    temperature.setText(response.getJSONObject("main").getString("temp") + " °C");

                    String backgroundImage = "";

                    //choose the image to set as background according to weather condition
                    if (jsonObj.getString("main").equals("Clouds")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/clouds-wallpaper2.jpg";
                    } else if (jsonObj.getString("main").equals("Rain")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/rainy-wallpaper1.jpg";
                    } else if (jsonObj.getString("main").equals("Snow")) {
                        backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/snow-wallpaper1.jpg";
                    }

                    // load image from link and display it on background
                    // We'll use the Glide library
                    Glide
                            .with(view.getContext().getApplicationContext())
                            .load(backgroundImage)
                            .centerCrop()
                            .crossFade()
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target target, boolean isFirstResource) {
                                    System.out.println(e.toString());
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(weatherBackground);

                    // hide the loading Dialog
                    pDialog.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(view.getContext().getApplicationContext(), "Error , try again ! ", Toast.LENGTH_LONG).show();
                    pDialog.dismiss();

                }
            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("tag", "Error: " + error.getMessage());
                Toast.makeText(view.getContext().getApplicationContext(), "Error while loading ... ", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.dismiss();
            }
        });

        // Adding request to request queue
        Volley2Controller.getInstance(view.getContext()).addToRequestQueue(jsonObjReq);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
