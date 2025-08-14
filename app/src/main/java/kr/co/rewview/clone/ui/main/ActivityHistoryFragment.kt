package kr.co.rewview.clone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.rewview.clone.R

class ActivityHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_history, container, false)
    }

    companion object {
        fun newInstance(): ActivityHistoryFragment {
            return ActivityHistoryFragment()
        }
    }
}
