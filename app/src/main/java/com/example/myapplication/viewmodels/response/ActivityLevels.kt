package com.example.myapplication.viewmodels.response

import com.squareup.moshi.Json

data class ActivityLevels(
    @field:Json(name = "levels")
    val levels: List<Level>
)

data class Level(
    @field:Json(name = "level")
    val level: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "description")
    val description: String,

    @field:Json(name = "state")
    val state: String,

    @field:Json(name = "activities")
    val activities: List<Activities>,
)

data class Activities(
    @field:Json(name = "id")
    val id: String,

    @field:Json(name = "challengeId")
    val challengeId: String,

    @field:Json(name = "type")
    val type: String,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "titleB")
    val titleB: String?,

    @field:Json(name = "description")
    val description: String,

    @field:Json(name = "descriptionB")
    val descriptionB: String?,

    @field:Json(name = "state")
    val state: String,

    @field:Json(name = "icon")
    val icon: Icon,

    @field:Json(name = "lockedIcon")
    val lockedIcon: Icon,
)

data class Icon(
    @field:Json(name = "file")
    val file: File,

    @field:Json(name = "title")
    val title: String,

    @field:Json(name = "description")
    val description: String,
)

data class File(
    @field:Json(name = "url")
    val url: String,

    @field:Json(name = "details")
    val details: Size,

    @field:Json(name = "fileName")
    val fileName: String,

    @field:Json(name = "contentType")
    val contentType: String,
)

data class Size(
    @field:Json(name = "type")
    val type: Int,
)


