package com.bs.demo.ui

import android.net.Uri
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.config.ActKeyConstants
import com.bs.demo.common.local.LocalBeanInfo
import com.bs.demo.extension.toBeans
import com.bs.demo.model.UserInfoBean
import com.bs.demo.ui.adapter.UserListAdapter
import com.bs.demo.utils.HttpUtil
import io.rong.imkit.RongIM
import io.rong.imkit.RongIM.UserInfoProvider
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : BaseActivity() {
    lateinit var userListAdapter: UserListAdapter
    val list= mutableListOf<UserInfoBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setNavBarTitle("联系人列表")
        userListAdapter= UserListAdapter(list)
        rlv_userlist.adapter=userListAdapter
        HttpUtil.newInstance()
            .post("getAllUser", object : HttpUtil.HttpListener {
                override fun onSuccess(t: String) {
                    list.clear()
                    list.addAll(t.toBeans(UserInfoBean::class.java).filterNot { it.id==LocalBeanInfo.userInfo!!.id })
                    userListAdapter.notifyDataSetChanged()
                }

                override fun onError(msg: String?) {
                }
            })

        userListAdapter.setOnItemClickListener { adapter, view, position ->
            val bean=list[position]
            val userinfo=UserInfo(
                bean.id.toString(),
                bean.name,
                Uri.parse(bean.logo)
            )
            RongIM.setUserInfoProvider({ userId -> userinfo }, true)
            RongIM.getInstance().refreshUserInfoCache(userinfo)

            val bundle=Bundle().apply {
                putString(ActKeyConstants.KEY_ID,bean.id.toString())
                putString(ActKeyConstants.KEY_TITLE,bean.name)
            }
            startAct(ChatActivity::class.java,bundle)
                // RongIM.getInstance().startPrivateChat(this, bean.id.toString(), bean.name)
            //RongIM.getInstance().startConversation(this, Conversation.ConversationType.PRIVATE , bean.id.toString(), bean.name)
        }
    }
}
