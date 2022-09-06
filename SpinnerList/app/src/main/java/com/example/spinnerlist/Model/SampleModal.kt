package com.example.spinnerlist.Model



data class SampleModal(
    val status: String? = null,
    val message: String? = null,
    val errorCode: String? = null,
    val filterData: List<FilterData?>? = null
)