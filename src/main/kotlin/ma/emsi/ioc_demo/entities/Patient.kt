package ma.emsi.ioc_demo.entities

import lombok.NoArgsConstructor
import java.util.Date
import javax.persistence.*

@Entity @Table(name = "Patients")
data class Patient(@Id @Column(name = "PatientId")
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   var id : Long?,
                   var idCard : String?,
                   var lastname : String?,
                   var firstname : String?,
                   var address : String?,
                   @Temporal(TemporalType.DATE)
                   var dateOfBirth : Date?,
                   var sex : Sex?
                   ) {
}