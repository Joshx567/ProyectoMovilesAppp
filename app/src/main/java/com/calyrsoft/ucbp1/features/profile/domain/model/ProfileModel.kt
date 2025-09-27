package com.calyrsoft.ucbp1.features.profile.domain.model

import com.calyrsoft.ucbp1.features.profile.domain.model.value.*

data class ProfileModel(
    val pathUrl: String,
    val name: String,
    val email: String,
    val cellphone: String,
    val summary: String
)
