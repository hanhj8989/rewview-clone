package kr.co.rewview.clone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.rewview.clone.ui.main.ActivityHistoryFragment
import kr.co.rewview.clone.ui.main.NoticeListFragment

class NoticePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActivityHistoryFragment.newInstance()
            1 -> NoticeListFragment.newInstance()
            else -> throw IllegalArgumentException("잘못된 값 - position : $position")
        }
    }

    companion object {
        const val POSITION_ACTIVITY = 0
        const val POSITION_NOTICE = 1

    }
}
