package com.example.calc.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CalculationDao {
    @Insert
    suspend fun insert(calculation: Calculation)

    @Query("SELECT * FROM calculations")
    fun getAllCalculations(): List<Calculation>
}

