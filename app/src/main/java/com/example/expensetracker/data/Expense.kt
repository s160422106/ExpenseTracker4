package com.example.expensetracker4.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val budgetId: Int,
    val amount: Double
)
