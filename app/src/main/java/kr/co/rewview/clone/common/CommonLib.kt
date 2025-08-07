package kr.co.rewview.clone.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Html
import android.text.Spanned
import android.text.format.DateFormat
import android.transition.TransitionManager
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.igaworks.v2.core.AdBrixRm
import com.orhanobut.logger.Logger
import io.reactivex.Single
import kr.co.rewview.clone.BuildConfig
import kr.co.rewview.clone.R
import okhttp3.ResponseBody
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.io.*
import java.nio.charset.Charset
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Administrator on 2018-05-10.
 */

object CommonLib {
    var uuid: UUID? = null

    fun isNetworkConnected(mActivity: Activity): Boolean {
        val cm: ConnectivityManager = mActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null
    }

    fun isValidEmailAddress(email: String): Boolean {
        val ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p: Pattern = Pattern.compile(ePattern)
        val m: Matcher = p.matcher(email)

        return m.matches()
    }

    fun isValidPw(pw: String): Boolean {
//        val ePattern = "^(?=.*\\d)(?=.*[~`!@#\$%\\^&*()-?<>.,=+_;\':\"])(?=.*[a-zA-Z]).{8,15}\$"
        val ePattern = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,15}\$"
        val p: Pattern = Pattern.compile(ePattern)
        val m: Matcher = p.matcher(pw)

        return m.matches()
    }

    fun setSharedPreference(context: Context, key: String, value: String?) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putString(key, value).commit()
    }

    fun setSharedPreference(context: Context, key: String, value: Int) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = prefs.edit()

        editor.putInt(key, value).commit()
    }

    fun getSharedPreference(context: Context, key: String): String {
        return getSharedPreference(context, key, "")
    }

    fun getSharedPreference(context: Context, key: String, def: String): String {
        try {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return prefs.getString(key, def)!!
        } catch (e: Exception) {
            e.printStackTrace()
            return def
        }
    }

    fun getIntSharedPreference(context: Context, key: String): Int {
        return getSharedPreference(context, key, 0)
    }

    fun getSharedPreference(context: Context, key: String, def: Int): Int {
        try {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return prefs.getInt(key, def)
        } catch (e: Exception) {
            e.printStackTrace()
            return def
        }
    }

    /*
    앱 내 데이터 저장공간의 해당키의 데이터를  지우는 메서드
     */
    fun delSharedPreference(context: Context, key: String) {
        try {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = prefs.edit()

            editor.remove(key).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*
    앱 내 데이터 저장공간의 데이터를 모두 지우는 메서드
     */
    fun delSharedPreference(context: Context) {
        try {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor: SharedPreferences.Editor = prefs.edit()

            editor.clear().commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getToday(): String {
        val dateTemplate = "yyyy-MM-dd"
        val now: Long = Calendar.getInstance().timeInMillis

        return DateFormat.format(dateTemplate, Date(now)).toString()
    }

    fun getYear(): String {
        val dateTemplate = "yyyy"
        val now: Long = Calendar.getInstance().timeInMillis

        return DateFormat.format(dateTemplate, Date(now)).toString()
    }

    fun getMonth(): String {
        val dateTemplate = "MM"
        val now: Long = Calendar.getInstance().timeInMillis

        return DateFormat.format(dateTemplate, Date(now)).toString()
    }

    fun getDay(): String {
        val dateTemplate = "dd"
        val now: Long = Calendar.getInstance().timeInMillis

        return DateFormat.format(dateTemplate, Date(now)).toString()
    }

    fun getHanDate(date: String): String {
        val tempDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)

        val newDate = SimpleDateFormat("MM월 dd일").format(tempDate)

        return newDate
    }

    fun zeroFill(num: String): String {
        var result = ""
        if (Integer.parseInt(num) < 10) {
            result = "0" + num
        } else {
            result = num
        }

        return result
    }

    fun zeroFill(num: Int): String {
        var result = ""
        if (num < 10) {
            result = "0" + Integer.toString(num)
        } else {
            result = Integer.toString(num)
        }

        return result
    }

    fun commaWon(value: String): String {
        val temp = Integer.parseInt(value)
        val commas = DecimalFormat("#,###")

        return commas.format(temp)
    }

    fun commaWon(value: Int): String {
        val temp = value
        val commas = DecimalFormat("#,###")

        return commas.format(temp)
    }

    fun keyboardSlideUp(context: Context, layout: View) {
        layout.requestFocus()
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.showSoftInput(layout, InputMethodManager.SHOW_FORCED)
    }

    fun keyboardSlideDown(mActivity: Activity) {
        val imm: InputMethodManager = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (mActivity.currentFocus != null) {
            imm.hideSoftInputFromWindow(mActivity.currentFocus!!.windowToken, 0)
        }
    }

    fun keyboardSlideDown(context: Context, layout: View) {
        val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(layout.windowToken, 0)
        layout.clearFocus()
    }

    fun parseJson(str: String): JsonObject? {
        var result: JsonObject?

        try {
            var jsonParser = JsonParser()
            result = jsonParser.parse(str) as JsonObject
        } catch (e: Exception) {
            result = null
        }
        return result
    }

    fun parseJsonArray(str: String): JsonArray {
        var result = JsonArray()

        try {
            var jsonParser = JsonParser()
            result = jsonParser.parse(str) as JsonArray
        } catch (e: Exception) {
            result = JsonArray()
        }

        return result
    }

    fun parseJson(json: JsonObject?, str: String): JsonObject? {
        var result: JsonObject?

        try {
            result = json?.get(str) as JsonObject
        } catch (e: Exception) {
            result = null
        }
        return result
    }

    fun parseJson(json: JsonArray?, idx: Int): JsonObject? {
        var result: JsonObject?

        try {
            result = json?.get(idx) as JsonObject
        } catch (e: Exception) {
            result = null
        }
        return result
    }

    fun parseJsonArray(json: JsonObject?, str: String): JsonArray {
        var result: JsonArray
        var obj = JsonObject()

        try {
            if (json != null) obj = json
            result = obj.getAsJsonArray(str)
        } catch (e: Exception) {
            //e.printStackTrace()
            result = JsonArray()
        }

        return result
    }

    fun getJsonBoolean(json: JsonObject?, str: String): Boolean {
        var result = false

        try {
            if (json != null) {
                result = json.get(str).asBoolean
            }
        } catch (e: Exception) {
            result = false
        }

        return result
    }

    fun getJsonInt(json: JsonObject?, str: String): Int? {
        return getJsonInt(json, str, 0)
    }

    fun getJsonInt(json: JsonObject?, str: String, nullStr: Int): Int? {
        var result: Int

        try {
            if (json != null) {
                result = json.get(str).asInt
            } else {
                result = nullStr
            }
        } catch (e: Exception) {
            result = nullStr
        }

        return result
    }

    fun getJsonDouble(json: JsonObject?, str: String): Double? {
        return getJsonDouble(json, str, 0.toDouble())
    }

    fun getJsonDouble(json: JsonObject?, str: String, nullStr: Double): Double? {
        var result: Double

        try {
            if (json != null) {
                result = json.get(str).asDouble
            } else {
                result = nullStr
            }
        } catch (e: Exception) {
            result = nullStr
        }

        return result
    }

    fun getJsonLong(json: JsonObject?, str: String): Long? {
        return getJsonLong(json, str, 0.toLong())
    }

    fun getJsonLong(json: JsonObject?, str: String, nullStr: Long): Long? {
        var result: Long

        try {
            if (json != null) {
                result = json.get(str).asLong
            } else {
                result = nullStr
            }
        } catch (e: Exception) {
            result = nullStr
        }

        return result
    }

    fun getJsonString(json: JsonObject?, str: String): String? {
        return getJsonString(json, str, "")
    }

    fun getJsonString(json: JsonObject?, str: String, nullStr: String): String? {
        var result: String?

        try {
            result = json?.get(str)?.asString

            if (result!!.isEmpty() || result.equals("") || result.equals("NULL") || result.equals("null")) {
                result = nullStr
            }
        } catch (e: Exception) {
            result = nullStr
        }
        return result
    }

    /*
    단말기에 있는 내 전화번호 문자로 추출하기
    AndroidManifest.xml : 퍼미션 설정 필용 : <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     */
    @RequiresPermission(anyOf = [Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE])
    fun getMyPhoneNumber(mActivity: Activity): String {
        val mTelephonyMgr = mActivity.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var tmp = ""

        try {
            tmp = mTelephonyMgr.line1Number
            if (tmp == null || "".equals(tmp)) {
                tmp = ""
            } else {
                tmp = tmp.replace("+82", "0")
                tmp = tmp.replace("-", "")
            }
        } catch (e: SecurityException) {
        }

        return tmp
    }

    fun getDeviceUUID(activity: Activity): String {
        return getDeviceUUID(activity.baseContext)
    }

    fun getDeviceUUID(context: Context): String {
        uuid.let {
            val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

            try {
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes(androidId.toByteArray(Charset.forName("utf8")))
                } else {
                    try {
                        val mTelephonyManager: TelephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                        var deviceId: String = ""
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            deviceId = mTelephonyManager.imei
                        } else {
                            deviceId = mTelephonyManager.deviceId
                        }

                        if (deviceId != null) {
                            uuid = UUID.nameUUIDFromBytes(deviceId.toByteArray(Charset.forName("utf8")))
                        } else {
                            uuid = UUID.randomUUID()
                        }
                    } catch (e: SecurityException) {
                    }
                }
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException(e)
            }
        }

        return uuid.toString()
    }

    fun getDrawable(activity: FragmentActivity, id: Int): Drawable? {
        return getDrawable(activity as Activity, id)
    }

    fun getDrawable(activity: Activity, id: Int): Drawable? {
        return getDrawable(activity.baseContext, id)
    }

    fun getDrawable(context: Context, id: Int): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }

    fun getColor(activity: FragmentActivity, id: Int): Int {
        return getColor(activity as Activity, id)
    }

    fun getColor(activity: Activity, id: Int): Int {
        return getColor(activity.baseContext, id)
    }

    fun getColor(context: Context, id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    fun fromHtml(text: String): Spanned {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Html.fromHtml(text)
        }
        return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    }

    fun getTextFileToString(mActivity: Activity, fileName: String): String {
        var reader: BufferedReader? = null
        var mLine: StringBuilder = StringBuilder()

        try {
            reader = BufferedReader(InputStreamReader(mActivity.assets.open(fileName), "UTF-8"))

            for (line in reader.readLines()) {
                mLine.append(line + "<br>")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            mLine = StringBuilder()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {

                }
            }
        }

        return mLine.toString()
    }

    fun getAppVersionCode(context: Context): Int {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            return 0
        }
    }

    fun getAppVersionName(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)

            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            return ""
        }
    }

    fun isPackageInstalled(context: Context, name: String): Boolean {
        val pm = context.packageManager

        try {
            pm.getPackageInfo(name, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }

        return false
    }

    fun setStyle(context: Context, textView: TextView, id: Int) {
        textView.setTextAppearance(id)
    }

    fun setCheckBoxStyle(context: Context, checkBox: CheckBox, id: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkBox.setTextAppearance(id)
        } else {
            checkBox.setTextAppearance(context, id)
        }
    }

    fun dipToPixels(context: Context, dipValue: Float): Float {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    }

    fun basicAlertDialog(mActivity: Activity, msg: String) {
        basicAlertDialog(mActivity, "", msg)
    }

    fun basicAlertDialog(mActivity: Activity, title: String, msg: String) {
        val builder = AlertDialog.Builder(mActivity)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(mActivity.resources.getString(R.string.dialog_ok)) { dialog, which ->

        }
        val alert = builder.create()
        alert.show()
    }

    fun listAlertDialog(mActivity: BaseActivity, items: Array<String>, type: String, title: String) {
        val builder = AlertDialog.Builder(mActivity)
        builder.setTitle(title)
        builder.setItems(items) { dialog, which ->
            mActivity.alertCallback(which, items[which], true, type)
        }
        val alert = builder.create()
        alert.show()
    }

    fun listAlertDialog(mFragment: BaseFragment, items: Array<String>, type: String, title: String) {
        val builder = AlertDialog.Builder(mFragment.activity)
        builder.setTitle(title)
        builder.setItems(items) { dialog, which ->
            mFragment.alertCallback(which, items[which], true, type)
        }
        val alert = builder.create()
        alert.show()
    }

    fun alertDialog(mActivity: BaseActivity, type: String, msg: String) {
        alertDialog(mActivity, type, "", msg)
    }

    fun alertDialog(mActivity: BaseActivity, type: String, title: String, msg: String) {
        val builder = AlertDialog.Builder(mActivity)
        builder.setCancelable(false)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(mActivity.resources.getString(R.string.dialog_ok)) { dialog, which ->
            mActivity.alertCallback(true, type)
        }
        builder.setNegativeButton(mActivity.resources.getString(R.string.dialog_cancel)) { dialog, which ->
            mActivity.alertCallback(false, type)
        }
        val alert = builder.create()
        alert.show()
    }

    fun alertDialog(mFragment: BaseFragment, type: String, msg: String) {
        alertDialog(mFragment, type, "", msg)
    }

    fun alertDialog(mFragment: BaseFragment, type: String, title: String, msg: String) {
        val builder = AlertDialog.Builder(mFragment.activity)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(mFragment.resources.getString(R.string.dialog_ok)) { dialog, which ->
            mFragment.alertCallback(true, type)
        }
        builder.setNegativeButton(mFragment.resources.getString(R.string.dialog_cancel)) { dialog, which ->
            mFragment.alertCallback(false, type)
        }
        val alert = builder.create()
        alert.show()
    }

    fun actionURL(activity: Activity, url: String) {
        try {
            var uri = Uri.parse(url)
            if (!url.contains("http://") && !url.contains("https://")) {
                uri = Uri.parse("http://${url}")
            }
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        } catch (e: Exception) {
            if (e is ActivityNotFoundException) {
                Toast.makeText(activity, R.string.not_found_browser, Toast.LENGTH_SHORT).show()
            } else {
                e.printStackTrace()
            }
        }
    }

    fun actionURL(context: Context, url: String) {
        try {
            var uri = Uri.parse(url)
            if (!url.contains("http://") && !url.contains("https://")) {
                uri = Uri.parse("http://${url}")
            }
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun actionDial(mActivity: Activity, phone: String) {
        try {
            val temp = phone.replace("-", "")

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + temp))
            mActivity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("MissingPermission")
    fun actionCall(mActivity: Activity, phone: String) {
        try {
            val temp = phone.replace("-", "")

            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + temp))
            mActivity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun actionSMS(mActivity: Activity, phone: String, content: String) {
        val temp = phone.replace("-", "")

        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.putExtra("address", temp)
            intent.putExtra("sms_body", content)
            intent.type = "vnd.android-dir/mms-sms"

            mActivity.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra("address", temp)
            intent.putExtra("sms_body", content)
            intent.type = "vnd.android-dir/mms-sms"

            mActivity.startActivity(intent)
        }
    }

    fun getUrl(url: String): String {
        var str = url

        if (!str.contains("http://") && !str.contains("https://")) {
            str = "https://rewd1w.rewview.co.kr${str}"
        }

        return str
    }

    @SuppressLint("NewApi")
    fun layoutChange(mActivity: Activity, layout: ConstraintLayout?, originalLayoutId: Int, changeLayoutId: Int, visible: Boolean) {
        val constraintLayout: ConstraintLayout? = layout
        val originalConstraints = ConstraintSet()
        val changeConstraints = ConstraintSet()

        originalConstraints.clone(mActivity, originalLayoutId)
        changeConstraints.clone(mActivity, changeLayoutId)

        if (visible) {
            TransitionManager.beginDelayedTransition(constraintLayout)
            changeConstraints.applyTo(constraintLayout)
        } else {
            TransitionManager.beginDelayedTransition(constraintLayout)
            originalConstraints.applyTo(constraintLayout)
        }
    }

    fun writeResponseBodyToDisk(context: Context, body: ResponseBody): Boolean {
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null

        try {
            val download = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            var name = "aaa.png"

            println("download : ${download.absolutePath}, ${download}, ${download.absolutePath + File.separator + name}")
            val file = File(download.absolutePath + File.separator + name)

            val fileReader = ByteArray(4096)
            val fileSize = body.contentLength()
            var fileSizeDownloaded: Long = 0


            inputStream = body.byteStream()
            outputStream = FileOutputStream(file)

            while (true) {
                val read = inputStream.read(fileReader)

                if (read == -1) break

                outputStream.write(fileReader, 0, read)
                fileSizeDownloaded += read

                println("file download: ${fileSizeDownloaded} of ${fileSize}")
            }

            outputStream.flush()

            context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)))

            return true
        } catch (e: IOException) {
            e.printStackTrace()

            return false
        } finally {
            if (inputStream != null) inputStream.close()
            if (outputStream != null) outputStream.close()
        }
    }

    fun getAdBrixRm(activity: Activity): AdBrixRm.AttrModel {
        return AdBrixRm.AttrModel()
                .setAttrs("user_idx", 113455)
                .setAttrs("device_type", "A")
                .setAttrs("device_token", getDeviceUUID(activity))

    }

    /**
     *
     * 해당 URL 의 전체 HTML 리턴 2020/09/21 seongtaek
     *
     */
    fun parsingUrlToHtml(url: String):Single<String> = Single.defer {
        val response = Jsoup.connect(url).method(Connection.Method.GET).execute()
        val document = response.parse()

        Logger.d("document = ${document.html()}")

        Single.just(document.html())
    }

    fun getLevelDrawable(context: Context, level: Int): Drawable? {
        if (DefineValues.USER_LEVEL_DRAWABLE.size == 0) {
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_0)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_1)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_2)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_3)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_4)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_5)!!)
            DefineValues.USER_LEVEL_DRAWABLE.add(getDrawable(context, R.drawable.user_level_6)!!)
        }

        // 2020.07.08 st_park / 레벨이 7 이상일 경우 레벨 이미지를 6으로 표시
        return if (level > 6) {
            getDrawable(context, R.drawable.user_level_6)
        } else {
            DefineValues.USER_LEVEL_DRAWABLE[level]
        }
    }

    fun getTimestamp() = (System.currentTimeMillis() / 1000).toString()
}