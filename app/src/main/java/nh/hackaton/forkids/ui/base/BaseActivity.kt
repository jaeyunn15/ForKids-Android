package nh.hackaton.forkids.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(){
    lateinit var viewDataBinding: T

    abstract val layoutResourceId: Int

    lateinit var progressDialog: ProgressDialog
    //abstract val viewModel: R

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
        progressDialog = ProgressDialog(this)

        initStartView()
        initDataBinding()
        initAfterBinding()
    }


}