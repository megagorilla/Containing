package com.nhl.containing;

import java.util.ArrayList;

import org.json.JSONArray;

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
	public static ArrayList<String> list1, list2, list3, list4, list5, list6, list7;
	
	public GraphView(LineChart lc) {
		mChart = lc;
		mChart.setDescription("All containers");
		setData(1);
		//api = new APIHandler("http://feenstraim.com/api.php");
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		list3 = new ArrayList<String>();
		list4 = new ArrayList<String>();
		list5 = new ArrayList<String>();
		list6 = new ArrayList<String>();
		list7 = new ArrayList<String>();
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
		
		System.out.println("GraphView.setData()");

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
			
		// Tijdelijke variabelen
		int count = 45;
		float range = 100;
		
//		try {
//			JSONArray js = api.getData();
//			System.out.println(js);
//		} catch (Exception ex) {
//			System.out.println("No Data Found");
//		}
		
		// Hier data verzamelen uit JSON
//		if (type > 0) {
//			JSONArray data = api.getData();
//			
//			if (data != null) {
//				try {
//					Object inputData = data.get(type);
//					// Hier verder afhandelen
//					
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//			}
//		} // enz.

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }
        
        ArrayList<String> yData = new ArrayList<String>();
        yData.add("13:10");
        yData.add("13:11");
        yData.add("13:12");
        yData.add("13:13");
        yData.add("13:14");
        yData.add("13:15");

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 3;// + (float)
                                                           // ((mult *
                                                           // 0.1) / 10);
            yVals.add(new Entry(val, i));
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
	
	/**
	 * This method returns the current Chart
	 * @return A LineChart object filled with data
	 */
	public LineChart getChart() {
		return mChart;
	}

	public void update() {
		
		int count = 45;
		float range = 100;
		
		ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i+=2) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i+=2) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 3;// + (float)
                                                           // ((mult *
                                                           // 0.1) / 10);
            yVals.add(new Entry(val, i));
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