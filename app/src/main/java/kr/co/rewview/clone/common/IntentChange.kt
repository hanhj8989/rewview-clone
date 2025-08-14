package kr.co.rewview.clone.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.igaworks.v2.core.AdBrixRm
import kr.co.rewview.clone.R
import kr.co.rewview.clone.ui.main.NoticeActivity

import java.util.*

/**
 * Created by Administrator on 2018-05-14.
 */
//화면전환
object IntentChange {

    //알림
    fun intentNotice(act: AppCompatActivity) {
        try {
            val intent = Intent(act, NoticeActivity::class.java)
            act.startActivityForResult(intent, DefineValues.REQUEST_NOTICE)
            act.overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)

            //act.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}