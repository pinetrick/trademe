package com.pine.mytrademetest.net

import android.widget.Toast
import com.google.gson.GsonBuilder
import com.pine.mytrademetest.Config
import com.pine.mytrademetest.app
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass


/**
 * 需要在APP中初始化
 * API初始化类
 * RetrofitManager(Config.baseUrl, RetrofitServices::class) //初始化
 *
 */
final class RetrofitManager<T : Any> {

    var hostUrl: String = "";

    var request0: T? = null
    var retrofit: Retrofit? = null

    private val cookieStore: HashMap<String, List<Cookie>> = HashMap()

    constructor(hostUrl: String, classInfo: KClass<T>) {
        this.hostUrl = hostUrl;
        mInstance = this;

        try {
            // init okhttp
            if (retrofit == null){


                val client = OkHttpClient.Builder()
                    .connectTimeout(500, TimeUnit.SECONDS)
                    .readTimeout(500, TimeUnit.SECONDS)
                    .writeTimeout(500, TimeUnit.SECONDS)
                    .cookieJar(object : CookieJar {


                        override fun saveFromResponse(
                            url: HttpUrl,
                            cookies: List<Cookie>
                        ) {
                            cookieStore[url.host] = cookies
                        }

                        override fun loadForRequest(url: HttpUrl): List<Cookie> {
                            val cookies =
                                cookieStore.get(url.host)
                            return cookies ?: ArrayList()
                        }
                    })
                    .addInterceptor {
                        val request: Request = it.request()
                            .newBuilder()
                            .addHeader("Content-Type", "text/json")
                            .addHeader(
                                "Authorization",
                                "OAuth " +
                                        "oauth_consumer_key=${Config.apiKey}" +
                                        ", oauth_token=${Config.tmpOauthToken}" +
                                        ", oauth_signature_method=PLAINTEXT" +
                                        ", oauth_signature=${Config.apiSecret}%26${Config.tmpOauthTokenSecret}"
                            )
                            .build()
                        it.proceed(request)
                    }
                    .build()

                // init Retrofit
                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(hostUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                    .build()

                request0 = retrofit!!.create(classInfo.java)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    fun req(): T {
        return request0!!
    }


    companion object {

        private var mInstance: RetrofitManager<*>? = null

        fun i(): RetrofitManager<*> {
            if (mInstance == null) {
                throw Exception("Please Run RetrofitManager(??, ??) on APP");
            }
            return mInstance!!

        }


    }
}


fun <T> Call<T>.require(function: (response: T?) -> Unit) {
    var x = object: Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) {
            function(response.body());
        }

        // TODO: 2021/9/6  make it global, watch the network change
        override fun onFailure(call: Call<T>, t: Throwable) {
            Toast.makeText(app(), "Load Fail", Toast.LENGTH_LONG).show()
            function(null);
        }

    }
    enqueue(x);
}