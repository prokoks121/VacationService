package com.example.data_import_service.services

import com.example.data_import_service.model.Employee
import com.example.data_import_service.repository.EmployeeRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader



@Service
class EmployeeService @Autowired constructor(
    val employeeRepository: EmployeeRepository
) {

    var TYPE = "text/csv"
    var HEADERS = arrayOf("Employee Email", "Employee Password")


    fun hasCSVFormat(file: MultipartFile): Boolean {
        return TYPE == file.contentType
    }

    fun csvToTutorials(inputStream: InputStream): List<Employee> {
        try {
            BufferedReader(InputStreamReader(inputStream, "UTF-8")).use { fileReader ->
                val firstLine = fileReader.readLine()

                CSVParser(
                    fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
                ).use { csvParser ->
                    val employee: MutableList<Employee> = ArrayList<Employee>()
                    val csvRecords: Iterable<CSVRecord> = csvParser.records
                    for (csvRecord in csvRecords) {
                        val tutorial = Employee(
                            email = csvRecord["Employee Email"],
                            password = csvRecord["Employee Password"]
                        )
                        employee.add(tutorial)
                    }
                    return employee
                }
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to parse CSV file: " + e.message)
        }
    }

    fun save(file: MultipartFile) {
        try {
            val employee: List<Employee> = csvToTutorials(file.inputStream)
            employeeRepository.saveAll(employee)
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

}