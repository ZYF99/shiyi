package com.bs.demo.ui


import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bs.demo.R
import com.bs.demo.common.SharedPreferencesUtils
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.model.DiaryBean
import com.bs.demo.ui.adapter.DiaryListAdapter
import com.chad.library.adapter.base.BaseQuickAdapter

/**
 * 备忘录tab
 */
class DiaryFragment : BaseFragment() {
    override fun initView() {
        view?.findViewById<RecyclerView>(R.id.rv_diary)?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DiaryListAdapter(
                getDiaryList(),
                onClick = {
                    showAddDiaryDialog(it)
                },
                onLongClick = {
                    showDeleteWarning(it)
                }
            )
        }
        view?.findViewById<ImageView>(R.id.iv_diary_add)?.setOnClickListener {
            showAddDiaryDialog()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_diary, container, false)
    }

    private fun showAddDiaryDialog(diaryBean: DiaryBean? = null) {
        val diaryDialogView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_add_diary, null, false)
        if (diaryBean != null) {
            diaryDialogView.findViewById<EditText>(R.id.et_title).setText(diaryBean.title)
            diaryDialogView.findViewById<EditText>(R.id.et_content).setText(diaryBean.content)
        }
        AlertDialog.Builder(context).setView(diaryDialogView)
            .setCancelable(false)
            .setNegativeButton("取消", null)
            .setPositiveButton(
                if (diaryBean == null) "添加"
                else "修改"
            ) { _, _ ->
                if (diaryBean == null) {//增加
                    insertIntoDiaryList(
                        DiaryBean(
                            System.currentTimeMillis().toString(),
                            diaryDialogView.findViewById<EditText>(R.id.et_title).text.toString(),
                            diaryDialogView.findViewById<EditText>(R.id.et_content).text.toString()
                        )
                    )
                } else {//修改
                    updateDiaryList(
                        diaryBean.id,
                        DiaryBean(
                            System.currentTimeMillis().toString(),
                            diaryDialogView.findViewById<EditText>(R.id.et_title).text.toString(),
                            diaryDialogView.findViewById<EditText>(R.id.et_content).text.toString()
                        )
                    )
                }

            }
            .create()
            .show()
    }

    private fun getDiaryList(): MutableList<DiaryBean> =
        SharedPreferencesUtils.getListData("diaryList", DiaryBean::class.java)

    private fun insertIntoDiaryList(diaryBean: DiaryBean) {
        SharedPreferencesUtils.putListData("diaryList", getDiaryList().apply { add(diaryBean) })
        (view?.findViewById<RecyclerView>(R.id.rv_diary)?.adapter as BaseQuickAdapter<DiaryBean, *>).replaceData(
            getDiaryList()
        )
    }

    private fun updateDiaryList(oldId: String, newDiaryBean: DiaryBean) {
        SharedPreferencesUtils.putListData("diaryList", getDiaryList().apply {
            find { it.id == oldId }?.apply {
                title = newDiaryBean.title
                content = newDiaryBean.content
            }
        })
        (view?.findViewById<RecyclerView>(R.id.rv_diary)?.adapter as BaseQuickAdapter<DiaryBean, *>).replaceData(
            getDiaryList()
        )
    }

    private fun showDeleteWarning(diaryBean: DiaryBean) {
        AlertDialog.Builder(context)
            .setTitle("警告")
            .setTitle("确定删除此条备忘录吗？删除后将不能恢复")
            .setCancelable(false)
            .setNegativeButton("取消", null)
            .setPositiveButton("确定") { _, _ ->
                SharedPreferencesUtils.putListData("diaryList", getDiaryList().apply {
                    removeAll { it.id == diaryBean.id }
                })
                (view?.findViewById<RecyclerView>(R.id.rv_diary)?.adapter as BaseQuickAdapter<DiaryBean, *>).replaceData(
                    getDiaryList()
                )
            }.create().show()
    }

    companion object {
        fun newInstance() = DiaryFragment().apply {}
    }
}
