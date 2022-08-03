package com.example.data.moments.models

interface MomentData {
    val id: String
    val createdDateTime: String
    val description: String
    val date: String?
    val time: String?
}