package ma.emsi.ioc_demo

import ma.emsi.ioc_demo.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.jvm.Throws
import ma.emsi.ioc_demo.entities.Patient
import ma.emsi.ioc_demo.entities.Sex
import java.util.*
import java.util.function.Consumer
import kotlin.jvm.internal.Intrinsics


@SpringBootApplication
class IoCDemoApplication : CommandLineRunner {

    @Autowired
    lateinit var patientRepository: PatientRepository

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<IoCDemoApplication>(*args)
        }
    }

    @Throws(Exception::class)
    override fun run(args: Array<String>) {

        patientRepository.save(Patient(null, "AB123456", "MERZAK", "Mohamed", "Casablanca", Date(), Sex.MALE))
        patientRepository.save(Patient(null, "AB123456", "AGOURRAM", "Yassine", "Casablanca", Date(), Sex.MALE))

        patientRepository.findAll().forEach(::println)

        //patientRepository.findByLastnameContainsOrFirstnameContains("ZAK", "ine").forEach(Consumer { x: Patient? -> println(x) })
    }
}