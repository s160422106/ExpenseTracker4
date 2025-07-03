package com.example.expensetracker4.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val total: Double,  // Total anggaran baru
    val used: Double    // Total pengeluaran yang sudah dipakai
)


