package ma.emsi.ioc_demo.controllers

import ma.emsi.ioc_demo.entities.Patient
import ma.emsi.ioc_demo.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


//import javax.validation.Valid


@RestController
class PatientsController {

    @Autowired
    private lateinit var patientRepository: PatientRepository

    /*@GetMapping("/patients")
    fun patients(): List<Patient> {
        return patientRepository.findAll()
    }*/

    @GetMapping("/patient/{id}")
    fun patient(@PathVariable id: Long): Patient {
        return patientRepository.findById(id).get()
    }

    @GetMapping("/patients/page/")
    fun patientsPages () : String {
        return "patients"
    }

    @GetMapping("/patients")
    fun list(
        model: Model,
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "5") size: Int,
        @RequestParam(name = "keyword", defaultValue = "") keyword: String
    ): String {
        val pagePatients: Page<Patient> = patientRepository.findByLastnameContains(keyword, PageRequest.of(page, size))
        model.addAttribute("list", pagePatients.content)
        model.addAttribute("pages", IntArray(pagePatients.totalPages))
        model.addAttribute("currentPage", page)
        model.addAttribute("size", size)
        model.addAttribute("keyword", keyword)
        return "patients"
    }

    @GetMapping("/deletePatient")
    fun deletePatient(id: Long, page: Int, keyword: String, size: Int): String {
        patientRepository.deleteById(id)
        return "redirect:/patients?page=$page&keyword=$keyword&size=$size"
    }

    @GetMapping(path = ["/deletePatient2"])
    fun deletePatient2(id: Long, page: Int, keyword: String, size: Int, model: Model): String {
        patientRepository.deleteById(id)
        return list(model, page, size, keyword)
    }

    @GetMapping("/formPatient")
    fun formPatient(model: Model): String {
        model.addAttribute("patient", Patient())
        model.addAttribute("mode", "new")
        return "formPatient"
    }

    @GetMapping(path = ["/editPatient"])
    fun editPatient(model: Model, id: Long): String {
        val patient = patientRepository.findById(id).get()
        model.addAttribute("patient", patient)
        model.addAttribute("mode", "edit")
        return "formPatient"
    }

    @PostMapping(path = ["/savePatient"])
    fun savePatient(model: Model, patient: Patient, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "formPatient"
        patientRepository.save(patient)
        model.addAttribute("patient", patient)
        return "confirmation"
    }

}