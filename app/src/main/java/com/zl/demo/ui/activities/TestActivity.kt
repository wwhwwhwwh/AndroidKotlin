package com.zl.demo.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zhonglv.mvvm.BaseActivity
import com.zhonglv.mvvm.BaseViewModel
import com.zl.demo.R
import com.zl.demo.databinding.ActivityTestBinding

class TestActivity : BaseActivity<ActivityTestBinding, BaseViewModel>() {



    override fun initView(savedInstanceState: Bundle?) {

        viewBind.textTest.setOnClickListener {
            Toast.makeText(this,"666",Toast.LENGTH_LONG).show()
        };
    }
}