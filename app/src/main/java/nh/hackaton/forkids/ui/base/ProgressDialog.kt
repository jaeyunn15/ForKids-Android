package nh.hackaton.forkids.ui.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import nh.hackaton.forkids.R

class ProgressDialog(context: Context) {

    private var view: View
    private var linear: LinearLayout
    private var builder: AlertDialog.Builder
    lateinit var dialog: Dialog
    lateinit var drawable: AnimationDrawable

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.fragment_progress_dialog, null)
        linear = view.findViewById(R.id.linear)
        builder = AlertDialog.Builder(context)
        drawable = view.findViewById<ImageView>(R.id.iv_loading).background as AnimationDrawable
        //drawable = view.iv_loading.background as AnimationDrawable
    }

    fun show() {
        builder.setView(view)
        drawable.start()
        dialog = builder.create()?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCancelable(false)
            show()
        }!!
    }

    fun hide() {
        drawable.stop()
        dialog.dismiss()
    }



}
