package com.example.roomdatabase.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class My_Contact {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name: String? = null
    var surname: String? = null

    constructor(id: Int?, name: String?, surname: String?) {
        this.id = id
        this.name = name
        this.surname = surname
    }

    constructor(name: String?, surname: String?) {
        this.name = name
        this.surname = surname
    }

    constructor()

    override fun toString(): String {
        return "My_Contact(id=$id, name=$name, surname=$surname)"
    }
}