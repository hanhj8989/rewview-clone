package kr.co.rewview.clone.ui.main

//import kr.co.rewview.clone.common.HttpClient
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import kr.co.rewview.clone.R
import kr.co.rewview.clone.common.BaseActivity
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
                // 공지사항 활성화
                mBinding.tvNotice.setTextAppearance(R.style.Text_28_000000_Bold)
                mBinding.tvActivityLog.setTextAppearance(R.style.Text_28_A8A6A6)
                mBinding.vwNotice.setBackgroundColor(getColor(R.color.basicColor))
                mBinding.vwActivityLog.setBackgroundColor(getColor(R.color.color_E5E5E5))
            }
            NoticePagerAdapter.POSITION_ACTIVITY -> {
                // 활동내역 활성화
                mBinding.tvActivityLog.setTextAppearance(R.style.Text_28_000000_Bold)
                mBinding.tvNotice.setTextAppearance(R.style.Text_28_A8A6A6)
                mBinding.vwActivityLog.setBackgroundColor(getColor(R.color.basicColor))
                mBinding.vwNotice.setBackgroundColor(getColor(R.color.color_E5E5E5))
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
