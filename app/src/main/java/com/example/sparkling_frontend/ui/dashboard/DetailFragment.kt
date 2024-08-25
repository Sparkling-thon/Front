package com.example.sparkling_frontend.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.model.ReservationItem

class DetailFragment : Fragment() {

    companion object {
        private const val ARG_RESERVATION_ITEM = "reservation_item"

        fun newInstance(item: ReservationItem): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_RESERVATION_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }

    private var reservationItem: ReservationItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            reservationItem = it.getParcelable(ARG_RESERVATION_ITEM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_detail_fragment, container, false)

        // reservationItem 데이터를 이용해 UI 업데이트
        reservationItem?.let { item ->
            view.findViewById<TextView>(R.id.tv_detail_id).text = "ID: ${item.id}"
            view.findViewById<TextView>(R.id.tv_detail_member_name).text = "회원 이름: ${item.memberName}"
            view.findViewById<TextView>(R.id.tv_detail_collector_name).text = "수거자 이름: ${item.collectorName}"
            view.findViewById<TextView>(R.id.tv_detail_complete_date).text = "완료 날짜: ${item.completeDate}"
            view.findViewById<TextView>(R.id.tv_detail_powder_quantity).text = "가루약 수량: ${item.powderQuantity}개"
            view.findViewById<TextView>(R.id.tv_detail_general_quantity).text = "일반의약품 수량: ${item.generalQuantity}개"
            view.findViewById<TextView>(R.id.tv_detail_professional_quantity).text = "전문의약품 수량: ${item.professionalQuantity}개"
            view.findViewById<TextView>(R.id.tv_detail_liquid_quantity).text = "액체약 수량: ${item.liquidQuantity}개"
            view.findViewById<TextView>(R.id.tv_detail_ointment_quantity).text = "연고약 수량: ${item.ointmentQuantity}개"
            view.findViewById<TextView>(R.id.tv_detail_is_completed).text = "완료 여부: ${if (item.isCompleted) "완료됨" else "미완료"}"
            view.findViewById<TextView>(R.id.tv_detail_reservation_date).text = "예약 날짜: ${item.reservationDate}"
            view.findViewById<TextView>(R.id.tv_detail_address).text = "주소: ${item.address}"
        }

        return view
    }
}