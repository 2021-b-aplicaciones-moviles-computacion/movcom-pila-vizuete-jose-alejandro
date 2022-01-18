package com.example.proyecto_1b

import android.os.Parcel
import android.os.Parcelable

class Libro(
    val idBiblioteca: Int,
    val nombreLibro: String?,
    val autorLibro: String?,
    val numEdicion: Int,
    val disponibilidad: Boolean,
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
//        TODO("n"),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idBiblioteca)
        parcel.writeString(nombreLibro)
        parcel.writeString(autorLibro)
        parcel.writeInt(numEdicion)
        parcel.writeByte(if (disponibilidad) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
    override fun toString(): String {
        val disponible: String
        if(disponibilidad){
            disponible = "si"
        }else{
            disponible = "no"
        }
        return "Nombre: ${nombreLibro}\nAutor: ${autorLibro}\nEdicion: ${numEdicion}\nDisponible:${disponible}\n"
    }
}