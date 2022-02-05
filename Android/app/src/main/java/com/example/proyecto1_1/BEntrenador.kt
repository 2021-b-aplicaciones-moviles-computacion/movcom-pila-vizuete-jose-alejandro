package com.example.proyecto1_1

import android.os.Parcel
import android.os.Parcelable
/*Clase 11 17-12-2021
* Creacion de clase para envio de clases por el Intent
* Generacion de Parcelable para convertirlo en objeto y poder tomar
* los valores del arreglo*/
class BEntrenador(
    val nombre: String?,
    val descripcion: String?,
): Parcelable {
    /*Lee los valores del objeto */
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "${nombre} | ${descripcion}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }
}