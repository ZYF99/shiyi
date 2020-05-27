package com.bs.demo.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.bs.demo.R;



/**
 * Description: 自定义加载对话框
 */

public class CLoadingDialog extends ProgressDialog {
    public CLoadingDialog(Context context) {
        super(context, R.style.CLoadingDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

}
