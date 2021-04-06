package ma.emsi.ioc_demo.controllers

import ma.emsi.ioc_demo.entities.Patient
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import ma.emsi.ioc_demo.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired


@RestController
class PatientsController {

    @Autowired
    private lateinit var patientRepository: PatientRepository

    @GetMapping("/patients")
    fun patients(): List<Patient> {
        return patientRepository.findAll()
    }

    @GetMapping("/patients/{id}")
    fun patient(@PathVariable id: Long): Patient {
        return patientRepository.findById(id).get()
    }
}