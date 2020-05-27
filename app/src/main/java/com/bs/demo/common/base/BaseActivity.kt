package com.bs.demo.common.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bs.demo.R
import com.bs.demo.common.config.ActKeyConstants
import com.bs.demo.widget.CLoadingDialog
import com.bs.demo.widget.view.NavigationBar
import com.gyf.immersionbar.ktx.immersionBar

/**
 * Author:  zjw
 * Email:   crazyzjw@foxmail.com
 * Date:    2019/2/12
 * Description:
 */
open class BaseActivity : FragmentActivity() {
    fun startAct(clazz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out)

    }

    fun startAct(clazz: Class<*>?) {
        startAct(clazz,null)
    }
    private var cLoadingDialog: CLoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全局背景色
        val view = this.window.decorView
        view.setBackgroundColor(resources.getColor(R.color.bg_main))

    }

    fun showToast(msg: String?) {
        dismissLoadingDialog()
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    var navigationBar: NavigationBar? = null
    protected fun setNavBarTitle(title: String?) {
        try {
            navigationBar = findViewById(R.id.navbar)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (navigationBar == null) {
            return
        }
        initStatusBar()
        navigationBar!!.setTitile(title)
        navigationBar!!.setTitileColor(R.color.white)
        navigationBar!!.setBackIcon(R.drawable.icon_back_w)
        navigationBar!!.setNavBarBg(R.color.colorPrimaryDark)
        navigationBar!!.setNavBarBackListener { finish() }
    }
    override fun finish() {
        super.finish()
        //overridePendingTransition(R.anim.slide_right_out, R.anim.slide_left_in)
        closeKeyboard()
    }
    //关闭页面时候隐藏输入法
    private fun closeKeyboard() {
        try {
            val view = window.peekDecorView()
            if (view != null) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
    fun startActWithId(clazz: Class<*>?,id:String) {
        val bundle=Bundle().apply {
            putString(ActKeyConstants.KEY_ID,id)
        }
        startAct(clazz,bundle)
    }
    fun initStatusBar(hideableBottomView: View?=null){
        immersionBar {
            fitsSystemWindows(true)
            statusBarColor(R.color.colorPrimaryDark)
            statusBarDarkFont(true)
            hideableBottomView?.let {
                keyboardEnable(true)
                setOnKeyboardListener { isPopup, _ ->
                    if (isPopup) {
                        hideableBottomView.visibility = View.GONE
                    } else {
                        hideableBottomView.visibility = View.VISIBLE
                    }
                }
            }

        }
    }
    fun dismissLoadingDialog() {
        cLoadingDialog?.let {
            if (!isDestroyed && !isFinishing) {
                it.dismiss()
            }
        }
        closeKeyboard()
    }

    fun showLoadingDialog() {
        cLoadingDialog = CLoadingDialog(this)
        cLoadingDialog?.let {
            if (!isDestroyed && !isFinishing) {
                it.show()
            }
        }
        closeKeyboard()
    }

}