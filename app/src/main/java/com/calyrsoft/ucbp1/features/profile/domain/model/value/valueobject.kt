package com.calyrsoft.ucbp1.features.profile.domain.model.value

@JvmInline
value class ProfileEmail(val value: String) {
    init {
        require(value.contains("@")) { "Email inválido" } // Validación al crear
    }
}

@JvmInline
value class ProfileName(val value: String) {
    init {
        require(value.isNotBlank()) { "El nombre no puede estar vacío" }
    }
}

@JvmInline
value class ProfileImage(val url: String) {
    init {
        require(url.isNotBlank()) { "La URL de la imagen no puede estar vacía" }
    }
}
