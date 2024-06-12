package cn.sichuan.cd.zzsy.zsh;


import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;


/**
 * @author : ck
 * @time:2021/11/4 16:41
 * @description :
 */
public class LogMangeUtil {



//    public static boolean isDebug = BuildConfig.DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化
        public static boolean isDebug = false  ;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static String TAG="------------日志---------";

    //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
    //  把4*1024的MAX字节打印长度改为2001字符数
    public  static void  logeLong(String str){
        if (isDebug){
            int max_str_length=2001-TAG.length();
            //大于4000时
            while (str.length()>max_str_length){
                Log.e(TAG, str.substring(0,max_str_length) );
                str=str.substring(max_str_length);
            }
            //剩余部分
//            Log.e(TAG, str );

            if(str.length() > 4000) {
                for(int i=0;i<str.length();i+=4000){
                    if(i+4000<str.length()){
                        Log.i(TAG+"-----1--resinfo-----"+i,str.substring(i, i+4000));
                        SendInforLog("大数据1==>>>>"+str.substring(i, i+4000));

                    }  else{
                        Log.i(TAG+"------2-resinfo-----"+i,str.substring(i));
                        SendInforLog("大数据2==>>>>"+str.substring(i));

                    }
                }
            } else{
                Log.i(TAG+"-----3--resinfo-----",str);
                SendInforLog("大数据4==>>>>"+str);
            }
        }else{
            SendInforLog("大数据3==>>>>"+str);
        }
    }


    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug){
            Log.i(TAG, msg);
        }else{
            SendInforLog("i1=>>"+msg);
        }
    }

    public static void d(String msg) {
        if (isDebug){
            LogMangeUtil.d(TAG, msg);
        }else{
            SendInforLog("d1=>>"+msg);
        }
    }

    public static void e(String msg) {
        if (isDebug){
            Log.e(TAG, msg);
        }else{
            SendInforLog("e1=>>"+msg);
        }

    }

    public static void v(String msg) {
        if (isDebug){
            Log.v(TAG, msg);
        }else{
            SendInforLog("v1=>>"+msg);
        }

    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug){
            Log.i(TAG+tag, msg);
        }else{
            SendInforLog("i1=>>"+msg);
        }

    }

    public static void d(String tag, String msg) {
        if (isDebug){
            Log.i(TAG+tag, msg);
        }else{
            SendInforLog("d1=>>"+msg);
        }

    }

    public static void e(String tag, String msg) {
        if (isDebug){
            Log.i(TAG+tag, msg);
        }else{
            SendInforLog("e1=>>"+msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug){
            Log.i(TAG+tag, msg);
        }else{
            SendInforLog("v1=>>"+msg);
        }
    }


    public  static void SendInforLog(String msginfor){

        Log.e("--------","-----"+msginfor);
//        Log.e("--------","--------写入库文件-------mecp------table:"+msginfor);

//        if (msginfor != null&& !TextUtils.isEmpty(msginfor)) {
//            LogMangeUtil.d("---------------", "--------写入结果------run-------"+msginfor);



//            String time =TimeUtils.CurrLogtime();
//            msginfor=time+"_time_:"+msginfor;
//            String path="";
////            long timestamp = System.currentTimeMillis();
////            String time = TimeUtils.CurrLogtime();
//            String daytimes =TimeUtils.CurrLogDaytime();
////            long daytimes = System.currentTimeMillis();
//
//
//            String fileName;
//            if (TextUtils.isEmpty(Tools.getLoginName() )){
//                fileName =   "_log_" + daytimes + ".txt";
//            }else{
//                fileName = Tools.getLoginName() +"_log_" + daytimes + ".txt";
//            }
//
//            String paths =new LogMangeUtil(). getSavePath();
//            File dir = new File(paths,fileName);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            path=dir.getAbsolutePath();
//
//            boolean  isWriter   =  FileUtils.writeFile(msginfor,path,false);
////           LogMangeUtil.d("---------------", "--------写入结果------run-------------isWriter:"+isWriter+"--"+msginfor+"--"+Tools.getLoginName()+"-dir:"+path);

//        }
    }

    public String getSavePath() {
        String path;
        if (Build.VERSION.SDK_INT > 29) {
            path =   MyApplication.getInstance().getExternalFilesDir(null).getAbsolutePath() + Constants.App_kog_path;
        } else {
            path = Environment.getExternalStorageDirectory().getPath() +  Constants.path_base;//
        }

//        LogMangeUtil.d("---------------","-------LogMang------getSavePath--------path:"+path);
        File file=new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


}
