package com.calyrsoft.ucbp1.features.dollar.data.mapper

import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel(): DollarModel {
    return DollarModel(
        dollarOfficialBuy = dollarOfficialBuy,
        dollarOfficialSell = dollarOfficialSell,
        dollarParallelBuy = dollarParallelBuy,
        dollarParallelSell = dollarParallelSell,
        lastUpdate = lastUpdate
    )
}

// Model -> Entity
fun DollarModel.toEntity(): DollarEntity {
    return DollarEntity(
        dollarOfficialBuy = dollarOfficialBuy ?: "",
        dollarOfficialSell = dollarOfficialSell ?: "",
        dollarParallelBuy = dollarParallelBuy ?: "",
        dollarParallelSell = dollarParallelSell ?: "",
        lastUpdate = lastUpdate ?: ""
    )
}