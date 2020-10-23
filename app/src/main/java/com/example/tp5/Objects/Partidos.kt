package com.example.tp5.Objects

import android.os.Parcel
import android.os.Parcelable

class Partidos(horario: String?, cantidad_jugadores: String?, costo: Double?, descripcion: String?, latitud: Double?, longitud: Double?, tipo_de_partido: String?, imagen_partido: String?) :
    Parcelable {
    var horario: String = ""

    var cantidad_jugadores: String = ""

    var costo: Double = 0.0

    var descripcion: String = ""

    var tipo_de_partido: String = ""

    var latitud: Double = 0.0

    var longitud: Double = 0.0

    var imagen_partido: String = ""

    constructor() : this("", "", 0.0, "", 0.0, 0.0, "", "")

    init {
        this.horario = horario!!
        this.cantidad_jugadores = cantidad_jugadores!!
        this.costo = costo!!
        this.descripcion = descripcion!!
        this.tipo_de_partido = tipo_de_partido!!
        this.latitud = latitud!!
        this.longitud = longitud!!
        this.imagen_partido = imagen_partido!!

    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(horario)
        writeString(cantidad_jugadores)
        writeValue(costo)
        writeString(descripcion)
        writeValue(latitud)
        writeValue(longitud)
        writeString(tipo_de_partido)
        writeString(imagen_partido)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Partidos> = object : Parcelable.Creator<Partidos> {
            override fun createFromParcel(source: Parcel): Partidos = Partidos(source)
            override fun newArray(size: Int): Array<Partidos?> = arrayOfNulls(size)
        }
    }
}