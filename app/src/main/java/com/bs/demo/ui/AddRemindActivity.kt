package com.bs.demo.ui

import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.utils.CalendarUtils
import com.bs.demo.utils.DateUtil
import com.bs.demo.widget.CDatePickerDialog
import com.bs.demo.widget.CTimePickerDialog
import kotlinx.android.synthetic.main.activity_add_remind.*
import java.util.*


class AddRemindActivity : BaseActivity() {
    var time=0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remind)
        setNavBarTitle("设置提醒")
        navigationBar?.setRightStyleBtnText("保存")
        navigationBar?.setNavBarRightBtnListener {
            val input=edt_add_remind_content.text.toString()
            if (input.isNullOrEmpty()){
                showToast("请输入提醒内容")
                return@setNavBarRightBtnListener
            }
            if (time==0L){
                showToast("请选择提醒时间")
                return@setNavBarRightBtnListener
            }
            CalendarUtils.addCalendarEventRemind(this,"提醒",input
                , time,
                DateUtil.yearAddNum(Date(),1).time,0,object :
                    CalendarUtils.onCalendarRemindListener {
                    override fun onSuccess() {
                        showToast("设置成功!")
                    }

                    override fun onFailed(error_code: CalendarUtils.onCalendarRemindListener.Status?) {
                        showToast("设置失败!")
                    }
                })
        }
        tv_add_remind_time.setOnClickListener {
            CDatePickerDialog(this,
                CDatePickerDialog.OnDatePicked { view, year, month, day ->
                    CTimePickerDialog(this,
                        CTimePickerDialog.OnTimePicked { view, hour, minute ->
                            val tv="$year-$month-$day $hour:$minute"
                            time= DateUtil.str2Date(tv,
                                DateUtil.FORMAT_YMDHM).time
                            tv_add_remind_time.text=tv
                        }
                    ).show()
                }).show()
        }
    }
}
