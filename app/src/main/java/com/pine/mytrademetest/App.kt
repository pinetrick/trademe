package com.pine.mytrademetest

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.pine.mytrademetest.net.RetrofitManager
import com.pine.mytrademetest.net.RetrofitServices


fun app(): App{
    return App.app!!;

}
var myRs: RetrofitServices? = null;
fun n(): RetrofitServices {
    if (myRs != null) {
        return myRs!!;
    }

    return myRs!!;
}


class App : MultiDexApplication() {

    companion object{
        var app: App? = null;

    }

    override fun onCreate() {

        super.onCreate()
        App.app = this;

        initRetrofit();
        //facebook imageview init
        Fresco.initialize(this);
     }

    private fun initRetrofit() {
        var rm = RetrofitManager(BuildConfig.apiUrl, RetrofitServices::class);
        myRs = rm.req();


    }


}
