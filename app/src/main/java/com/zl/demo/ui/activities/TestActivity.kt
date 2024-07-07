package com.zl.demo.ui.activities

import android.os.Bundle
import android.widget.Toast
import com.zl.base.BaseVBActivity
import com.zl.demo.databinding.ActivityTestBinding

class TestActivity : BaseVBActivity<ActivityTestBinding>() {



    override fun initView(savedInstanceState: Bundle?) {

        viewBind.textTest.setOnClickListener {
            Toast.makeText(this,"666",Toast.LENGTH_LONG).show()
        };
    }
}