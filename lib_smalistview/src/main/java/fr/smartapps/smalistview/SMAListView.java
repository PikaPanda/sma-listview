package fr.smartapps.smalistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by vincentchann on 07/08/16.
 */
public class SMAListView extends RecyclerView {

    /*
    Attributes
     */
    protected Context context;
    protected int columnNumber = 1;
    protected StaggeredGridLayoutManager staggeredGridLayoutManager;
    protected SMAAdapter SMAAdapter;

    /*
    Default constructors
     */
    public SMAListView(Context context) {
        super(context);
        this.context = context;
    }

    public SMAListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SMAListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    /*
    Callback
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (SMAAdapter != null) {
            setAdapter(SMAAdapter);
            setHasFixedSize(true);
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(columnNumber, VERTICAL);
            setLayoutManager(staggeredGridLayoutManager);
        }
    }

    public void initData(int columnNumber, List<SMADataView> dataViews, SMAListListener SMAListListener) {
        this.SMAAdapter = new SMAAdapter(dataViews, SMAListListener);
        this.columnNumber = columnNumber;
    }


}
