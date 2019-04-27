package com.zf.eth

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.fm.openinstall.OpenInstall
import com.yanzhenjie.album.Album
import com.yanzhenjie.album.AlbumConfig
import com.zf.eth.utils.MediaLoader
import kotlin.properties.Delegates


class MyApplication : Application() {


    companion object {

        var context: Context by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        Album.initialize(AlbumConfig.newBuilder((this)).setAlbumLoader(MediaLoader()).build())

        if (isMainProcess()) {
            OpenInstall.init(this)
        }
    }

    private fun isMainProcess(): Boolean {
        val pid = android.os.Process.myPid()
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in activityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return applicationInfo.packageName == appProcess.processName
            }
        }
        return false
    }

}
