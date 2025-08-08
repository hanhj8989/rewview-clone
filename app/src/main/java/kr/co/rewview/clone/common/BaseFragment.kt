package kr.co.rewview.clone.common

import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
// import com.google.gson.JsonObject
// import com.gun0912.tedpermission.PermissionListener
// import com.gun0912.tedpermission.normal.TedPermission
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kr.co.rewview.clone.R
// import kr.co.rewview.clone.common.IntentChange
// import kr.co.rewview.clone.widget.LoadingDialog
import okhttp3.RequestBody
import java.util.*

/**
 * Created by Administrator on 2018-05-10.
 */

open class BaseFragment : Fragment() {

    lateinit var permissionType: String
    lateinit var mDialog: Dialog

    lateinit var mProgressDialog: Dialog
    private var mForceUpdateDialog: Dialog? = null

    private val compositeDisposable = CompositeDisposable()

    val permissionDeniedMsg: String = "권한을 거절 한다면 해당 서비스를 이용에 제한이 있습니다.\n\n해당 권한을 선택 하시기 바랍니다. [설정] > [권한]"
    // 나중에 구현 예정!
    // val listener = object : PermissionListener {
    //     override fun onPermissionGranted() {
    //         onPermissionEnable(permissionType)
    //     }

    //     override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
    //         onPermissionReject(deniedPermissions, permissionType)
    //     }
    // }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialog = AppCompatDialog(requireContext())
    }

    open fun onPermissionEnable(permissionType: String) {}

    open fun onPermissionReject(mutableList: MutableList<String>?, permissionType: String) {}

    fun alertCallback(whice: Int, str: String, flag: Boolean, type: String) {}

    fun alertCallback(flag: Boolean, type: String) {}

    fun goToLogin() {
        // val data = JsonObject()
        // data.addProperty("notification_count", 0)

        // if (requireActivity() is BaseActivity) {
        //     (requireActivity() as BaseActivity).updateNotification(data);
        // }

        CommonLib.delSharedPreference(requireContext())

        // if (requireActivity() is CampaignActivity == false) {
        //     IntentChange.IntentLogin(requireActivity() as AppCompatActivity)
        // }
        // IntentChange.IntentLogin(requireActivity() as AppCompatActivity)
    }

    fun showForceUpdateAlert() {
        if (mForceUpdateDialog != null && mForceUpdateDialog!!.isShowing) {
            return
        }

        mDialog = AlertDialog.Builder(ContextThemeWrapper(requireContext(), R.style.MaterialComponents_AlertDialogTheme))
            .setTitle(R.string.update_available)
            .setCancelable(false)
            .setMessage(getString(R.string.immediate_update_message))
            .setPositiveButton(R.string.update) { dialog, _ ->
                dialog.dismiss()
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.data = Uri.parse("market://details?id=${requireContext().packageName}")
                    startActivity(intent)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }.show()
    }

    // open fun onCallback(transactionId: String, result: JsonObject) {
    //     println("callback fragment : " + transactionId + " , " + result.toString())
    // }



    // fun permissionCheck(permissionType: String, vararg p: String) {
    //     this.permissionType = permissionType

    //     TedPermission.create()
    //         .setPermissionListener(listener)
    //         .setDeniedMessage(permissionDeniedMsg)
    //         .setPermissions(*p)
    //         .check()
    // }

    fun toRequestBody(value: String): RequestBody {
        val body: RequestBody = RequestBody.create(okhttp3.MultipartBody.FORM, value)
        return body
    }


    fun showLoading() {
        // mProgressDialog = LoadingDialog.Builder(requireContext()).show()
    }

    fun hideLoading() {
        // mProgressDialog.dismiss()
    }

    /**
     * 2020/12/14 seongtaek Fragment 스택에서 최초로 push 되는 fragment 여부.
     */
    open fun isRoot() = false

    /**
     * 2020/12/14 seongtaek
     */
    open fun setVisibility(isVisible: Boolean) {}

    /**
     * 앱을 시작한 이후의 신고한 유저 리스트 가져오기.
     *
     * 2023/08/19 seongtaek
     */
    protected fun loadUserReportHistory(): Single<MutableList<String>> = Single.defer {
        val userReportList = mutableListOf<String>()

        val strUserList = CommonLib.getSharedPreference(requireContext(), DefineValues.PROPERTY_USER_REPORT_HISTORY)
        if (strUserList.isNotEmpty()) {
            strUserList.split("|").forEach {
                userReportList.add(it)
            }
        }
        Single.just(userReportList)
    }

    // 폴드 디바이스 여부를 확인하는 함수
    protected fun isFoldableDevice(): Boolean {
        val screenSize = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        val isLargeScreen = screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE

        val isFoldable = // 폴드 디바이스 여부를 확인하는 추가 로직
        // 예를 들어, 폴드 디바이스 특정 제조사나 모델 확인
            // 여기서는 단순히 큰 화면 크기로 판단

//            return isLargeScreen && isFoldable
        return isLargeScreen
    }
}