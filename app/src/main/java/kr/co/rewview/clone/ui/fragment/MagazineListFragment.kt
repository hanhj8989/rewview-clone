package kr.co.rewview.clone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.rewview.clone.databinding.FragmentMagazineListBinding
import kr.co.rewview.clone.ui.adapter.MagazineAdapter

class MagazineListFragment : Fragment() {

    private var _binding: FragmentMagazineListBinding? = null
    private val binding get() = _binding!!

    private lateinit var magazineAdapter: MagazineAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMagazineListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        magazineAdapter = MagazineAdapter()
        binding.rvMagazineList.adapter = magazineAdapter
        binding.rvMagazineList.layoutManager = LinearLayoutManager(context) // 세로방향으로 데이터 표시

        val extra1: String? = arguments?.getString(ARG_EXTRA1) //전달받은 extra1으로 구분함
        magazineAdapter.filterByExtra1(extra1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_EXTRA1 = "arg_extra1"

        fun newInstance(extra1: String?): MagazineListFragment {
            val fragment = MagazineListFragment()
            val args = Bundle()
            if (extra1 != null) {
                args.putString(ARG_EXTRA1, extra1)
            }
            fragment.arguments = args
            return fragment
        }

        val EXTRA1_ALL: String? = null
        val EXTRA1_VOGUE: String = "142"
        val EXTRA1_ALLURE: String = "144"
        val EXTRA1_W: String = "143"
        val EXTRA1_GQ: String = "145"
    }
}
