package cn.sichuan.cd.zsh.utils;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import cn.sichuan.cd.zzsy.utils.LLogUtils;

public class DataSubmissionWorker extends Worker {
    Context context;

    public DataSubmissionWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // 创建并启动一个线程来执行后台任务
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    LLogUtils.d("--------------------------DataSubmissionWorker----------------------开始执行线程-----------run--------");
                    submitDataToServer(context);
                }
            });
            thread.start();
            thread.join();  // 等待线程完成

            return Result.success();
        } catch (Exception e) {
            return Result.retry();
        }
    }

    private void submitDataToServer(Context context) {
        LLogUtils.d("----------------------DataSubmissionWorker-------------定时执行一个后台任务---------------");

        //1.启动定位服务
//        if (!new AlarmReceiver().isServiceWork(context, LocationService.class.getName())) {
//            LLogUtils.d("--------------DataSubmissionWorker----------------------->>> 定位服务不在运行，执行启动操作");
//            Intent locationIntent = new Intent(context, LocationService.class);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                context.startForegroundService(locationIntent);
//            } else {
//                context.startService(locationIntent);
//            }
//        } else {
//            LLogUtils.d("--------------------DataSubmissionWorker----------------->>> 定位服务在运行");
//        }


    }
}