package com.example.proyecto_1b

import android.os.Parcel
import android.os.Parcelable

class Biblioteca(
    var nombre: String?,
    var direccion: String?,
    var numeroTel: Int,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(direccion)
        parcel.writeInt(numeroTel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Biblioteca> {
        override fun createFromParcel(parcel: Parcel): Biblioteca {
            return Biblioteca(parcel)
        }

        override fun newArray(size: Int): Array<Biblioteca?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String = "${nombre} \n ${direccion}\n ${numeroTel}"
}