package kr.co.rewview.clone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kr.co.rewview.clone.R
import kr.co.rewview.clone.databinding.FragmentMagazineBinding

class MagazineFragment : Fragment() {
    private lateinit var mBinding: FragmentMagazineBinding
    private lateinit var tabLayouts: List<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMagazineBinding.inflate(inflater, container, false)
        setupPager()
        
        return mBinding.root
    }

    private fun setupPager() {
        val pagerAdapter = kr.co.rewview.clone.ui.adapter.MagazinePagerAdapter(this)
        mBinding.viewPager.adapter = pagerAdapter

        setupTabClickListeners()

        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTabSelection(position)
            }
        })

        updateTabSelection(TAB_ALL)
    }
    
    private fun setupTabClickListeners() {
        val tabIds = listOf(
            R.id.vw_magazine_all,
            R.id.vw_magazine_vogue,
            R.id.vw_magazine_allure,
            R.id.vw_magazine_w,
            R.id.vw_magazine_gq
        )

        tabLayouts = tabIds.map { id ->
            mBinding.root.findViewById<View>(id).parent as LinearLayout
        }

        tabLayouts.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                mBinding.viewPager.setCurrentItem(index, true) //탭클릭시 전환
            }
        }
    }

    private fun updateTabSelection(position: Int) {
        val ctx = requireContext()

        tabLayouts.forEachIndexed { index, layout ->
            val titleText = layout.getChildAt(0) as TextView
            val underline = layout.getChildAt(1)
            if (index == position) {
                titleText.setTextAppearance(R.style.Text_28_000000_Bold)
                underline.setBackgroundColor(ContextCompat.getColor(ctx, R.color.basicColor))
            } else {
                titleText.setTextAppearance(R.style.Text_30_A8A6A6)
                underline.setBackgroundColor(ContextCompat.getColor(ctx, R.color.color_E5E5E5))
            }
        }
    }

    private companion object {
        const val TAB_ALL = 0
    }
}
