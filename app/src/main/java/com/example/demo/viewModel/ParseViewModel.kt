package com.example.demo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.demo.model.ParentModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


class ParseViewModel(appContext: Application) : AndroidViewModel(appContext) {

    //General Model
    fun parseModel(jsonObject: JsonObject): ParentModel {
        val gson = Gson()
        val token: TypeToken<ParentModel> = object : TypeToken<ParentModel>() {}
        return gson.fromJson(jsonObject.toString(), token.type)
    }

}