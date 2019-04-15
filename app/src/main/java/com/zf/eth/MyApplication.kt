package com.zf.eth

import android.app.Application
import android.content.Context
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

    }


}
