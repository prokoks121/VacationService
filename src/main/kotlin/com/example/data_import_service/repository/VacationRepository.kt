package com.example.data_import_service.repository

import com.example.data_import_service.model.Vacation
import org.springframework.data.jpa.repository.JpaRepository

interface VacationRepository:JpaRepository<Vacation,Long> {
}