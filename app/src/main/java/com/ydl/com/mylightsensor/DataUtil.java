package com.ydl.com.mylightsensor;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ydl on 2018/4/8 0008.
 */

public class DataUtil {

    public String datastr;
    private HashMap<String, List<String>> mMap = new HashMap<>();
    private HashMap<String,String> mMap2 = new HashMap<>();

    public DataUtil(Context context){

    }

    public ArrayList<String> getplaceByhouse(String houseStr,Context context) {
        if (houseStr=="住宅建筑"){
            datastr = context.getResources().getString(R.string.residential_building);
        }else if (houseStr=="图书馆建筑"){
            datastr = context.getResources().getString(R.string.library_building);
        }else if (houseStr=="办公建筑"){
            datastr = context.getResources().getString(R.string.office_building);
        }else if (houseStr=="商店建筑"){
            datastr = context.getResources().getString(R.string.shop_building);
        }else if (houseStr=="观演建筑"){
            datastr = context.getResources().getString(R.string.view_building);
        }else if (houseStr=="旅馆建筑"){
            datastr = context.getResources().getString(R.string.hotel_building);
        }else if (houseStr=="医疗建筑"){
            datastr = context.getResources().getString(R.string.medical_building);
        }else if (houseStr=="教育建筑"){
            datastr = context.getResources().getString(R.string.educational_building);
        }
        mMap.clear();
        mMap2.clear();
        String[] norstr = datastr.split("#");
        for (String str:norstr){
            String place = str.split(":")[0];
            String doall = str.split(":")[1];
            List<String> doList = Arrays.asList(doall.split(";"));
            mMap.put(place,doList);
        }
        ArrayList<String> arrList = new ArrayList<>();
        for (String str2 : norstr) {
            String place = str2.split(":")[0];
            arrList.add(place);
        }
        return arrList;
    }

    public ArrayList<String> getdoByplace(String placeStr,Context context){
        List<String> list = mMap.get(placeStr);
        for (String str:list){
            String dothing = str.split("!!")[0];
            String last = str.split("!!")[1];
            mMap2.put(dothing,last);
        }
        ArrayList<String> arrList = new ArrayList<>();
        for (String dot : list) {
            String dothing = dot.split("!!")[0];
            arrList.add(dothing);
        }
        return arrList;
    }

    public void setXbydo(String dothing,Context context){
        String last = mMap2.get(dothing);
        String posi = last.split("!")[0];
        String num = last.split("!")[1];
        DashboardView.X = Integer.parseInt(num.trim());
        MainActivity.txttip.setText("请将手机放置于 "+posi+"\r\n当前照度标准值为 "+num+"lux");
    }
}
