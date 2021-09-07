package com.pine.mytrademetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

fun a(): BaseActivity {
    return BaseActivity.activity!!
}

open class BaseActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        BaseActivity.activity = this;
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        BaseActivity.activity = this;
        super.onResume()
    }

    companion object {
        var activity: BaseActivity? = null;
    }
}