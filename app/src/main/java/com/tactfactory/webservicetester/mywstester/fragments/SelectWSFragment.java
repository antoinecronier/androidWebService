package com.tactfactory.webservicetester.mywstester.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.mywstester.activities.UseWSActivity;
import com.tactfactory.webservicetester.mywstester.managers.HttpClientManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectWSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class SelectWSFragment extends Fragment {

    public static final String WS_MANAGER_INTENT = "managerWS";
    public static final String BASE_URL = "baseURL";
    View view;
    Button HttpClientBtn;
    Button SpringRestBtn;
    Button VolleyBtn;
    EditText apiRootEditText;

    private OnFragmentInteractionListener mListener;

    public SelectWSFragment() {
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
        view = inflater.inflate(R.layout.fragment_select_ws, container, false);

        HttpClientBtn = (Button) view.findViewById(R.id.fragment_select_ws_btn_httpclient);
        SpringRestBtn = (Button) view.findViewById(R.id.fragment_select_ws_btn_springrest);
        VolleyBtn = (Button) view.findViewById(R.id.fragment_select_ws_btn_volley);
        apiRootEditText = (EditText) view.findViewById(R.id.fragment_select_ws_root_api_content);

        //Build intent depending on btn clicked
        final Intent intent = new Intent(view.getContext(), UseWSActivity.class);

        HttpClientBtn.setOnClickListener(new SelectWSOnClickListenerImp(intent,1));

        SpringRestBtn.setOnClickListener(new SelectWSOnClickListenerImp(intent,2));

        VolleyBtn.setOnClickListener(new SelectWSOnClickListenerImp(intent,3));

        return view;
    }

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

    private interface SelectWSOnClickListener extends View.OnClickListener{
        public void specificAction();
        public void validation();
        public void onValidate();
    }

    private class SelectWSOnClickListenerImp implements SelectWSOnClickListener{

        private final Intent intent;
        private final int option;

        @Override
        public void specificAction() {
            intent.putExtra(WS_MANAGER_INTENT, option);
        }

        @Override
        public void validation() {
            if (Patterns.WEB_URL.matcher(apiRootEditText.getText()).matches()){
                onValidate();
            }else{
                Toast.makeText(view.getContext(),"Error validate URL",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onValidate() {
            intent.putExtra(BASE_URL, apiRootEditText.getText().toString());
            view.getContext().startActivity(intent);
        }

        @Override
        public void onClick(View view) {
            specificAction();
            validation();
        }

        public SelectWSOnClickListenerImp(Intent intent, int option){
            this.intent = intent;
            this.option = option;
        }
    }

}
