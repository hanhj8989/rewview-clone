package kr.co.rewview.clone.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.rewview.clone.R
import kr.co.rewview.clone.ui.activity.CashUseActivity

class MyPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        
        view.findViewById<View>(R.id.button_cash_use).setOnClickListener {
            val intent = Intent(requireContext(), CashUseActivity::class.java)
            startActivity(intent)
        }
        
        return view
    }
}
