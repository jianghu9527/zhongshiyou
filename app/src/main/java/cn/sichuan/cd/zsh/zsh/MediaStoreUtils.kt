package cn.sichuan.cd.zsh.zsh
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.IOException
class MediaStoreUtils {

    fun saveImageToPublicDirectory(context: Context, bitmap: Bitmap, fileName: String) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyApp")
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (uri != null) {
            try {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    LogMangeUtil.d("saveImage", "---------------------Image saved successfully")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("saveImage", "---------------------Failed to save image", e)
            }
        } else {
            Log.e("saveImage", "---------------------Failed to create new MediaStore record.")
        }
    }
    private fun saveVideoToPublicDirectory(context: Context,videoFileName:String) {
//        val videoFileName = "example_video.mp4"
        val values = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, videoFileName)
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + "/MyApp")
        }

        val contentResolver = context.contentResolver
        val videoUri = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)

        if (videoUri != null) {
            try {
                contentResolver.openOutputStream(videoUri)?.use { outputStream ->
                    // 假设 videoData 是你的视频数据
                    val videoData: ByteArray = getVideoData()
                    outputStream.write(videoData)
                    outputStream.flush()
                    LogMangeUtil.d("-----------------------saveVideo", "---------------------Video saved successfully")
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("---------------------saveVideo", "---------------------Failed to save video", e)
            }
        } else {
            Log.e("---------------------saveVideo", "---------------------Failed to create new MediaStore record.")
        }
    }

    // 示例函数：获取视频数据
    private fun getVideoData(): ByteArray {
        // 生成或获取你的视频数据，这里只是一个示例
        return ByteArray(1024) // 示例数据，实际应为你的视频文件数据
    }


}