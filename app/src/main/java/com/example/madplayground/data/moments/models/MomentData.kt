package com.example.madplayground.data.moments.models

interface MomentData {
    val id: String
    val createdDateTime: String
    val description: String
    val date: String?
    val time: String?
}