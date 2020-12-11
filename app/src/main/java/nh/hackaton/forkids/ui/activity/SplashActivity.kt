package nh.hackaton.forkids.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ActivitySplashBinding
import nh.hackaton.forkids.ui.base.BaseActivity
import nh.hackaton.forkids.ui.base.ProgressDialog


class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    lateinit var handler: Handler

    override val layoutResourceId: Int
        get() = R.layout.activity_splash

    override fun initStartView() {
        handler = Handler(Looper.getMainLooper())
        progressDialog.show()
        handler.postDelayed({
            progressDialog.hide()
            startActivity(
                    Intent(baseContext, SignUpActivity::class.java)
            )
            finish()
        }, 2500)

    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {

    }
}