package com.example.calc.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculations")
data class Calculation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val expression: String,
    val result: Double
)

