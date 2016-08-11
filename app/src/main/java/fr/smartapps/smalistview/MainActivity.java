package fr.smartapps.smalistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SMAListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setListView();
    }

    protected void setListView() {
        SMAListView listView = (SMAListView) findViewById(R.id.list);
        if (listView != null) {
            listView.initData(1, getDataViews(), this);
        }
    }

    protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // title
        SMADataView dataTitle = new SMADataView(R.layout.list_title);
        dataTitle.setTitle("SMAListView Showcase");

        // text
        SMADataView dataText = new SMADataView(R.layout.list_text);
        dataText.setTitle("Welcome to SMAListView showcase. Here you can see every possibilities this library can offer with a very easy implementation.");

        // options 1
        SMADataView dataOptionGrid = new SMADataView(R.layout.list_button);
        dataOptionGrid.setTitle("GridView");
        dataOptionGrid.setId(1);

        // options 2
        SMADataView dataOptionList = new SMADataView(R.layout.list_button);
        dataOptionList.setTitle("ListView");
        dataOptionList.setId(2);

        // options 3
        SMADataView dataOptionAsyncList = new SMADataView(R.layout.list_button);
        dataOptionAsyncList.setTitle("Asynchronous ListView");
        dataOptionAsyncList.setId(3);

        // options 4
        SMADataView dataOptionRefreshList = new SMADataView(R.layout.list_button);
        dataOptionRefreshList.setTitle("Refresh ListView");
        dataOptionRefreshList.setId(4);

        // add each view
        result.add(dataTitle);
        result.add(dataText);
        result.add(dataOptionGrid);
        result.add(dataOptionList);
        result.add(dataOptionAsyncList);
        result.add(dataOptionRefreshList);

        // return list of view to display them in the listView
        return result;
    }

    @Override
    public void onBindViewHolder(View itemView, final SMADataView dataView) {
        // title
        TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
        if (titleView != null && dataView.getTitle() != null) {
            titleView.setText(dataView.getTitle());
        }

        // options
        if (dataView.resourceViewIdx == R.layout.list_button) {
            Button buttonGo = (Button) itemView.findViewById(R.id.row_button);

            if (buttonGo == null)
                return;

            // define each GO button onClickListener thX to ID
            buttonGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (dataView.getId()) {
                        case 1:
                            Intent intentGrid = new Intent(getApplicationContext(), GridActivity.class);
                            startActivity(intentGrid);
                            break;
                        case 2:
                            Intent intentList = new Intent(getApplicationContext(), ListActivity.class);
                            startActivity(intentList);
                            break;
                        case 3:
                            Intent intentAsyncList = new Intent(getApplicationContext(), AsyncListActivity.class);
                            startActivity(intentAsyncList);
                            break;
                        case 4:
                            Intent intentRefreshList = new Intent(getApplicationContext(), RefreshListActivity.class);
                            startActivity(intentRefreshList);
                            break;
                    }
                }
            });
        }
    }
}
