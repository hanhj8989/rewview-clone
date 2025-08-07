package kr.co.rewview.clone.common

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
// Data Binding - 나중에 필요시 활성화
// import androidx.databinding.BindingAdapter
// import androidx.databinding.DataBindingUtil

// Gson - 나중에 필요시 활성화
// import com.google.gson.JsonObject

// Ted Permission - 나중에 필요시 활성화
// import com.gun0912.tedpermission.PermissionListener
// import com.gun0912.tedpermission.normal.TedPermission

    // AdBrix - 나중에 필요시 활성화
    import com.igaworks.v2.core.AdBrixRm

    // RxJava - 나중에 필요시 활성화
    import io.reactivex.Single

import kr.co.rewview.clone.R
// Legacy activities - 나중에 구현 예정
// import kr.co.rewview.clone.legacy.activity.CampaignActivity
// import kr.co.rewview.clone.legacy.activity.HomeMainActivity
// import kr.co.rewview.clone.legacy.activity.MagazineActivity
// import kr.co.rewview.clone.legacy.activity.MyPageActivity
// import kr.co.rewview.clone.legacy.activity.ReviewActivity
// import kr.co.rewview.clone.legacy.activity.ReviewMainActivityV2
// import kr.co.rewview.clone.legacy.activity.TalkActivity
// 나중에 구현 예정
// import kr.co.rewview.clone.common.IntentChange
// import kr.co.rewview.clone.databinding.DialogDefaultBinding
// import kr.co.rewview.clone.databinding.DialogDefaultOkV2Binding
// import kr.co.rewview.clone.databinding.DialogTitleMessageYesNoBinding
// import kr.co.rewview.clone.info.DialogInfo
// import kr.co.rewview.clone.info.UserInfoV2
// import kr.co.rewview.clone.info.UserNotificationInfo
// import kr.co.rewview.clone.util.DebouncingOnClickListener
// import kr.co.rewview.clone.widget.LoadingDialog
import okhttp3.RequestBody

/**
 * Created by Administrator on 2018-05-10.
 */

private const val STATE_USER_INFO = "user_info"

open class BaseActivity : AppCompatActivity() {

    lateinit var permissionType: String
    lateinit var activity: AppCompatActivity
    lateinit var mDialog: Dialog
    private var mForceUpdateDialog: Dialog? = null

    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2021/01/21 seongtaek 저장된 인스턴스 상태를 사용하여 유저 정보 복원
        // if (savedInstanceState != null) {
        //     with(savedInstanceState) {
        //         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        //             getSerializable(STATE_USER_INFO, UserInfoV2::class.java)?.let {
        //                 DefineValues.USER_INFO_V2 = it
        //             }
        //         } else {
        //             getSerializable(STATE_USER_INFO)?.let {
        //                 DefineValues.USER_INFO_V2 = it as UserInfoV2
        //             }
        //         }
        //     }
        // }

        mDialog = Dialog(this)
        activity = this
    }

    /**
     * 2021/01/21 seongtaek
     *
     * 시스템에서 메모리 부족으로 활동이 정지되기 시작하면 인스턴스 상태 번들에 상태 정보를 저장할 수 있도록 함.
     */
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        // outState.putSerializable(STATE_USER_INFO, DefineValues.USER_INFO_V2)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    // val mUserNotificationInfo = UserNotificationInfo()

    open fun alertCallback(whice: Int, str: String, flag: Boolean, type: String) {}

    open fun alertCallback(flag: Boolean, type: String) {}

}