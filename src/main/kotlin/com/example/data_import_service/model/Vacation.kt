package com.example.data_import_service.model

import javax.persistence.*

@Entity
data class Vacation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long = 0,
    val totalDays:Int,
    val employeeEmail:String,
    val year:Int
)
