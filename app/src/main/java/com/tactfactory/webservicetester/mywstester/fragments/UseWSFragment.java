package com.tactfactory.webservicetester.mywstester.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.mywstester.activities.SelectWSActivity;
import com.tactfactory.webservicetester.mywstester.managers.HttpClientManager;
import com.tactfactory.webservicetester.mywstester.managers.SpringRestManager;
import com.tactfactory.webservicetester.mywstester.managers.VolleyManager;
import com.tactfactory.webservicetester.mywstester.managers.base.BaseManager;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UseWSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UseWSFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private BaseManager baseManager;
    private String baseUrl;
    private int managerId;

    private EditText urlEditText;
    private Button getBtn;
    private Button postBtn;
    private Button putBtn;
    private Button deleteBtn;

    public UseWSFragment() {
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
        View view = inflater.inflate(R.layout.fragment_use_ws, container, false);

        if (this.getActivity().getIntent() != null){
            baseUrl = this.getActivity().getIntent().getStringExtra(SelectWSFragment.BASE_URL);
            managerId = this.getActivity().getIntent().getIntExtra(SelectWSFragment.WS_MANAGER_INTENT,0);
        }

        switch (managerId){
            case 1 : this.baseManager = new HttpClientManager(view.getContext()); break;
            case 2 : this.baseManager = new SpringRestManager(); break;
            case 3 : this.baseManager = new VolleyManager(); break;
            default:
        }

        urlEditText = view.findViewById(R.id.fragment_use_ws_url);
        getBtn = view.findViewById(R.id.fragment_use_ws_btn_get);
        postBtn = view.findViewById(R.id.fragment_use_ws_btn_create);
        putBtn = view.findViewById(R.id.fragment_use_ws_btn_update);
        deleteBtn = view.findViewById(R.id.fragment_use_ws_btn_delete);

        urlEditText.setText(baseUrl);

        getBtn.setOnClickListener(new UseWSClickListenerImp() {
            @Override
            public void onClick(View view) {
                sendResult(baseManager.get(urlEditText.getText().toString()));
            }
        });
        postBtn.setOnClickListener(new UseWSClickListenerImp() {
            @Override
            public void onClick(View view) {
                sendResult(baseManager.post(urlEditText.getText().toString()));
            }
        });
        putBtn.setOnClickListener(new UseWSClickListenerImp() {
            @Override
            public void onClick(View view) {
                baseManager.update(urlEditText.getText().toString());
            }
        });
        deleteBtn.setOnClickListener(new UseWSClickListenerImp() {
            @Override
            public void onClick(View view) {
                baseManager.delete(urlEditText.getText().toString());
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
        void onFragmentInteraction(Uri uri);
        void onFragmentRestUpdate(JSONObject object);
    }

    private interface UseWSClickListener extends View.OnClickListener{
        public void sendResult(JSONObject result);
    }

    private abstract class UseWSClickListenerImp implements UseWSClickListener {

        @Override
        public void sendResult(JSONObject result) {
            mListener.onFragmentRestUpdate(result);
        }
    }
}
