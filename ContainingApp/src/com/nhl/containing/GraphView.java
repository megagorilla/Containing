package com.nhl.containing;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.LimitLine;
import com.github.mikephil.charting.utils.LimitLine.LimitLabelPosition;

public class GraphView {

	private LineChart mChart;
	private APIHandler api;
	private ArrayList<Integer> mData;
	
	public GraphView(LineChart lc) {
		mChart = lc;
		
		mChart.setDescription("All containers");
		
		setData(1);
		
		api = new APIHandler("http://127.0.0.1");
	}
	
	public void setDescription(String desc) {
		this.mChart.setDescription(desc);
	}
	
	public void setData(int type) {
			
		// Tijdelijke variabelen
		int count = 45;
		float range = 100;
		
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
	
	public LineChart getChart() {
		return mChart;
	}
}