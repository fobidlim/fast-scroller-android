package com.fobid.fastscroller.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public abstract class FastScrollerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public abstract
    @NonNull
    String getSectionTitle(int position);
}
