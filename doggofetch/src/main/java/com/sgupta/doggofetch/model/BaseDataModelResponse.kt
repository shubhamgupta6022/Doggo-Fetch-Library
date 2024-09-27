package com.sgupta.doggofetch.model

import com.google.gson.annotations.SerializedName

internal open class BaseDataModelResponse<DataType> {
    @SerializedName("status") var status: String = ""
    @SerializedName("message") var data: DataType? = null
}