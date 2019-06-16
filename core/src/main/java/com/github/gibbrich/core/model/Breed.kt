package com.github.gibbrich.core.model

import android.os.Parcel
import android.os.Parcelable

data class Breed(
    val name: String,
    val photoUrl: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(photoUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Breed> {
        override fun createFromParcel(parcel: Parcel): Breed = Breed(parcel)

        override fun newArray(size: Int): Array<Breed?> = arrayOfNulls(size)
    }
}