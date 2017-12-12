package com.tactfactory.webservicetester.mywstester.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tactfactory.webservicetester.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JsonAdaptableFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class JsonAdaptableFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View view;

    public JsonAdaptableFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_json_adaptable, container, false);


        Button clearBtn = view.findViewById(R.id.fragment_json_adaptable_clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout container = (LinearLayout) JsonAdaptableFragment.this.view.findViewById(R.id.fragment_json_adaptable_container);
                View btnClear = container.getChildAt(0);
                container.removeAllViews();
                container.addView(btnClear);
            }
        });

        return view;
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

    public void update(JSONObject object) {

        if (object != null){
            LinearLayout container = (LinearLayout) view.findViewById(R.id.fragment_json_adaptable_container);

            Iterator<String> iterator = object.keys();

            while (iterator.hasNext()){
                String key = iterator.next();
                String value = "";
                try {
                    value = object.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LinearLayout newLinearLayout = new LinearLayout(view.getContext());
                newLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                newLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
                newLinearLayout.setPadding(0,20,0,0);

                TextView newTextViewLabel = new TextView(view.getContext());
                newTextViewLabel.setText(key);
                newTextViewLabel.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

                newLinearLayout.addView(newTextViewLabel);

                TextView newTextViewContent = new TextView(view.getContext());
                newTextViewContent.setText(value);
                newTextViewContent.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                newTextViewContent.setPadding(10,0,0,0);

                newLinearLayout.addView(newTextViewContent);

                container.addView(newLinearLayout);
            }
        }
    }
}
