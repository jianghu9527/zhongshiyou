package cn.sichuan.cd.zsh.zsh

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION_CODES

class Tools {


    /*批量申请权限*/
    fun askForPerssion(context: Activity, requsetCode: Int) {
        //所需要申请的权限数组
        val permissionsArray = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.READ_PHONE_STATE,


//            Manifest.permission.READ_LOGS,
//            Manifest.permission.ACCESS_NOTIFICATION_POLICY,


            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
//            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.USE_FINGERPRINT,


            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,


//            Manifest.permission.SYSTEM_ALERT_WINDOW,
//            Manifest.permission.ACCESS_NOTIFICATION_POLICY


        )
        //还需申请的权限列表
        val permissionList: MutableList<String> = ArrayList()
        if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
            for (permission in permissionsArray) {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission)
                }
            }
            if (permissionList.size > 0) {
                context.requestPermissions(permissionList.toTypedArray<String>(), requsetCode)
            }
        }
    }
}