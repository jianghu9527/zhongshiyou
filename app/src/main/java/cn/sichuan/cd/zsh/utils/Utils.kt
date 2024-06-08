package cn.sichuan.cd.zsh.utils

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream

object Utils {

    //拷贝文件到文件系统
    fun copyAssets(context: Context, oldPath: String, newPath: String) {
        try {
            val fileNames = context.assets.list(oldPath) // 获取assets目录下的所有文件及目录名
            if (fileNames != null) {
                val file = File(newPath)
                if (fileNames.isNotEmpty()) { // 如果是目录
                    file.mkdirs() // 如果文件夹不存在，则递归
                    for (fileName in fileNames) {
                        copyAssets(context, "$oldPath/$fileName", "$newPath/$fileName")
                    }
                } else {// 如果是文件
//                    if (file.exists()) {
//                        val `is` = context.assets.open(oldPath)
//                        val fileMD5: String? = getFileMD5(file)
//                        val steamMD5: String? = getFileMD5(`is`)
//                        if (fileMD5 == steamMD5) {
//                            Log.d("copyAssets", "存在相同文件：" + file.absolutePath)
//                            return
//                        }
//                    }
                    Log.i("copyAssets", "导入新的文件：$oldPath")
                    val inputStream = context.assets.open(oldPath)
                    val fileOutputStream = FileOutputStream(file)
                    val buffer = ByteArray(8324816)
                    var byteCount: Int
                    while (inputStream.read(buffer).also { byteCount = it } != -1) { // 循环从输入流读取
                        // buffer字节
                        fileOutputStream.write(buffer, 0, byteCount) // 将读取的输入流写入到输出流
                    }
                    fileOutputStream.flush() // 刷新缓冲区
                    inputStream.close()
                    fileOutputStream.close()
                }
            }
        } catch (e: Exception) {
            Log.e("copyAssets", "Exception" + e.message)
            e.printStackTrace()
        }
    }
}