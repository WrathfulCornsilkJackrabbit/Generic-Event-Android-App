package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models

import java.time.LocalDate


data class ClientModel(
        val name: String,
        val eventName: String,
        val eventDays: ArrayList<LocalDate>
)
