package com.nhl.containing;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;

import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.LimitLine;
import com.github.mikephil.charting.utils.LimitLine.LimitLabelPosition;

public class GraphView {

	public static LineChart mChart;
	private APIHandler api;
	public static ArrayList<ListData> list1, list2, list3, list4, list5, list6, list7;
	Context ctx;
	public int currentList;
	
	public GraphView(LineChart lc, Context _ctx) {
		mChart = lc;
		mChart.setDescription("All containers");
		setData(1);
		//api = new APIHandler("http://feenstraim.com/api.php");
		list1 = new ArrayList<ListData>();
		list2 = new ArrayList<ListData>();
		list3 = new ArrayList<ListData>();
		list4 = new ArrayList<ListData>();
		list5 = new ArrayList<ListData>();
		list6 = new ArrayList<ListData>();
		list7 = new ArrayList<ListData>();
		this.ctx = _ctx;
	}
	
	/**
	 * Sets another description
	 * @param desc The description to set
	 */
	public void setDescription(String desc) {
		mChart.setDescription(desc);
	}
	
	/**
	 * Sets the data showed in the Chart
	 */
	public void setData(int type) {

		currentList = type;
		
		switch (type) {
			case 1:
				this.setDescription("All containers");
				break;
			case 2:
				this.setDescription("Train platform");
				break;
			case 3:
				this.setDescription("Truck platform");
				break;
			case 4:
				this.setDescription("Ship platform");
				break;
			case 5:
				this.setDescription("Seaship platform");
				break;
			case 6:
				this.setDescription("Storage");
				break;
			case 7:
				this.setDescription("Others");
				break;
		}
    }
	
	/**
	 * This method returns the current Chart
	 * @return A LineChart object filled with data
	 */
	public LineChart getChart() {
		return mChart;
	}

	public void update() {

		this.list2.add(new ListData(System.currentTimeMillis(), Constants.getStorage(this.ctx, "Train")));
		this.list3.add(new ListData(System.currentTimeMillis(), Constants.getStorage(this.ctx, "Truck")));
		this.list4.add(new ListData(System.currentTimeMillis(), Constants.getStorage(this.ctx, "Seaship")));
		this.list5.add(new ListData(System.currentTimeMillis(), Constants.getStorage(this.ctx, "Ship")));
		this.list6.add(new ListData(System.currentTimeMillis(), Constants.getStorage(this.ctx, "Others")));
		
		System.out.println("In de lijst zit: "+list2);

		int count = 0;
		float range = 100;

		
		ArrayList<String> xVals = new ArrayList<String>();

        ArrayList<Entry> yVals = new ArrayList<Entry>();
		
		if ((currentList == 1) || (currentList == 2)) {
			count = list2.size();
			int i = 0;
	        for(ListData d : list2) {
	        	Date date = new java.util.Date(d.getTime());
	        	xVals.add(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	        	yVals.add(new Entry(d.getAantal(), i));
	        	i++;
	        }
		}
		if (currentList == 3) {
			count = list3.size();
			int i = 0;
	        for(ListData d : list3) {
	        	Date date = new java.util.Date(d.getTime());
	        	xVals.add(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	        	yVals.add(new Entry(d.getAantal(), i));
	        	i++;
	        }
		}
		if (currentList == 4) {
			count = list4.size();
			int i = 0;
	        for(ListData d : list4) {
	        	Date date = new java.util.Date(d.getTime());
	        	xVals.add(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	        	yVals.add(new Entry(d.getAantal(), i));
	        	i++;
	        }
		}
		if (currentList == 5) {
			count = list5.size();
			int i = 0;
	        for(ListData d : list5) {
	        	Date date = new java.util.Date(d.getTime());
	        	xVals.add(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	        	yVals.add(new Entry(d.getAantal(), i));
	        	i++;
	        }
		}
		if (currentList == 6) {
			count = list6.size();
			int i = 0;
	        for(ListData d : list6) {
	        	Date date = new java.util.Date(d.getTime());
	        	xVals.add(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	        	yVals.add(new Entry(d.getAantal(), i));
	        	i++;
	        }
		}

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "# of containers");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleSize(4f);
        set1.setFillAlpha(65);
        set1.setFillColor(Color.BLACK);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        LimitLine ll1 = new LimitLine(130f);
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setDrawValue(true);
        ll1.setLabelPosition(LimitLabelPosition.RIGHT);

        LimitLine ll2 = new LimitLine(-30f);
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setDrawValue(true);
        ll2.setLabelPosition(LimitLabelPosition.RIGHT);

        data.addLimitLine(ll1);
        data.addLimitLine(ll2);

        // set data
        mChart.setData(data);
        mChart.postInvalidate();
	}
}