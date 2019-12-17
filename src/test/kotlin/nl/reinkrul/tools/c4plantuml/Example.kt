package nl.reinkrul.tools.c4plantuml

import org.junit.Test

class Example {

    @Test
    fun test() {
        workspace("Tikfacturatie", "Tikfacturatie Architectuur") {
            val tikfacturatie = system("Tikfacturatie") {
                val database = database("Database", technology = "PostgreSQL")
                container("Tikfacturatie Service", technology = "Spring Boot 2") {
                    uses(database)
                }
            }
            externalSystem("PEC-dienst") {
                uses(tikfacturatie, "Registreert tikken")
                uses("Tikfacturatie Service", "Registreert tikken", "JMS")
            }
            externalSystem("KIP", "Factuurverwerker") {
                uses(tikfacturatie, "Verwerkt tikfacturatie")
                uses("Tikfacturatie Service", "Verwerkt tikfacturatie", "HTTP")
            }
        }.render()
    }

    @Test(expected = IllegalStateException::class)
    fun `workspace with no systems`() {
        workspace("No systems") {}.render()
    }
}
