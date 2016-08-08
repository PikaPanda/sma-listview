package fr.smartapps.smalistview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by vincentchann on 07/08/16.
 */
public class SMAAdapter extends RecyclerView.Adapter<SMAViewHolder> {

    protected SMAListListener SMAListListener;
    protected List<SMADataView> dataViews;

    /*
    Constructor
    */
    public SMAAdapter(List<SMADataView> dataViews, SMAListListener SMAListListener) {
        this.dataViews = dataViews;
        this.SMAListListener = SMAListListener;
    }

    /*
    Callbacks
     */
    @Override
    public int getItemCount() {
        if (dataViews != null) {
            return dataViews.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public SMAViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        if (dataViews != null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new SMAViewHolder(inflater.inflate(dataViews.get(position).resourceViewIdx, parent, false));
        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(SMAViewHolder holder, int position) {
        if (SMAListListener != null && dataViews != null) {
            SMAListListener.onBindViewHolder(holder.itemView, dataViews.get(position));

            // full span layout
            if (dataViews.get(position).getFullWidth()) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(true);
                holder.itemView.setLayoutParams(layoutParams);
            }
            else {
                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setFullSpan(false);
                holder.itemView.setLayoutParams(layoutParams);
            }
        }
    }

    public void reloadData(List<SMADataView> dataViews) {
        this.dataViews = dataViews;
        notifyDataSetChanged();
    }
}
