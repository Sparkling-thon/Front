package com.example.sparkling_frontend.model

import android.os.Parcel
import android.os.Parcelable

data class ReservationItem(
    val id: Int,
    val memberName: String,
    val collectorName: String,
    val completeDate: String,
    val powderQuantity: Int,
    val generalQuantity: Int,
    val professionalQuantity: Int,
    val liquidQuantity: Int,
    val ointmentQuantity: Int,
    val isCompleted: Boolean,
    val reservationDate: String,
    val address: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(memberName)
        parcel.writeString(collectorName)
        parcel.writeString(completeDate)
        parcel.writeInt(powderQuantity)
        parcel.writeInt(generalQuantity)
        parcel.writeInt(professionalQuantity)
        parcel.writeInt(liquidQuantity)
        parcel.writeInt(ointmentQuantity)
        parcel.writeByte(if (isCompleted) 1 else 0)
        parcel.writeString(reservationDate)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReservationItem> {
        override fun createFromParcel(parcel: Parcel): ReservationItem {
            return ReservationItem(parcel)
        }

        override fun newArray(size: Int): Array<ReservationItem?> {
            return arrayOfNulls(size)
        }
    }
}