package ma.emsi.ioc_demo.entities

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.Date
import javax.persistence.*

@Entity @Table(name = "Patients") @NoArgsConstructor @AllArgsConstructor
data class Patient(@Id @Column(name = "PatientId")
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   var id : Long? = null,
                   var idCard : String? = null,
                   var lastname : String? = null,
                   var firstname : String? = null,
                   var address : String? = null,
                   @Temporal(TemporalType.DATE)
                   var dateOfBirth : Date? = null,
                   var sex : Sex? = null
                   ) {
}