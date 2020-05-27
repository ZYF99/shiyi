package com.bs.demo.common.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bs.demo.R
import com.bs.demo.common.config.ActKeyConstants
import com.bs.demo.widget.CLoadingDialog

abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private var cLoadingDialog: CLoadingDialog? = null

    open abstract fun initView()
    fun startAct(clazz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(activity, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)

    }

    fun startAct(clazz: Class<*>?) {
        startAct(clazz,null)
    }
    fun startActWithId(clazz: Class<*>?,id:String) {
        val bundle=Bundle().apply {
            putString(ActKeyConstants.KEY_ID,id)
        }
        startAct(clazz,bundle)
    }
    fun showToast(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    protected fun showLoadingDialog() {
        cLoadingDialog = CLoadingDialog(context)
        cLoadingDialog?.show()
    }

    protected fun dismissLoadingDialog() {
        cLoadingDialog?.let {
            it.dismiss()
        }
    }

}