package fr.smartapps.smalistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SMAListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();
    }

    protected void setListView() {
        SMAListView listView = (SMAListView) findViewById(R.id.list);
        listView.initData(2, getDataViews(), this);
    }

    protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // data 0
        SMADataView dataView0 = new SMADataView(R.layout.list_title);
        dataView0.setFullWidth(true);
        dataView0.setTitle("Title 0");
        result.add(dataView0);

        // data 1
        SMADataView dataView1 = new SMADataView(R.layout.list_stretchable_card_rectangle);
        dataView1.setFullWidth(true);
        dataView1.setTitle("Title 1");
        dataView1.setSubtitle("Subtitle 1");
        dataView1.setImage("file:///android_asset/media9239_M.jpg");
        result.add(dataView1);

        for (int position = 2; position < 100; position++) {
            SMADataView dataView = new SMADataView(R.layout.list_stretchable_card_square);
            dataView.setTitle("Title " + position);
            dataView.setTitle("Subtitle " + position);
            switch (position % 6) {
                case 0:
                    dataView.setImage("file:///android_asset/media9240_M.jpg");
                    break;
                case 1:
                    dataView.setImage("file:///android_asset/media9241_M.jpg");
                    break;
                case 2:
                    dataView.setImage("file:///android_asset/media9242_M.jpg");
                    break;
                case 3:
                    dataView.setImage("file:///android_asset/media9243_M.jpg");
                    break;
                case 4:
                    dataView.setImage("file:///android_asset/media9244_M.jpg");
                    break;
                case 5:
                    dataView.setImage("file:///android_asset/media9245_M.jpg");
                    break;
                case 6:
                    dataView.setImage("file:///android_asset/media9239_M.jpg");
                    break;
            }
            result.add(dataView);
        }

        return result;
    }

    @Override
    public void onBindViewHolder(View itemView, SMADataView dataView) {
        // title
        TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
        if (titleView != null && dataView.getTitle() != null) {
            titleView.setText(dataView.getTitle());
        }

        // subtitle
        TextView subtitleView = (TextView) itemView.findViewById(R.id.row_subtitle);
        if (subtitleView != null && dataView.getSubtitle() != null) {
            subtitleView.setText(dataView.getSubtitle());
        }

        // image
        ImageView imageView = (ImageView) itemView.findViewById(R.id.row_image);
        if (imageView != null && dataView.getImage() != null) {
            Glide.with(this).load(dataView.getImage()).into(imageView);
        }

        // special case
        switch (dataView.resourceViewIdx) {
            case R.layout.list_stretchable_card_rectangle:
                break;
            case R.layout.list_stretchable_card_square:
                break;
            case R.layout.list_title:
                break;
        }
    }
}
