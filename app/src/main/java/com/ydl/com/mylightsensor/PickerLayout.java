package com.ydl.com.mylightsensor;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.pl.wheelview.WheelView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ydl on 2018/4/8 0008.
 */

public class PickerLayout extends LinearLayout {
    private WheelView mhousePicker;
    private WheelView mplacePicker;
    private WheelView mdothingPicker;

    private int mCurrhouseIndex = -1;
    private int mCurrplaceIndex = -1;
    private int mCurrdothingIndex = -1;

    private DataUtil mDataUtil;
    private ArrayList<String> mbuildingList = new ArrayList<String>(Arrays.asList("住宅建筑","图书馆建筑","办公建筑","商店建筑","观演建筑","旅馆建筑","医疗建筑","教育建筑"));

    public PickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDataUtil = new DataUtil(getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.layout_picker, this);
        mhousePicker = (WheelView) findViewById(R.id.house_wv);
        mplacePicker = (WheelView) findViewById(R.id.place_wv);
        mdothingPicker = (WheelView) findViewById(R.id.dothing_wv);

        mhousePicker.setData(mbuildingList);
        mhousePicker.setDefault(0);

        String defaultbuilding = mbuildingList.get(0);
        mplacePicker.setData(mDataUtil.getplaceByhouse(defaultbuilding,getContext()));
        mplacePicker.setDefault(0);

        String defaultplace = mDataUtil.getplaceByhouse(defaultbuilding,getContext()).get(0);
        mdothingPicker.setData(mDataUtil.getdoByplace(defaultplace,getContext()));
        mdothingPicker.setDefault(0);

        mhousePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                Log.e("Picker selecting","id="+id+", text="+text);
                if (text.equals("") || text == null)
                    return;
                if (mCurrhouseIndex != id) {
                    mCurrhouseIndex = id;
                }
                ArrayList<String> places = mDataUtil.getplaceByhouse(text,getContext());
                mplacePicker.setData(places);
                mplacePicker.setDefault(0);

                String placestr = places.get(0);
                mdothingPicker.setData(mDataUtil.getdoByplace(placestr,getContext()));
                mdothingPicker.setDefault(0);
                mDataUtil.setXbydo(mDataUtil.getdoByplace(placestr,getContext()).get(0),getContext());
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        mplacePicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                if (mCurrplaceIndex != id) {
                    mCurrplaceIndex = id;
                }
                ArrayList<String> dostrs = mDataUtil.getdoByplace(text,getContext());
                mdothingPicker.setData(dostrs);
                mdothingPicker.setDefault(0);
                mDataUtil.setXbydo(dostrs.get(0),getContext());
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
        mdothingPicker.setOnSelectListener(new WheelView.OnSelectListener() {
            @Override
            public void endSelect(int id, String text) {
                if (text.equals("") || text == null)
                    return;
                mDataUtil.setXbydo(text,getContext());
            }

            @Override
            public void selecting(int id, String text) {

            }
        });
    }
}
