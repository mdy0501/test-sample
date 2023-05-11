package com.mdy.sample.unit

import org.json.JSONObject

data class JsonTestDto(
    val json: String?
) {
    fun getPgOrderId(): String? {
        return try {
            JSONObject(json)
                .get("id") as String?
        } catch (e: Exception) {
            null
        }
    }

    fun getPgCaptureId(): String? {
        return try {
            JSONObject(json)
                .getJSONArray("purchase_units")
                .getJSONObject(0)
                .getJSONObject("payments")
                .getJSONArray("captures")
                .getJSONObject(0)
                .getString("id")
        } catch (e: Exception) {
            null
        }
    }
}