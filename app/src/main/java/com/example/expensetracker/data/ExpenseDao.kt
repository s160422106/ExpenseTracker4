package com.example.expensetracker4.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query


@Dao
interface ExpenseDao {

    @Query("SELECT IFNULL(SUM(amount), 0) FROM Expense WHERE budgetId = :budgetId")
    fun getTotalExpenseForBudget(budgetId: Int): LiveData<Double>

    // Asumsikan fungsi insert dll sudah ada
}
