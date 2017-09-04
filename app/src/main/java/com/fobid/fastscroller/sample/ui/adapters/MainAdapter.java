package com.fobid.fastscroller.sample.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fobid.fastscroller.sample.R;
import com.fobid.fastscroller.sample.models.State;
import com.fobid.fastscroller.ui.adapters.FastScrollerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FastScrollerAdapter<RecyclerView.ViewHolder> {

    private final List<State> states;

    public MainAdapter() {
        states = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.i_state, parent, false);
        return new StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StateViewHolder) holder).bindData(states.get(position));

    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public boolean updateData(final @NonNull List<State> states) {
        final boolean result = this.states.addAll(states);
        notifyDataSetChanged();
        return result;
    }

    @NonNull
    @Override
    public String getSectionTitle(int position) {
        return states.get(position).name;
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView location;

        public StateViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            location = itemView.findViewById(R.id.location);
        }

        public void bindData(final @NonNull State item) {
            name.setText(item.name);
            location.setText(item.location);
        }
    }
}
