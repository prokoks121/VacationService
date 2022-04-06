package com.example.data_import_service.repository

import com.example.data_import_service.model.UsedVacation
import org.springframework.data.jpa.repository.JpaRepository

interface UsedVacationRepository : JpaRepository<UsedVacation,Long> {
}