package cn.sichuan.cd.zsh.mvvm

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.Environment
import android.telecom.Call
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import cn.sichuan.cd.zsh.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat

class VideoActivity : AppCompatActivity() {
    private lateinit var mediaProjectionManager: MediaProjectionManager
    private var mediaProjection: MediaProjection? = null
    private lateinit var mediaRecorder: MediaRecorder
    private var virtualDisplay: VirtualDisplay? = null

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
        private const val REQUEST_CODE_CAPTURE_PERMISSION = 1002
        private const val DISPLAY_WIDTH = 1280
        private const val DISPLAY_HEIGHT = 720
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        requestPermissions()
        findViewById<Button>(R.id.start_recording_button).setOnClickListener {
            startScreenCapture()
        }

        findViewById<Button>(R.id.stop_recording_button).setOnClickListener {
            stopRecording()
            uploadVideo()
        }
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO
        )

        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSIONS)
    }





    private fun initRecorder() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(getVideoFilePath())
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setVideoSize(DISPLAY_WIDTH, DISPLAY_HEIGHT)
            setVideoEncodingBitRate(512 * 1000)
            setVideoFrameRate(30)
            prepare()
        }
    }

    private fun getVideoFilePath(): String {
        val directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        Log.d("------------------------------------","-------------------getVideoFilePath-----------------${directory.path}");
        return "${directory.path}/screen_recording.mp4"
    }

    private val mediaProjectionCallback = object : MediaProjection.Callback() {
        override fun onStop() {
            mediaRecorder.stop()
            mediaRecorder.reset()
            mediaProjection = null
            stopScreenCapture()
        }
    }

    private fun startScreenCapture() {
        val projectionIntent = mediaProjectionManager.createScreenCaptureIntent()
        startActivityForResult(projectionIntent, REQUEST_CODE_CAPTURE_PERMISSION)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_CAPTURE_PERMISSION) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data)
                mediaProjection?.registerCallback(mediaProjectionCallback, null)
                initRecorder()
                virtualDisplay = mediaProjection?.createVirtualDisplay(
                    "ScreenCapture",
                    DISPLAY_WIDTH,
                    DISPLAY_HEIGHT,
                    resources.displayMetrics.densityDpi,
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    mediaRecorder.surface,
                    null,
                    null
                )
                mediaRecorder.start()
            }
        }
    }

    private fun stopScreenCapture() {
        virtualDisplay?.release()
        virtualDisplay = null
        mediaProjection?.unregisterCallback(mediaProjectionCallback)
    }

    private fun stopRecording() {
        mediaProjection?.stop()
    }

    private fun uploadVideo() {
        val file = File(getVideoFilePath())
        val requestBody = file.asRequestBody("video/mp4".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

        Log.d("----------------","-----------------onResponse------uploadVideo----"+file.absolutePath);


//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("YOUR_UPLOAD_URL")
//            .post(multipartBody)
//            .build()
//
//        client.newCall(request).enqueue(object : Callback {
//
//
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: Response) {
//                Log.d("----------------","-----------------onResponse------uploadVideo----");
//                if (response.isSuccessful) {
//                    // Handle success
//                } else {
//                    // Handle failure
//                }
//            }
//        })





    }


}