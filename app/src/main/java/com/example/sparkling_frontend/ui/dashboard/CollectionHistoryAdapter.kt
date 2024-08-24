package com.example.sparkling_frontend.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.model.ReservationItem

class CollectionHistoryAdapter(
    private val items: List<ReservationItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CollectionHistoryAdapter.CollectionHistoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: ReservationItem)
    }

    class CollectionHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDate: TextView = view.findViewById(R.id.tv_date)
        val tvStatus: TextView = view.findViewById(R.id.tv_status)
        val tvTotalItems: TextView = view.findViewById(R.id.tv_total_items)
        val tvDetail: TextView = view.findViewById(R.id.tv_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.collect_history, parent, false)
        return CollectionHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionHistoryViewHolder, position: Int) {
        val item = items[position]
        holder.tvDate.text = item.completeDate
        holder.tvStatus.text = if (item.isCompleted) "수거 완료" else "수거 중"
        holder.tvTotalItems.text = "총 ${item.powderQuantity + item.generalQuantity + item.professionalQuantity + item.liquidQuantity + item.ointmentQuantity}개"
        holder.tvDetail.text = "가루약 ${item.powderQuantity}개\n알약 ${item.professionalQuantity}개\n물약 ${item.liquidQuantity}개"

        // 아이템 클릭 리스너 설정
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}