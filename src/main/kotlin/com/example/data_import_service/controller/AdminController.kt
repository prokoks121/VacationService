package com.example.data_import_service.controller

import com.example.data_import_service.model.Employee
import com.example.data_import_service.model.UsedVacation
import com.example.data_import_service.model.Vacation
import com.example.data_import_service.repository.EmployeeRepository
import com.example.data_import_service.repository.UsedVacationRepository
import com.example.data_import_service.repository.VacationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController @Autowired constructor(
    val employeeRepository: EmployeeRepository,
    val vacationRepository: VacationRepository,
    val usedVacationRepository: UsedVacationRepository
) {

    @PostMapping("/add/employee")
    fun addEmployee(
        @RequestBody employee: Employee
    ):ResponseEntity<Employee>{
        if (!employeeRepository.existsByEmail(employee.email)){
            employeeRepository.save(employee)
        }else{
             throw java.lang.RuntimeException("Email already exist")
        }
        return ResponseEntity(employee,HttpStatus.CREATED)
    }


    @PostMapping("/add/vacation")
    fun addVacation(
        @RequestBody vacation:Vacation
    ):Vacation{
        if (employeeRepository.existsByEmail(vacation.employeeEmail))
            vacationRepository.save(vacation)
        else
            throw java.lang.RuntimeException("Korisnik ne postoji")
        return vacation
    }

    @PostMapping("/add/used-vacation")
    fun addUsedVacation(
        @RequestBody usedVacation: UsedVacation
    ): UsedVacation {
        if (employeeRepository.existsByEmail(usedVacation.employeeEmail))
        usedVacationRepository.save(usedVacation)
        else
            throw java.lang.RuntimeException("Korisnik ne postoji")
    return usedVacation
    }
}