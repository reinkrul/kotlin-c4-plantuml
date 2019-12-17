package nl.reinkrul.tools.c4plantuml

import org.junit.Test
import java.nio.file.Paths
import java.util.UUID

class RenderTest {

    @Test
    fun `render to non-existing directory`() {
        val directory = Paths.get("/tmp/${UUID.randomUUID()}")
        workspace("PetClinic") {
            system("PetStore")
        }.render(directory = directory)
        directory.toFile().deleteRecursively()
    }
}