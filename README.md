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
            	android:orientation="horizontal"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"/>
 
                
2 - Create some cells layout to add to your list :

**layout_image_and_title.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content">
	    
	    <ImageView
                android:id="@+id/row_image"
		android:layout_width="40dp"
		android:layout_height="40dp"/>
                
            <TextView
	        android:id="@+id/row_title"
	        android:layout_margin="10dp"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content" />
	
	</LinearLayout>

**layout_title.xml**

	<?xml version="1.0" encoding="utf-8"?>
	<LinearLayout
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="wrap_content">

            <TextView
	        android:id="@+id/row_title"
	        android:layout_margin="10dp"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content" />
	
	</LinearLayout>
	
	
3 - Initialize **SMAListView** : 
**numberOfColumn** : indicate the number of column in your list
**getDataViews()** : populate your list with this method
**SMAListListener()** : interface to implement to access onBindViewHolder()

	listView.initData(numberOfColumn, getDataViews(), new SMAListListener() {
	@Override
	public void onBindViewHolder(View itemView, SMADataView dataView) {
        	[ ... next step ... ]
        }
	});



4 - Set datas : here I add 2 differents cells (one with a title and a second one with a title and an image) :

	protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        	// data title
		SMADataView dataView1 = new SMADataView(R.layout.layout_title);
		dataView1.setTitle("Title 1");  
		result.add(dataView1);

		// data image & title
		SMADataView dataView2 = new SMADataView(R.layout.layout_image_and_title);
		dataView2.setTitle("Title 2");
		dataView2.setImage(url_image);
		result.add(dataView2);

		return result;
	}


5 - Use your datas in the callback : 

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

# More

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
