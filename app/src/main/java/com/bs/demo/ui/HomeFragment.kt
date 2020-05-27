package com.bs.demo.ui


import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.bs.demo.R
import com.bs.demo.common.base.BaseFragment
import com.bs.demo.common.config.ActKeyConstants
import io.rong.imkit.RongIM
import io.rong.imkit.RongIM.ConversationListBehaviorListener
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imkit.model.UIConversation
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 主页tab
 */

class HomeFragment : BaseFragment() {

    override fun initView() {
        iv_home_add.setOnClickListener {
            startAct(UserListActivity::class.java)
        }
        val fragmentManage: FragmentManager = childFragmentManager
        val fragement = fragmentManage.findFragmentById(R.id.conversationlist) as ConversationListFragment
        val uri =
            Uri.parse("rong://" + context?.packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(
                    Conversation.ConversationType.PRIVATE.getName(),
                    "false"
                )
                .appendQueryParameter(
                    Conversation.ConversationType.GROUP.getName(),
                    "false"
                )
                .appendQueryParameter(
                    Conversation.ConversationType.PUBLIC_SERVICE.getName(),
                    "false"
                )
                .appendQueryParameter(
                    Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(),
                    "false"
                )
                .appendQueryParameter(
                    Conversation.ConversationType.SYSTEM.getName(),
                    "true"
                )
                .build()
        fragement.uri = uri
        RongIM.setConversationListBehaviorListener(object : ConversationListBehaviorListener {
            override fun onConversationPortraitClick(
                context: Context?,
                conversationType: Conversation.ConversationType?,
                targetId: String?
            ): Boolean {
                return false
            }

            override fun onConversationPortraitLongClick(
                context: Context?,
                conversationType: Conversation.ConversationType?,
                targetId: String?
            ): Boolean {
                return false
            }

            override fun onConversationLongClick(
                context: Context?,
                view: View?,
                conversation: UIConversation?
            ): Boolean {
                return false
            }

            override fun onConversationClick(
                context: Context?,
                view: View?,
                conversation: UIConversation?
            ): Boolean {
                val bundle=Bundle().apply {
                    putString(ActKeyConstants.KEY_ID,conversation!!.conversationTargetId)
                    putString(ActKeyConstants.KEY_TITLE,conversation!!.uiConversationTitle)
                }
                startAct(ChatActivity::class.java,bundle)
                return true
            }
        })
        tv_home_remind.setOnClickListener {
            startAct(AddRemindActivity::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    companion object {
        fun newInstance() = HomeFragment().apply {}
    }
}
