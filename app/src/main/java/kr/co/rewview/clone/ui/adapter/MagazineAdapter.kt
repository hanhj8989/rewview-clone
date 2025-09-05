package kr.co.rewview.clone.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.rewview.clone.databinding.LayoutMagazineItemBinding

data class MagazineItem(
    val extra1: String,
    val brand: String,
    val title: String? = null,
    val src: String? = null,
    val cashReward: String = "20캐시 적립"
)

class MagazineAdapter : RecyclerView.Adapter<MagazineAdapter.MagazineViewHolder>() {

    private val allItems = mutableListOf<MagazineItem>()
    private val items = mutableListOf<MagazineItem>()

    init {
        // 더미데이터 생성
        allItems.addAll(listOf(
            MagazineItem(
                extra1="142",
                brand = "VOGUE",
                title = "새로운 텍스처, 남다른 포뮬러, 감촉 좋은 새로운 뷰티 트렌드",
                src = "https://img.vogue.co.kr/vogue/2025/08/style_68ac41783d1da-1120x1400.jpg"
            ),
            MagazineItem(
                extra1="143",
                brand = "W",
                title = "라이즈, 2026 롤라팔루자 남미 출격",
                src = "https://img.vogue.co.kr/vogue/2025/08/style_68b1591f6a858-933x1400.jpg"
            ),
            MagazineItem(
                extra1="144",
                brand = "ALLURE",
                title = "요리 못하는 사람도, 아주 간단하게 만들 수 있는 고단백 아침 식사",
                src = "https://img.gqkorea.co.kr/gq/2025/09/style_68b69564e82d9.jpg"
            ),
            MagazineItem(
                extra1="145",
                brand = "GQ",
                title = "150주년을 맞은 오데마 피게와 동시대 아이콘의 만남",
                src = "https://img.gqkorea.co.kr/gq/2025/08/style_68931b6973c35-1920x1080.jpg"
            ),
            MagazineItem(
                extra1="142",
                brand = "VOGUE",
                title = "세레나 윌리엄스가 직접 밝힌 GLP-1 복용 후기",
                src = "https://img.vogue.co.kr/vogue/2025/09/style_68b64e66b00df.jpg"
            )
        ))
        
        // 초기에는 모든 아이템 표시
        items.addAll(allItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazineViewHolder {
        return MagazineViewHolder(LayoutMagazineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MagazineViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun filterByExtra1(extra1: String?) {
        items.clear()
        if (extra1 == null) {
            // 전체 보기
            items.addAll(allItems)
        } else {
            // 특정 extra1 값으로 보여주기
            items.addAll(allItems.filter { it.extra1 == extra1 })
        }
        notifyDataSetChanged()//리스트 다시 그리기
    }

    inner class MagazineViewHolder(private val binding: LayoutMagazineItemBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: MagazineItem) {
            binding.apply {
                tvMagazineBrand.text = item.brand
                tvMagazineTitle.text = item.title
                
                // Glide를 사용해서 이미지 로드.. > 링크 문자열을 넣기만해선 표시가 안됨!
                if (item.src != null) {
                    Glide.with(ivMagazineThumbnail.context)
                        .load(item.src)
                        .into(ivMagazineThumbnail)
                }

                tvCashBadge.text = item.cashReward
            }
        }
    }

    companion object {
        const val POSITION_ALL = "all"
        const val POSITION_VOGUE = "vogue"
        const val POSITION_ALLURE = "allure"
        const val POSITION_W = "w"
        const val POSITION_GQ = "gq"
    }
}
