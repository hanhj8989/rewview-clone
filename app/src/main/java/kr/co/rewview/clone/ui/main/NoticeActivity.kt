package kr.co.rewview.clone.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.orhanobut.logger.Logger
import kr.co.rewview.clone.common.BaseActivity
import kr.co.rewview.clone.common.CommonLib
import kr.co.rewview.clone.common.DefineValues
//import kr.co.rewview.clone.common.HttpClient
import kr.co.rewview.clone.R
import kr.co.rewview.clone.databinding.ActivityNoticeBinding
import kr.co.rewview.clone.ui.adapter.NoticePagerAdapter
//import kr.co.rewview.clone.info.MagazineInfo
//import kr.co.rewview.clone.info.NotificationInfo

/**
 * 알림 화면
 */
class NoticeActivity : BaseActivity() {
    val TAG: String = this::class.simpleName as String

    lateinit var mBinding: ActivityNoticeBinding
    private lateinit var pagerAdapter: NoticePagerAdapter
    var mActivity: NoticeActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice)

        mBinding.toolbar.setNavigationIcon(R.drawable.ico_back)
        setSupportActionBar(mBinding.toolbar)

        setupViewPager()
        setupTabClickListeners()
        
        // 활동내역이 디폴트
        updateTabSelection(NoticePagerAdapter.POSITION_ACTIVITY)
    }

    private fun setupViewPager() {
        pagerAdapter = NoticePagerAdapter(this)
        mBinding.viewPager.adapter = pagerAdapter

        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTabSelection(position)
            }
        })
    }

    private fun setupTabClickListeners() {
        mBinding.llActivityLog.setOnClickListener {
            mBinding.viewPager.currentItem = NoticePagerAdapter.POSITION_ACTIVITY
        }

        mBinding.llNotice.setOnClickListener {
            mBinding.viewPager.currentItem = NoticePagerAdapter.POSITION_NOTICE
        }
    }

    private fun updateTabSelection(position: Int) {
        when (position) {
            NoticePagerAdapter.POSITION_NOTICE -> {
                mBinding.tvNotice.setTextColor(getColor(R.color.basicColor))
                mBinding.tvActivityLog.setTextColor(getColor(R.color.color_707070))
            }
            NoticePagerAdapter.POSITION_ACTIVITY -> {
                mBinding.tvActivityLog.setTextColor(getColor(R.color.basicColor))
                mBinding.tvNotice.setTextColor(getColor(R.color.color_707070))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
