package com.example.data_import_service.repository

import com.example.data_import_service.model.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository:JpaRepository<Employee,Long>{
    fun existsByEmail(email:String):Boolean
}

