package com.example.simplegetrequest

import com.google.gson.annotations.SerializedName

class Names {
    var name: ArrayList<Name>? = null

    class Name {
        @SerializedName("name")
        var name: String? = null
        constructor( name: String?) {
            this.name = name
        }
    }
}