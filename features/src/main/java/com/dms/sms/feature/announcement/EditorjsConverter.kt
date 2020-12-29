package com.dms.sms.feature.announcement

import com.dms.data.util.EJResponse
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import work.upstarts.editorjskit.models.EJBlock
import work.upstarts.gsonparser.EJDeserializer

fun String.convertToEditorjs() : List<EJBlock>{
    val ejResponse = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<MutableList<EJBlock>>() {}.type
            , EJDeserializer()
        )
        .create().fromJson(this.replace("\\", "")
            , EJResponse::class.java)

    return ejResponse.blocks

}