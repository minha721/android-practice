package com.ssafy.googlemap

import com.google.android.gms.maps.model.LatLng

data class Marker(
    val pos: LatLng,
    val title: String
)
