package com.example.data_import_service.controller

import com.example.data_import_service.services.EmployeeService
import com.example.data_import_service.services.UsedVacationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class AdminCsvController @Autowired constructor(
    val employeeService: EmployeeService,
    val usedVacationService: UsedVacationService,
    val
) {

    @PostMapping("/upload/employee")
    fun uploadEmployee(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        var message = ""
        if (employeeService.hasCSVFormat(file)) {
            return try {
                employeeService.save(file)
                message = "Uploaded the file successfully: " + file.originalFilename
                ResponseEntity.status(HttpStatus.OK).body<String>(message)
            } catch (e: Exception) {
                message = "Could not upload the file: " + file.originalFilename + "!"
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<String>(message)
            }
        }
        message = "Please upload a csv file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<String>(message)
    }

    @PostMapping("/upload/used-vacation")
    fun uploadUsedVacations(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        var message = ""
        if (usedVacationService.hasCSVFormat(file)) {
            return try {
                usedVacationService.save(file)
                message = "Uploaded the file successfully: " + file.originalFilename
                ResponseEntity.status(HttpStatus.OK).body<String>(message)
            } catch (e: Exception) {
                message = "Could not upload the file: " + file.originalFilename + "!"
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<String>(message)
            }
        }
        message = "Please upload a csv file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<String>(message)
    }

    @PostMapping("/upload/vacation")
    fun uploadVacations(@RequestParam("file") file: MultipartFile): ResponseEntity<String> {
        var message = ""
        if (usedVacationService.hasCSVFormat(file)) {
            return try {
                usedVacationService.save(file)
                message = "Uploaded the file successfully: " + file.originalFilename
                ResponseEntity.status(HttpStatus.OK).body<String>(message)
            } catch (e: Exception) {
                message = "Could not upload the file: " + file.originalFilename + "!"
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body<String>(message)
            }
        }
        message = "Please upload a csv file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<String>(message)
    }

}