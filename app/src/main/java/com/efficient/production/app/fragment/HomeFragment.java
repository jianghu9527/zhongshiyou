package com.efficient.production.app.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bigemap.bmcore.BMEngine;
import com.bigemap.bmcore.EarthFragment;
import com.bigemap.bmcore.entity.BoundingBox;
import com.bigemap.bmcore.entity.CustomMapSource;
import com.bigemap.bmcore.entity.GeoPoint;
import com.bigemap.bmcore.entity.MapConfig;
import com.bigemap.bmcore.entity.Provider;
import com.bigemap.bmcore.entity.VectorElement;
import com.bigemap.bmcore.listener.OperationCallback;
import com.efficient.production.app.R;
import com.efficient.production.app.ui.MyApplication;
import com.efficient.production.app.utils.LLogUtils;
import com.efficient.production.app.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OperationCallback {

    public HomeFragment() {
        // Required empty public constructor
    }

    private boolean isInit = false;
    private boolean isFirst = true;
    View homeView;

//    private final static String TEST_MAP_SOURCE_URL1 = "http://services.arcgisonline.com/ArcGIS/services/World_Imagery/MapServer?mapname=Layers&layer=_alllayers&format=PNG&level={z}&row={y}&column={x}";
//    private final static String TEST_MAP_SOURCE_URL2 = "https://map.geoq.cn/arcgis/rest/services/ChinaOnlineStreetWarm/MapServer/tile/{z}/{y}/{x}";
//



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeView=inflater.inflate(R.layout.fragment_home, container, false);
        initView(homeView);
        return homeView;
    }
    private EarthFragment mEarthFragment;
    private final static String TAG_EARTH_FRAGMENT = "TAG_EARTH_FRAGMENT";
    void initView(  View homeView){

        BMEngine.preInit(getActivity(), "af655b5079055e38f095313c27a84345");
        // 1、Context 2、自定义图标存放位置 3、是否加载地形
        BMEngine.init(getActivity(), getActivity().getFilesDir().getPath() + File.separator, false);

        //拷贝文件到文件系统
        Utils.INSTANCE.copyAssets(getActivity(), "img", getActivity().getFilesDir().getPath());
        Utils.INSTANCE.copyAssets(getActivity(), "map",getActivity().getFilesDir().getPath());

//        Utils.INSTANCE.copyAssets(this, "同名图标", BMEngine.getIconPath());

        // 添加在线地图
//        addMapSource(TEST_MAP_SOURCE_URL1);
//        addMapSource(TEST_MAP_SOURCE_URL2);
        // 添加离线地图
        addOfflineMap(homeView);

        onCreateBaseMap();

        mEarthFragment = EarthFragment.getInstance(this);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.flt_container, mEarthFragment);
        transaction.commitAllowingStateLoss();

        new Handler().postDelayed(() -> {
//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            LLogUtils.d("-------------------HomeFragment-------延时加载------------initView-------");



            if (mEarthFragment==null){
                LLogUtils.d("-------------------HomeFragment-------------------initView---null----");

            }else{
                LLogUtils.d("-------------------HomeFragment-------------------initView----1---"+isInit);
            }
//            mEarthFragment = EarthFragment.getInstance(this);

//            if (!isInit) {
//                transaction.add(R.id.flt_container, mEarthFragment);
//                transaction.commitAllowingStateLoss();
//                isInit = true;
//            } else {
//                transaction.show(mEarthFragment);
//                transaction.commitAllowingStateLoss();
//            }

        }, 1000);


//        FragmentTransaction transaction = MyApplication.Companion.getInstan().getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.flt_container, mEarthFragment, TAG_EARTH_FRAGMENT);
//        transaction.commitAllowingStateLoss();











//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                VectorElement element = mEarthFragment.getThisElementAttribute(id);
//                element.geoPoints.add(new GeoPoint(113.565, 22.161));
//                element.outlineColor = "#FFFF0000";
//                mEarthFragment.setThisElementAttribute(element);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        VectorElement element = mEarthFragment.getThisElementAttribute(id);
//                        element.geoPoints.add(new GeoPoint(113.566, 22.162));
//                        element.outlineColor = "#FF00FF00";
//                        mEarthFragment.setThisElementAttribute(element);
//                    }
//                }, 2000);
//            }
//        }, 2000);


//        new Handler().postDelayed(() -> {
//            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//            LLogUtils.d("-------------------HomeFragment-------延时加载------------initView-------");
//
//
//
//            if (mEarthFragment==null){
//                LLogUtils.d("-------------------HomeFragment-------------------initView---null----");
//
//            }else{
//                LLogUtils.d("-------------------HomeFragment-------------------initView----1---");
//            }
//            mEarthFragment = EarthFragment.getInstance(this);
//
//
//            if (!isInit) {
//                transaction.add(R.id.flt_container, mEarthFragment);
//                transaction.commitAllowingStateLoss();
//                isInit = true;
//            } else {
//                transaction.show(mEarthFragment);
//                transaction.commitAllowingStateLoss();
//            }
//
//        }, 1000);


    }
    public void addOfflineMap( View homeView) {
        String name = "澳门特别行政区_卫图";
        String icon = "";
        String path = homeView.getContext().getFilesDir().getPath() + File.separator + "澳门特别行政区_卫图.bmpkg";
        List<String> strings = new ArrayList<>();
        strings.add(path);
        BMEngine.addOfflineMap(name, icon, strings);
    }




    public void addMapSource(String url) {
        // 1、创建自定义地图源
        CustomMapSource custom = new CustomMapSource();
        custom.name = "Arcgis";
        custom.url = url;// 瓦片链接地址
        custom.tileSize = 256;// 瓦片大小
        custom.projection = true;// 投影true:墨卡托，false:经纬度直投
        custom.minLevel = 0;// 最小层级
        custom.maxLevel = 19;// 最大层级
        custom.cacheKey = "ArcGis";// 缓存标识

        // 2、加入引擎中
        BMEngine.addCustomMapSource(custom);

//        // 1、创建自定义地图源
//        CustomMapSource custom = new CustomMapSource();
//        custom.name = "Arcgis";
//        custom.url = url;// 瓦片链接地址
//        custom.tileSize = 256;// 瓦片大小
//        custom.projection = true;// 投影true:墨卡托，false:经纬度直投
//        custom.minLevel = 0;// 最小层级
//        custom.maxLevel = 19;// 最大层级
//        custom.cacheKey = "11";// 最大层级
//
//        List<CustomMapSource> list = new ArrayList<>();
//        list.add(custom);
//
//        // 2、加入引擎中
//        BMEngine.addMapLayerList("111", "111", list);
    }



    private void onCreateBaseMap() {
        List<CustomMapSource> list = new ArrayList<>();
        onBuildBaseMapLayerList(list);
        BMEngine.addMapLayerList("地图源名称", "图标", list);
    }
    private void onBuildBaseMapLayerList(List<CustomMapSource> list) {
        CustomMapSource custom1 = new CustomMapSource();
        custom1.name = "卫星地图底图";
        custom1.url = "http://101.204.24.18:3000/bigemap.dc-satellite/merc/tiles/{z}/{x}/{y}.jpg?access_token=pk.eyJhIjoiNjZybnpzaGd0NnY4NnZxNDR4a3I4bXVmeiIsInQiOjMsInUiOiIxOTkwMDAwMDAwMDAifQ.AGjsWqbd6T5bGteh_0cioY5c-XR-h3GtJM_XMlmDPBQ";
        custom1.tileSize = 256;
        custom1.projection = true;
        custom1.minLevel = 0;
        custom1.maxLevel = 19;
        custom1.cacheKey = "image";
        list.add(custom1);

        CustomMapSource custom2 = new CustomMapSource();
        custom2.name = "地图路网";
        custom2.url = "http://101.204.24.18:3000/bigemap.dc-street/merc/tiles/{z}/{x}/{y}.png?access_token=pk.eyJhIjoiNjZybnpzaGd0NnY4NnZxNDR4a3I4bXVmeiIsInQiOjMsInUiOiIxOTkwMDAwMDAwMDAifQ.AGjsWqbd6T5bGteh_0cioY5c-XR-h3GtJM_XMlmDPBQ";
        custom2.tileSize = 256;
        custom2.projection = true;
        custom2.minLevel = 0;
        custom2.maxLevel = 19;
        custom2.cacheKey = "route";
        list.add(custom2);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("-------------HomeFragment---------","----HomeFragment-----onDestroy-------------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("-------------HomeFragment---------","----HomeFragment-----onResume-------------");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("-------------HomeFragment---------","---HomeFragment------onAttach-------------");
    }

    @Override
    public void onCreateEarthComplete() {
        BMEngine.isShowBuilding(false);
        setMapSource();
        LLogUtils.d("--------------------------------onCreateEarthComplete----------");
        onAnimateTo(new GeoPoint(104.06303644, 30.56918947));
    }
    /**
     * 设置地图来源
     */
    private void setMapSource() {
        List<Provider> providers = BMEngine.getMapProviders();

        if (!providers.isEmpty()){
            Provider provider = providers.get(providers.size() - 1);
//            Provider provider = providers.get(0);
            LLogUtils.d("----------------------------size-----------------"+providers.size());
            if (provider!=null){
                LLogUtils.d("-----------------------------1------provider----------"+provider.mapId);
                if (mEarthFragment!=null){
                    mEarthFragment.changeMapSource(provider.mapId);
                }else{
                    LLogUtils.d("-----------------------------2------mEarthFragment--------null--");
                }

            }else{
                LLogUtils.d("-----------------------------2------provider--------null--");
            }
        }
    }
    private void onAnimateTo(GeoPoint geoPoint) {
        // 视角高度
        int height = 3000;

        // 跳转时间
        double time = 0.0;

        // 俯仰角度
        double pitch = -90.0;
        LLogUtils.d("-----------------------------geoPoint:"+geoPoint.lon);
        LLogUtils.d("-----------------------------geoPoint:"+geoPoint.lat);

        mEarthFragment.animateTo(geoPoint, height, time, pitch);
    }



    @Override
    public void onCreateEarthFail(int i) {

    }

    @Override
    public void onScroll() {

    }

    @Override
    public void callbackScreenCenterPoint(GeoPoint geoPoint, double v, long l) {

    }

    @Override
    public void callbackEarthOrientation(float v) {

    }

    @Override
    public void onSingleTapConfirmed(MotionEvent motionEvent) {
//        toAddPointInMap(geoPoint.getLon(), geoPoint.getLat());
    }

    @Override
    public void onClickedElement(VectorElement vectorElement) {

    }

    @Override
    public void onChangeMapSourceComplete(MapConfig mapConfig) {

    }

    @Override
    public void onChangeMapTypeGroupComplete(MapConfig mapConfig) {

    }

    @Override
    public void onCallbackHistoricalImagery(int[] ints) {

    }

    @Override
    public void onCallbackHistoricalImagery(String[] strings) {

    }
    private List<GeoPoint> mPointList;
    @Override
    public void onCallbackDrawElementStepEditing(VectorElement vectorElement) {
//        mPointList = vectorElement.geoPoints;
//
//        List<DynamiteDownloadMap> data = new ArrayList<>();
//        data.add(new DynamiteDownloadMap(7, "显示国家的边界，主要城市和一些重要的地理特征", onCalculation(7)));
//        data.add(new DynamiteDownloadMap(8, "细化到州或省的范围，可以显示更多的城市和道路", onCalculation(8)));
//        data.add(new DynamiteDownloadMap(9, "继续细分，可以看到更多城市的街道和一些小型地理特征", onCalculation(9)));
//        data.add(new DynamiteDownloadMap(10, "显示国家内大部分城市的街道和建筑物等信息", onCalculation(10)));
//        data.add(new DynamiteDownloadMap(11, "显示城市范围，包括主要的街道和建筑物", onCalculation(11)));
//        data.add(new DynamiteDownloadMap(12, "细致到可以看到一些小巷和公园等", onCalculation(12)));
//        data.add(new DynamiteDownloadMap(13, "显示更多的建筑物、商店等细节", onCalculation(13)));
//        data.add(new DynamiteDownloadMap(14, "提供较为详细的城市地图信息", onCalculation(14)));
//        data.add(new DynamiteDownloadMap(15, "显示城市中的街道网络和建筑物，包括一些小型地理特征", onCalculation(15)));
//        data.add(new DynamiteDownloadMap(16, "继续细化，显示更多的商店、停车场等信息", onCalculation(16)));
//        data.add(new DynamiteDownloadMap(17, "显示非常详细的城市地图，可以看到建筑物的轮廓等", onCalculation(17)));
//        data.add(new DynamiteDownloadMap(18, "最高层级，提供市区内几乎所有细节，包括建筑物的细节和标注等", onCalculation(18)));
//        mDownloadMapInfoAdapter.setNewData(data);
//
//        requireActivity().runOnUiThread(() -> {
//            mEarthFragment.toStopDrawElement();
//            mEarthFragment.toCancelDrawingElement();
//            layoutOperationDraw.setSelected(false);
//            layoutDrawDownload.setEnabled(true);
//        });

        getActivity().  runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 5、结束当前绘制对象，启动新的绘制
                mEarthFragment.toStopDrawElement();
                // 6、退出绘制
                mEarthFragment.toCancelDrawingElement();
                Log.e("===", "==onCallbackDrawElementStepEditing==");
            }
        });
    }
//    private String onCalculation(int level) {
//        BoundingBox box = BoundingBox.fromGeoPoints(mPointList);
//        int size = TilesUtils.getTilesCoverageIterable(box, level, level).size();
//        int tileSize = PREVIEW_SIZE;//12
//        float kb = size * tileSize;
//        float mb = size * tileSize / 1024f;
//        float gb = size * tileSize / 1024f / 1024f;
//        float tb = size * tileSize / 1024f / 1024f / 1024f;
//        String value;
//
//        if (tb > 1) {
//            value = tb + "TB";
//        } else if (gb > 1) {
//            value = gb + "GB";
//        } else if (mb > 1) {
//            value = mb + "MB";
//        } else {
//            value = kb + "KB";
//        }
//
//        return value;
//    }
    @Override
    public void onCallbackDrawElementStepCreated(VectorElement vectorElement) {

    }

    @Override
    public void onCallbackAddedTrackPoint(GeoPoint geoPoint) {

    }

    @Override
    public void onLoadVectorFileStart(int i) {

    }

    @Override
    public void onLoadVectorFileDoing() {

    }

    @Override
    public void onLoadVectorFileComplete(boolean b, long l) {

    }

    @Override
    public void onLoadVectorFileComplete(VectorElement vectorElement) {

    }

    @Override
    public byte[] onFormatStringToPicture(String s) {
        return new byte[0];
    }

    @Override
    public byte[] webPToPng(byte[] bytes) {
        return new byte[0];
    }

    @Override
    public boolean onUpdateOfflineCallback(int i, int i1) {
        return false;
    }
}