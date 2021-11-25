package com.mestabn.aad_playground.commons.serializer

import com.google.gson.Gson

class GsonSerializer constructor(private val gson: Gson) : JsonSerializer {

    override fun <T> toJson(obj: T, typeClass: Class<T>): String = gson.toJson(obj, typeClass)

    override fun <T> fromJson(json: String, typeClass: Class<T>): T = gson.fromJson(json, typeClass)
}