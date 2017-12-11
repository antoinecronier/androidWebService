package com.tactfactory.webservicetester.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tactfactory.webservicetester.R;
import com.tactfactory.webservicetester.entities.WebServiceEntity;
import com.tactfactory.webservicetester.fragments.WebServiceEntityFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link WebServiceEntity} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class WebServiceEntityRecyclerViewAdapter extends RecyclerView.Adapter<WebServiceEntityRecyclerViewAdapter.ViewHolder> {

    private final List<WebServiceEntity> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;

    public WebServiceEntityRecyclerViewAdapter(List<WebServiceEntity> items, OnListFragmentInteractionListener listener, FragmentActivity activity) {
        mValues = items;
        mListener = listener;
        mContext = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_webserviceentity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mButtonView.setText(mValues.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Button mButtonView;
        public WebServiceEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mButtonView = (Button) view.findViewById(R.id.webServiceEntityButton);

            mButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, mItem.getActivity());
//                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public String toString() {
            return mItem.getName();
        }
    }
}
