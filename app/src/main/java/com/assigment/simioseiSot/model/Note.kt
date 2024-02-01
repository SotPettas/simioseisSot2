package com.assigment.simioseiSot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var noteTitle : String,
    var noteText: String
        ) : Serializable