package com.bs.demo.ui

import android.net.Uri
import android.os.Bundle
import com.bs.demo.R
import com.bs.demo.common.base.BaseActivity
import com.bs.demo.common.config.ActKeyConstants
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.model.Conversation


class ChatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val id = intent.getStringExtra(ActKeyConstants.KEY_ID)
        setNavBarTitle(intent.getStringExtra(ActKeyConstants.KEY_TITLE))
        val fragmentManage = supportFragmentManager
        val fragement = fragmentManage.findFragmentById(R.id.conversation) as ConversationFragment
        val uri =
            Uri.parse("rong://" + applicationInfo.packageName).buildUpon()
                .appendPath("conversation")
                .appendPath(Conversation.ConversationType.PRIVATE.name)
                .appendQueryParameter("targetId", id).build()

        fragement.uri = uri

    }
}
