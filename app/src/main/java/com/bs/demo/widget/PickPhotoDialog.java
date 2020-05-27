package com.bs.demo.widget;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.bs.demo.R;
import com.bs.demo.common.base.BaseDialog;


/**
 * Description: 拍照相册选择对话框
 */
public class PickPhotoDialog extends BaseDialog {
    TextView tvTakePhoto;
    TextView tvFromAlbum;
    TextView mbtnCancel;

    private OnDialogItemClickListener onItemClickListener = null;


    public interface OnDialogItemClickListener {
        void fromAlbum();
        void fromTakePhoto();
    }


    public static PickPhotoDialog newInstance() {
        Bundle args = new Bundle();
        PickPhotoDialog fragment = new PickPhotoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_pick_photo;
    }

    public PickPhotoDialog setOnItemClickListener(OnDialogItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    protected void initView(View view) {
        tvFromAlbum=view.findViewById(R.id.tv_from_album);
        tvTakePhoto=view.findViewById(R.id.tv_take_photo);
        mbtnCancel=view.findViewById(R.id.mbtn_cancel);
        mbtnCancel.setOnClickListener(v -> dismiss());
        tvFromAlbum.setOnClickListener(v -> {
            onItemClickListener.fromAlbum();
            dismiss();
        });
        tvTakePhoto.setOnClickListener(v -> {
            onItemClickListener.fromTakePhoto();
            dismiss();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottom_dialog);
    }


}
