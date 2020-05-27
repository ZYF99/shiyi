package com.bs.demo.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;

import com.bs.demo.R;


/**
 * Author:  zjw
 * Email:   crazyzjw@foxmail.com
 * Date:    2018/9/27
 * Description: 自定义导航栏
 */
public class NavigationBar extends LinearLayout {
    private TextView tvNavbarRight;
    private LinearLayout llyNavbarRoot;
    private ImageButton ibtnNavbarBack;
    private TextView tvNavbarTitle;
    private ImageView btnNavbarRight;
    private TextView tvNavbarRight2;

    private NavBarBackListener navBarBackListener;
    private NavBarRightBtnListener navBarRightBtnListener;
    private NavBarRightBtnLongClickListener navBarRightBtnLongClickListener;
    public interface NavBarBackListener {
        void onClickBack();
    }

    public interface NavBarRightBtnListener {
        void onClick();
    }
    public interface NavBarRightBtnLongClickListener {
        void onLongClick();
    }

    public void setNavBarBackListener(NavBarBackListener navBarBackListener) {
        this.navBarBackListener = navBarBackListener;
    }

    public void setNavBarRightBtnListener(NavBarRightBtnListener navBarRightBtnListener) {
        this.navBarRightBtnListener = navBarRightBtnListener;
    }

    public void setNavBarRightBtnLongClickListener(NavBarRightBtnLongClickListener navBarRightBtnLongClickListener) {
        this.navBarRightBtnLongClickListener = navBarRightBtnLongClickListener;
    }

    public NavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public void setNavBarBg(@ColorRes int bg) {
        setBackgroundColor(getResources().getColor(bg));
    }

    public void setBackIcon(int icon) {
        ibtnNavbarBack.setImageResource(icon);
    }

    public void setTitile(String s) {
        tvNavbarTitle.setText(s);
    }
    public void setTitileColor(@ColorRes int color) {
        tvNavbarTitle.setTextColor(getResources().getColor(color));
    }


    public void setRightBtnIcon(int icon) {
        btnNavbarRight.setImageResource(icon);
        btnNavbarRight.setVisibility(VISIBLE);
    }

    public void setRightBtnText(String s) {
        tvNavbarRight.setText(s);
        tvNavbarRight.setVisibility(VISIBLE);
    }

    public void setRightStyleBtnText(String s) {
        tvNavbarRight2.setText(s);
        tvNavbarRight2.setVisibility(VISIBLE);
    }

    public TextView getTvNavbarRight() {
        return tvNavbarRight;
    }
    public TextView getTvNavbarRight2() {
        return tvNavbarRight2;
    }

    public void hideRight(){
        tvNavbarRight.setVisibility(GONE);
        btnNavbarRight.setVisibility(GONE);
        tvNavbarRight2.setVisibility(GONE);
    }
    public String getTitle(){
        return tvNavbarTitle.getText().toString();
    }
    public void hideBack(){
        ibtnNavbarBack.setVisibility(GONE);
    }
    private void initView(AttributeSet attrs) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.viewgroup_navbar, this);
        ibtnNavbarBack = v.findViewById(R.id.ibtn_navbar_back);
        tvNavbarTitle = v.findViewById(R.id.tv_navbar_title);
        btnNavbarRight = v.findViewById(R.id.btn_navbar_right);
        llyNavbarRoot = v.findViewById(R.id.lly_navbar_root);
        tvNavbarRight=v.findViewById(R.id.tv_navbar_right);
        tvNavbarRight2=v.findViewById(R.id.tv_navbar_style_right);
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NavigationBar);
            ibtnNavbarBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navBarBackListener != null) {
                        navBarBackListener.onClickBack();
                    }
                }
            });

            btnNavbarRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navBarRightBtnListener != null) {
                        navBarRightBtnListener.onClick();
                    }
                }
            });
            tvNavbarRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navBarRightBtnListener != null) {
                        navBarRightBtnListener.onClick();
                    }
                }
            });
            tvNavbarRight2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (navBarRightBtnListener != null) {
                        navBarRightBtnListener.onClick();
                    }
                }
            });
            btnNavbarRight.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (navBarRightBtnLongClickListener!=null){
                        navBarRightBtnLongClickListener.onLongClick();
                    }
                    return true;
                }
            });
            a.recycle();
        }
    }

}
