# SMAListView

## Problem

RecyclerView has always been difficult to use.
You need at least 3 classes to use it :

* an **adapter** to manage its cycle of life
* a **viewHolder** to customize each view
* a **dataView** to manage differents data for each cell

You also need 1 more interface to manage clicks callback.
And sometimes few more viewHolders and dataViews to manage differents views in the same list.
Not to mention, decorator and animator classes =).


## Solution

Those hard times are over now.
I introduce you the **SMAListView** that is SO simple to use.

1 - Add the widget **SMAListView** to your layout :

	<fr.smartapps.smalistview.SMAListView
		android:id="@+id/list"
		android:layout_width="match_parent"
		android:layout_height="match_parent"/>


2 - Initialize **SMAListView** :

	listView.initData(numberOfColumn, getDataViews(), new SMAListListener() {
	@Override
	public void onBindViewHolder(View itemView, SMADataView dataView) {
        [ ... next step ... ]
        }
	});



3 - Set datas :

	protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // data 1
		SMADataView dataView1 = new SMADataView(YourLayout1);
		dataView1.setTitle("Title 1");  
		result.add(dataView1);

		// data 2
		SMADataView dataView2 = new SMADataView(YourLayout2);
		dataView2.setTitle("Title 2");
		dataView2.setImage(url_image);
		result.add(dataView2);

		return result;
	}


4 - Use your datas in the callback : 

	@Override
	public void onBindViewHolder(View itemView, SMADataView dataView) {
		// set title
		TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
		if (titleView != null) {
		titleView.setText(dataView.getTitle());
		}

		// set image
		ImageView imageView = (ImageView) itemView.findViewById(R.id.row_image);
		if (imageView != null) {
			Glide.with(this).load(dataView.getImage()).into(imageView);
		}
	}

## More : set data

You have way more possibilities to set common datas :

* setTitle(String title);
* setSubtitle(String subtitle);
* setImage(String urlImage);
* setIcon(String urlIcon);

If you need more flexibility :

* setString(String key, String value);
* setInt(String key, int value);
* setBoolean(String key, boolean value);

And for the best of you :

* set(String key, Object value);
* get(String key);

Obviously all of these setters have their getters !

## More : Bonus

With multiple column (more than 1) :

* if your layouts have the same height, you will get a GRID.
* if your layouts have different heights, you will get asynchronous GRID.

You can set a layout to take every columns with the method :

* setFullWidth(true);
