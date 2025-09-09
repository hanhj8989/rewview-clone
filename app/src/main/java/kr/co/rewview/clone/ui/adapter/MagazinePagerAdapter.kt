package kr.co.rewview.clone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.rewview.clone.ui.fragment.MagazineListFragment

class MagazinePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            POSITION_ALL -> MagazineListFragment.newInstance(MagazineListFragment.EXTRA1_ALL)
            POSITION_VOGUE -> MagazineListFragment.newInstance(MagazineListFragment.EXTRA1_VOGUE)
            POSITION_ALLURE -> MagazineListFragment.newInstance(MagazineListFragment.EXTRA1_ALLURE)
            POSITION_W -> MagazineListFragment.newInstance(MagazineListFragment.EXTRA1_W)
            POSITION_GQ -> MagazineListFragment.newInstance(MagazineListFragment.EXTRA1_GQ)
            else -> throw IllegalArgumentException("그 외 position : $position")
        }
    }

    companion object {
        const val POSITION_ALL = 0
        const val POSITION_VOGUE = 1
        const val POSITION_ALLURE = 2
        const val POSITION_W = 3
        const val POSITION_GQ = 4
    }
}
