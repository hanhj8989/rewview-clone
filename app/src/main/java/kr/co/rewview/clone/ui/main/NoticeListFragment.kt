package kr.co.rewview.clone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.rewview.clone.R

class NoticeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notice_list, container, false)
    }

    companion object {
        fun newInstance(): NoticeListFragment {
            return NoticeListFragment()
        }
    }
}
