package com.batista.foodrescue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "produtos_table")
data class Produto(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("assignetTo")
    val assignetTo: String,
    @SerializedName("dueDate")
    val dueDate: String,
    @SerializedName("status")
    val status: String
) {
}