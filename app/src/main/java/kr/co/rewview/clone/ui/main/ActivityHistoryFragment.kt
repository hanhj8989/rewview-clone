package kr.co.rewview.clone.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.rewview.clone.R
import kr.co.rewview.clone.databinding.FragmentActivityHistoryBinding
import kr.co.rewview.clone.ui.adapter.ActivityLogListAdapter

class ActivityHistoryFragment : Fragment() {
    private lateinit var mBinding: FragmentActivityHistoryBinding
    private lateinit var activityLogListAdapter: ActivityLogListAdapter
    private val mFragment = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_activity_history, container, false)
        mBinding.fragment = mFragment

        activityLogListAdapter = ActivityLogListAdapter()

        setupRecyclerView()

        return mBinding.root
    }

    private fun setupRecyclerView() {
        mBinding.rvList.adapter = activityLogListAdapter
        mBinding.rvList.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        fun newInstance(): ActivityHistoryFragment {
            return ActivityHistoryFragment()
        }
    }
}
