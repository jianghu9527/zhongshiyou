//package com.efficient.production.app.fragment;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.bigemap.bmcore.BMEngine;
//import com.bigemap.bmcore.EarthFragment;
//import com.bigemap.bmcore.constant.Constants;
//import com.bigemap.bmcore.entity.CustomMapSource;
//import com.bigemap.bmcore.entity.DefaultStyle;
//import com.bigemap.bmcore.entity.GeoPoint;
//import com.bigemap.bmcore.entity.MapConfig;
//import com.bigemap.bmcore.entity.Provider;
//import com.bigemap.bmcore.entity.VectorElement;
//import com.bigemap.bmcore.listener.OperationCallback;
//import com.bigemap.bmcore.sp.StyleUtils;
//import com.efficient.production.app.R;
//import com.efficient.production.app.utils.Utils;
//
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JavaMainActivity extends AppCompatActivity implements OperationCallback {
//
//    private final static String TAG_EARTH_FRAGMENT = "TAG_EARTH_FRAGMENT";
//
//    private final static String TEST_MAP_SOURCE_URL1 = "http://services.arcgisonline.com/ArcGIS/services/World_Imagery/MapServer?mapname=Layers&layer=_alllayers&format=PNG&level={z}&row={y}&column={x}";
//    private final static String TEST_MAP_SOURCE_URL2 = "https://map.geoq.cn/arcgis/rest/services/ChinaOnlineStreetWarm/MapServer/tile/{z}/{y}/{x}";
//
//    private EarthFragment mEarthFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_map);
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 保持亮屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//        BMEngine.preInit(this, "af655b5079055e38f095313c27a84345");
//        // 1、Context 2、自定义图标存放位置 3、是否加载地形
//        BMEngine.init(this, getFilesDir().getPath() + File.separator, false);
//
//        //拷贝文件到文件系统
//        Utils.INSTANCE.copyAssets(this, "img", getFilesDir().getPath());
//        Utils.INSTANCE.copyAssets(this, "map", getFilesDir().getPath());
//
////        Utils.INSTANCE.copyAssets(this, "同名图标", BMEngine.getIconPath());
//
//        // 添加在线地图
//        addMapSource(TEST_MAP_SOURCE_URL1);
//        addMapSource(TEST_MAP_SOURCE_URL2);
//        // 添加离线地图
//        addOfflineMap();
//
//        mEarthFragment = EarthFragment.getInstance(this);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.flt_container, mEarthFragment, TAG_EARTH_FRAGMENT);
//        transaction.commitAllowingStateLoss();
//
//        Button btn_test1 = findViewById(R.id.btn_test1);
//        Button btn_test2 = findViewById(R.id.btn_test2);
//        Button btn_online1 = findViewById(R.id.btn_online1);
//        Button btn_online2 = findViewById(R.id.btn_online2);
//        Button btn_offline = findViewById(R.id.btn_offline);
//        Button btn_animate_to = findViewById(R.id.btn_animate_to);
//        Button btn_locate = findViewById(R.id.btn_locate);
//        Button btn_add_point = findViewById(R.id.btn_add_point);
//        Button btn_add_line = findViewById(R.id.btn_add_line);
//        Button btn_add_circle = findViewById(R.id.btn_add_circle);
//        Button btn_add_ellipse = findViewById(R.id.btn_add_ellipse);
//        Button btn_draw_point = findViewById(R.id.btn_draw_point);
//        Button btn_draw_line = findViewById(R.id.btn_draw_line);
//        Button btn_draw_plane = findViewById(R.id.btn_draw_plane);
//        Button btn_draw_revocation = findViewById(R.id.btn_draw_revocation);
//        Button btn_load_kml = findViewById(R.id.btn_load_kml);
//        Button btn_length = findViewById(R.id.btn_length);
//
////        btn_test1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                new Thread() {
////                    @Override
////                    public void run() {
////                        super.run();
////                        try {
////                            FileInputStream jpg1 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg2 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg3 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg4 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg5 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg6 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg7 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg8 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////                            FileInputStream jpg9 = new FileInputStream(getFilesDir().getPath() + File.separator + "1.jpeg");
////
////                            TilesDatabaseWriter writer = new TilesDatabaseWriter(getFilesDir().getPath() + File.separator + "测试.mbtiles");
////
////                            writer.saveFile(417, 288, 9, jpg1);
////                            writer.saveFile(834, 576, 10, jpg2);
////                            writer.saveFile(835, 576, 10, jpg3);
////                            writer.saveFile(1670, 1153, 11, jpg4);
////                            writer.saveFile(1669, 1153, 11, jpg5);
////                            writer.saveFile(3340, 2307, 12, jpg6);
////                            writer.saveFile(3339, 2307, 12, jpg7);
////                            writer.saveFile(3340, 2306, 12, jpg8);
////                            writer.saveFile(3339, 2306, 12, jpg9);
////                        } catch (FileNotFoundException e) {
////                            throw new RuntimeException(e);
////                        }
////                    }
////                }.start();
//////                Intent intent = new Intent(getApplicationContext(), KotlinMainActivity.class);
//////                startActivity(intent);
////            }
////        });
////        btn_test2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                ArrayList<GeoPoint> list = new ArrayList<>();
////                list.add(new GeoPoint(113.52818104, 22.10915053));
////                list.add(new GeoPoint(113.59886192, 22.21704316));
////                List<Long> index = CacheManager.getTilesCoverage(list, true, 9, 12);
////
////                for (Long it : index) {
////                    int z = MapTileIndex.getZoom(it);
////                    int x = MapTileIndex.getX(it);
////                    int y = (int) (Math.pow(2.0, z) - MapTileIndex.getY(it) - 1);
////                    Log.e("===", "x = " + x + " y = " + y + " z = " + MapTileIndex.getZoom(it));
////                }
////            }
////        });
//        btn_online1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<Provider> providers = BMEngine.getMapProviders();
//
//                if (!providers.isEmpty()) {
//                    Provider provider = providers.get(0);
//                    mEarthFragment.changeMapSource(provider.mapId);
//                }
//            }
//        });
////        btn_online2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                List<Provider> providers = BMEngine.getMapProviders();
////
////                if (!providers.isEmpty()) {
////                    Provider provider = providers.get(1);
////                    mEarthFragment.changeMapSource(provider.mapId);
////                }
////            }
////        });
////        btn_offline.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                List<Provider> providers = BMEngine.getMapProviders();
////
////                if (!providers.isEmpty()) {
////                    Provider provider = providers.get(providers.size() - 1);
////                    mEarthFragment.changeMapSource(provider.mapId);
////
////                    if (provider.mapId.startsWith("MAPID_BM_OFFLINEMAP_PKG")) {
////                        mEarthFragment.animateToOfflineArea();
////                    }
////
////                    toAddPointInMap(113.5636, 22.16317);
////                    onAnimateTo(113.5636, 22.16317, 0.0);
////                }
////            }
////        });
//        btn_animate_to.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toAddPointInMap(0.0, 0.0);
//                onAnimateTo(0.0, 0.0, 3.0);
//            }
//        });
//        btn_locate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GeoPoint geoPoint = new GeoPoint(113.5636, 22.16317);
//                mEarthFragment.updateLocation(geoPoint, 100, 0);// 1、位置 2、精度 3、方向
//            }
//        });
//        btn_add_point.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toAddPointInMap(113.56, 22.16);
//                onAnimateTo(113.56, 22.16, 0.0);
//            }
//        });
//        btn_add_line.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GeoPoint geoPoint1 = new GeoPoint(113.563, 22.161);
//                GeoPoint geoPoint2 = new GeoPoint(113.564, 22.162);
//                toAddLineInMap(geoPoint1, geoPoint2);
//                onAnimateTo(113.5635, 22.1615, 0.0);
//            }
//        });
//        btn_add_circle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toAddCircleInMap(113.56, 22.16);
//                onAnimateTo(113.56, 22.16, 0.0);
//            }
//        });
//        btn_add_ellipse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toAddEllipseInMap(113.56, 22.16);
//                onAnimateTo(113.56, 22.16, 0.0);
//            }
//        });
//        btn_draw_point.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDrawElement(Constants.DRAW_ELEMENT_TYPE_POINT);
//                Toast.makeText(getApplication(), "点击屏幕", Toast.LENGTH_LONG).show();
//            }
//        });
//        btn_draw_line.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDrawElement(Constants.DRAW_ELEMENT_TYPE_LINE);
//                Toast.makeText(getApplication(), "点击屏幕", Toast.LENGTH_LONG).show();
//            }
//        });
//        btn_draw_plane.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onDrawElement(Constants.DRAW_ELEMENT_TYPE_PLANE);
//                Toast.makeText(getApplication(), "点击屏幕", Toast.LENGTH_LONG).show();
//            }
//        });
//        btn_draw_revocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEarthFragment.toRetreatDrawingElement();
//            }
//        });
//        btn_load_kml.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = "";
//                if (url.isEmpty()) {
//                    Toast.makeText(getApplication(), "添加路径", Toast.LENGTH_LONG).show();
//                } else {
//                    long rootID = mEarthFragment.getRootLayerId();
//                    mEarthFragment.loadKMLFile(rootID, url);
//                }
//            }
//        });
////        btn_length.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                List<GeoPoint> geoPoints = new ArrayList<>();
////                geoPoints.add(new GeoPoint(113.5636, 22.16317));
////                geoPoints.add(new GeoPoint(113.5637, 22.16318));
////                BMEngine.getPerimeter(geoPoints, VectorElement.TYPE_LINE);
////            }
////        });
//    }
//    public void addMapSource(String url) {
//        // 1、创建自定义地图源
//        CustomMapSource custom = new CustomMapSource();
//        custom.name = "Arcgis";
//        custom.url = url;// 瓦片链接地址
//        custom.tileSize = 256;// 瓦片大小
//        custom.projection = true;// 投影true:墨卡托，false:经纬度直投
//        custom.minLevel = 0;// 最小层级
//        custom.maxLevel = 19;// 最大层级
//        custom.cacheKey = "ArcGis";// 缓存标识
//
//        // 2、加入引擎中
//        BMEngine.addCustomMapSource(custom);
//
////        // 1、创建自定义地图源
////        CustomMapSource custom = new CustomMapSource();
////        custom.name = "Arcgis";
////        custom.url = url;// 瓦片链接地址
////        custom.tileSize = 256;// 瓦片大小
////        custom.projection = true;// 投影true:墨卡托，false:经纬度直投
////        custom.minLevel = 0;// 最小层级
////        custom.maxLevel = 19;// 最大层级
////        custom.cacheKey = "11";// 最大层级
////
////        List<CustomMapSource> list = new ArrayList<>();
////        list.add(custom);
////
////        // 2、加入引擎中
////        BMEngine.addMapLayerList("111", "111", list);
//    }
//
//    public void addOfflineMap() {
//        String name = "澳门特别行政区_卫图";
//        String icon = "";
//        String path = getFilesDir().getPath() + File.separator + "澳门特别行政区_卫图.bmpkg";
//        List<String> strings = new ArrayList<>();
//        strings.add(path);
//        BMEngine.addOfflineMap(name, icon, strings);
//    }
//
//    private void onDrawElement(int type) {
//        StyleUtils.init(this);
//        DefaultStyle style = new DefaultStyle();
//        style.polylineColor = BMEngine.argb2rgba("#FFFF0000");
//        style.polylineWidth = 2;
//        BMEngine.setDefaultStyle(style);
//
//        // 1、首先应该获取根图层ID
//        long rootID = mEarthFragment.getRootLayerId();
//
//        // 2、在根图层上创建自己的图层
//        VectorElement layer = mEarthFragment.onCreateLayer(rootID, "", true);
//
//        // 3、在自己创建的图层上去绘制元素
//        mEarthFragment.toStartDrawElement(type, layer.id);
//
//        // 4、在地图上绘制完成后，会回调 onCallbackDrawElementStepEditing() 这个方法
//    }
//
//    private void toAddPointInMap(Double lon, Double lat) {
//        // 1、首先应该获取根图层ID
//        long rootID = mEarthFragment.getRootLayerId();
//
//        // 2、在根图层上创建自己的图层
//        VectorElement layer = mEarthFragment.onCreateLayer(rootID, "", true);
//
//        // 3、在自己创建的图层上添加元素
//        VectorElement vector = new VectorElement(layer.id, VectorElement.TYPE_POINT, "点");
//        vector.description = "描述";
//
//        // 4、是否使用自定义图标
//        vector.isCustomPath = false;
//        if (vector.isCustomPath) {
//            // 是：需要在自定义图标目录里面添加图标
//            // BMEngine.init(this, 自定义图标目录, false)
//            vector.iconPath = "自定义.png";// 只需要文件名
//        } else {
//            // 否：内置图标
//            vector.iconPath = "170.png";// 只需要文件名
//        }
//        // 5、设置点的大小
//        vector.iconScale = 1.5f;
//
//        vector.labelColor = "#FF00FF00";
//        GeoPoint geo = new GeoPoint(lon, lat, 0.0);
//        vector.geoPoints.add(geo);
//
//        // 6、绘制在地球中
//        long id = mEarthFragment.drawElement(vector, true);
//
//        BMEngine.setElementDescription(id, "测试");
//    }
//
//    private void toAddLineInMap(final GeoPoint geoPoint1, final GeoPoint geoPoint2) {
//        // 1、首先应该获取根图层ID
//        long rootID = mEarthFragment.getRootLayerId();
//
//        // 2、在根图层上创建自己的图层
//        VectorElement layer = mEarthFragment.onCreateLayer(rootID, "", true);
//
//        // 3、在自己创建的图层上添加元素
//        final VectorElement vector = new VectorElement(layer.id, VectorElement.TYPE_LINE, "名称");
//        vector.description = "描述";
//        vector.labelColor = "#FF00FF00";
//        vector.outlineWidth = "5";
//        vector.outlineColor = "#FF00FF00";
//
//        // 4、设置坐标
//        vector.geoPoints.add(geoPoint1);
//        vector.geoPoints.add(geoPoint2);
//
//        // 5、绘制在地球中
//        final long id = mEarthFragment.drawElement(vector, true);
//
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
//    }
//
//    private void toAddCircleInMap(Double lon, Double lat) {
//        // 1、首先应该获取根图层ID
//        long rootID = mEarthFragment.getRootLayerId();
//
//        // 2、在根图层上创建自己的图层
//        VectorElement layer = mEarthFragment.onCreateLayer(rootID, "", true);
//
//        // 3、在自己创建的图层上添加元素
//        VectorElement vector = new VectorElement(layer.id, VectorElement.TYPE_CIRCLE, "");
//        vector.description = "描述";
//        vector.labelColor = "#FF00FF00";
//
//        // 4、设置坐标
//        GeoPoint geo1 = new GeoPoint(lon, lat, 0.0);
//        GeoPoint geo2 = new GeoPoint(1000, 0.0, 0.0);
//        vector.geoPoints.add(geo1);
//        vector.geoPoints.add(geo2);
//
//        // 5、绘制在地球中
//        mEarthFragment.drawElement(vector, true);
//    }
//
//    private void toAddEllipseInMap(Double lon, Double lat) {
//        // 1、首先应该获取根图层ID
//        long rootID = mEarthFragment.getRootLayerId();
//
//        // 2、在根图层上创建自己的图层
//        VectorElement layer = mEarthFragment.onCreateLayer(rootID, "", true);
//
//        // 3、在自己创建的图层上添加元素
//        VectorElement vector = new VectorElement(layer.id, VectorElement.TYPE_ELLIPSE, "");
//        vector.description = "描述";
//        vector.labelColor = "#FF00FF00";
//
//        // 4、设置坐标
//        GeoPoint geo1 = new GeoPoint(lon, lat, 0.0);
//        GeoPoint geo2 = new GeoPoint(1000, 500, 0.0);// lon长半轴 lat短半轴
//        GeoPoint geo3 = new GeoPoint(12, 0.0, 0.0);// lon角度
//        vector.geoPoints.add(geo1);
//        vector.geoPoints.add(geo2);
//        vector.geoPoints.add(geo3);
//
//        // 5、绘制在地球中
//        mEarthFragment.drawElement(vector, true);
//    }
//
//    @Override
//    public void onCallbackDrawElementStepEditing(VectorElement vectorElement) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                // 5、结束当前绘制对象，启动新的绘制
//                mEarthFragment.toStopDrawElement();
//                // 6、退出绘制
//                mEarthFragment.toCancelDrawingElement();
//                Log.e("===", "==onCallbackDrawElementStepEditing==");
//            }
//        });
//    }
//
//    @Override
//    public void onCallbackDrawElementStepCreated(VectorElement vectorElement) {
//        Log.e("===", "==onCallbackDrawElementStepCreated==");
//    }
//
//    /**
//     * time 跳转时间
//     */
//    private void onAnimateTo(double lon, double lat, double time) {
//        // 定位位置
//        GeoPoint geoPoint = new GeoPoint(lon, lat);
//
//        // 视角高度
//        int height = 3000;
//
//        // 俯仰角度
//        double pitch = -90.0;
//
//        mEarthFragment.animateTo(geoPoint, height, time, pitch);
//    }
//
//    @Override
//    public void onCreateEarthComplete() {
//        Log.e("=====", "=====");
//        // 获取内置地图源
//        List<Provider> providers = BMEngine.getMapProviders();
//
//        if (!providers.isEmpty()) {
//            Provider provider = providers.get(0);
//            mEarthFragment.changeMapSource(provider.mapId);
//
//
//            if (provider.mapId.startsWith("MAPID_BM_OFFLINEMAP_BKG")) {
//                mEarthFragment.animateToOfflineArea();
//            }
//        }
//
//        //        BMEngine.setGesturesRotation(false); // 禁止旋转手势
//        //        BMEngine.setLockAzimuthPanning(true); // 锁定轴不转动
//
//        //        BMEngine.setGesturesTilting(false) // 让地球一直俯视
//
//        // 跳转指定位置
//        onAnimateTo(104.06303644, 30.56918947, 0.0);
//
//        BMEngine.isShowBuilding(false);
//    }
//
//    @Override
//    public void onCreateEarthFail(int i) {
//
//    }
//
//    @Override
//    public void onScroll() {
//
//    }
//
//    @Override
//    public void callbackScreenCenterPoint(GeoPoint geoPoint, double v, long l) {
//
//    }
//
//    @Override
//    public void callbackEarthOrientation(float v) {
//
//    }
//
//    @Override
//    public void onSingleTapConfirmed(MotionEvent motionEvent) {
//
//    }
//
//
////    @Override
////    public void onSingleTapConfirmed(MotionEvent motionEvent, GeoPoint geoPoint) {
////        toAddPointInMap(geoPoint.getLon(), geoPoint.getLat());
////    }
//
////    @Override
////    public void onLongPress(MotionEvent motionEvent, GeoPoint geoPoint) {
////
////    }
//
////    @Override
////    public void onCallbackSiWeiHistoryData(String[] strings) {
////
////    }
//
//    @Override
//    public void onClickedElement(VectorElement vectorElement) {
//        VectorElement element = mEarthFragment.getThisElementAttribute(vectorElement.id);
//    }
//
////    @Override
////    public void onLongClickedElement(VectorElement vectorElement) {
////        VectorElement element = mEarthFragment.getThisElementAttribute(vectorElement.id);
////    }
//
//    @Override
//    public void onChangeMapSourceComplete(MapConfig mapConfig) {
//
//    }
//
//    @Override
//    public void onChangeMapTypeGroupComplete(MapConfig mapConfig) {
//
//    }
//
//    @Override
//    public void onCallbackHistoricalImagery(int[] ints) {
//
//    }
//
//    @Override
//    public void onCallbackHistoricalImagery(String[] strings) {
//
//    }
//
//    @Override
//    public void onCallbackAddedTrackPoint(GeoPoint geoPoint) {
//
//    }
//
//    @Override
//    public void onLoadVectorFileStart(int i) {
//
//    }
//
//    @Override
//    public void onLoadVectorFileDoing() {
//
//    }
//
//    @Override
//    public void onLoadVectorFileComplete(boolean b, long l) {
//
//    }
//
//    @Override
//    public void onLoadVectorFileComplete(VectorElement vectorElement) {
//
//    }
//
//    @Override
//    public byte[] onFormatStringToPicture(String s) {
//        return new byte[0];
//    }
//
//    @Override
//    public byte[] webPToPng(byte[] bytes) {
//        return new byte[0];
//    }
//
//    @Override
//    public boolean onUpdateOfflineCallback(int i, int i1) {
//        return false;
//    }
//}