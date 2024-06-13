package cn.sichuan.cd.zsh.utils;

import android.content.Context;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import java.util.concurrent.TimeUnit;

import cn.sichuan.cd.zzsy.utils.LLogUtils;

public class WorkManagerHelper {

    public static void schedulePeriodicWork(Context context) {

        LLogUtils.d("--------------------------DataSubmissionWorker-------------------执行的间隔时间--------------schedulePeriodicWork--------");
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(DataSubmissionWorker.class, 6, TimeUnit.MINUTES).build();

        WorkManager.getInstance(context).enqueue(workRequest);

    }


}
