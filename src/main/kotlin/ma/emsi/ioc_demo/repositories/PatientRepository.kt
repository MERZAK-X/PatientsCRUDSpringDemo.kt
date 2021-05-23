package ma.emsi.ioc_demo.repositories

import ma.emsi.ioc_demo.entities.Patient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository : JpaRepository <Patient, Long>{
    fun findByLastnameContains(lastname : String, pageable : Pageable) : Page <Patient>
    fun findByLastnameContainsOrFirstnameContains(lastname : String, firstname : String) : List <Patient>
}