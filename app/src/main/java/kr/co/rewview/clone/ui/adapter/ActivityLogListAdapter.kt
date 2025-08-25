package kr.co.rewview.clone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kr.co.rewview.clone.R
import kr.co.rewview.clone.databinding.LayoutActivityLogItemBinding

data class ActivityLogItem(
    val title: String,
    val description: String,
    val date: String,
    val flag: String,
    val brandImageResId: Int? = null
)

class ActivityLogListAdapter : RecyclerView.Adapter<ActivityLogListAdapter.ActivityLogViewHolder>() {

    private val items = mutableListOf<ActivityLogItem>()

    init {
        // 더미데이터 5개 생성
        items.addAll(listOf(
            ActivityLogItem(
                title = "입생로랑 나이트 리부트 세럼",
                description = "신청완료! 선정 시 알려드려요.",
                date = "2025.08.22",
                flag = "campaign"
            ),
            ActivityLogItem(
                title = "레벨업!",
                description = "르뷰 메이트로 진화했어요.",
                date = "2025.08.22",
                flag = "level"
            ),
            ActivityLogItem(
                title = "1:1문의",
                description = "문의에 대한 답변이 등록되었습니다.",
                date = "2025.08.22",
                flag = "notice"
            ),
            ActivityLogItem(
                title = "르뷰캐시",
                description = "캐시 출금을 신청했어요.",
                date = "2025.08.22",
                flag = "cash"
            ),
            ActivityLogItem(
                title = "미션성공!",
                description = "르뷰점수 100000점 획득했어요.",
                date = "2025.08.22",
                flag = "campaign"
            )
        ))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityLogViewHolder {
        return ActivityLogViewHolder(LayoutActivityLogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ActivityLogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ActivityLogViewHolder(private val binding: LayoutActivityLogItemBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ActivityLogItem) {
            binding.apply {
                // flag에 따라 다른 아이콘 설정
                val logoImageResId = when (item.flag) {
                    "campaign" -> {
                        R.drawable.v2_ic_vt_notification_notice_32dp
                    }
                    "cash" -> {
                        R.drawable.v2_ic_vt_notification_cash_32dp
                    }
                    "notice" -> {
                        R.drawable.v2_ic_vt_notification_inquiry_32dp
                    }
                    "level" -> {
                        R.drawable.v2_ic_vt_notification_level_32dp
                    }
                    else -> {
                        R.drawable.notification_notice
                    }
                }
                
                ivBrand.setImageResource(logoImageResId)
                tvDate.text = item.date
                textView122.text = item.title
                textView125.text = item.description
            }
        }
    }
}
