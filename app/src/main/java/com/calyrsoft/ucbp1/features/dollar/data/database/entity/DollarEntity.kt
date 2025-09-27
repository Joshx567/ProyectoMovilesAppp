package com.calyrsoft.ucbp1.features.dollar.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dollars")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "dollar_official_buy")
    var dollarOfficialBuy: String = "",

    @ColumnInfo(name = "dollar_official_sell")
    var dollarOfficialSell: String = "",

    @ColumnInfo(name = "dollar_parallel_buy")
    var dollarParallelBuy: String = "",

    @ColumnInfo(name = "dollar_parallel_sell")
    var dollarParallelSell: String = "",

    @ColumnInfo(name = "last_update")
    var lastUpdate: String = ""
)
