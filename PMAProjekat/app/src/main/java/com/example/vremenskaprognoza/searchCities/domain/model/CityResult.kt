package com.example.vremenskaprognoza.searchCities.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/*
*   Model for CityGeo
**/
@Serializable
data class CityResult (
    @SerializedName("id"           ) var id          : Long?             = null,
    @SerializedName("name"         ) var name        : String?           = null,
    @SerializedName("latitude"     ) var latitude    : Double?           = null,
    @SerializedName("longitude"    ) var longitude   : Double?           = null,
    @SerializedName("country_code" ) var countryCode : String?           = null,
    @SerializedName("population"   ) var population  : Long?             = null,
    @SerializedName("country"      ) var country     : String?           = null,
    @SerializedName("admin1"       ) var admin       : String?           = null,
)