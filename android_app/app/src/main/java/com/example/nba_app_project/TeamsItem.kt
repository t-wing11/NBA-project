package com.example.nba_app_project

import android.os.Parcelable

data class TeamsItem(
    val full_name: String,
    val id: Int,
    val losses: Int,
    val players: List<Player> ,
    val wins: Int
)

data class Player(
    val first_name: String,
    val id: Int,
    val last_name: String,
    val number: Int,
    val position: String
): Parcelable {
    constructor(parcel: android.os.Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(first_name)
        parcel.writeInt(id)
        parcel.writeString(last_name)
        parcel.writeInt(number)
        parcel.writeString(position)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: android.os.Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
