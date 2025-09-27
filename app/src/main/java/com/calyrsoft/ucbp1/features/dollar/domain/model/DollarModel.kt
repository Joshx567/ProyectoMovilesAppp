package com.calyrsoft.ucbp1.features.dollar.domain.model

data class DollarModel(
    var dollarOfficialBuy: String? = null,
    var dollarOfficialSell: String? = null,
    var dollarParallelBuy: String? = null,
    var dollarParallelSell: String? = null,
    var lastUpdate: String? = null // fecha de actualización
)
