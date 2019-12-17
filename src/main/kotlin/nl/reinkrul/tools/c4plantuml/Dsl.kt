package nl.reinkrul.tools.c4plantuml

import com.structurizr.Workspace
import com.structurizr.model.Component
import com.structurizr.model.Container
import com.structurizr.model.Model
import com.structurizr.model.Person
import com.structurizr.model.SoftwareSystem
import com.structurizr.model.StaticStructureElement


fun workspace(name: String, description: String? = null, acceptor: Model.() -> Unit = {}): Workspace =
        Workspace(name, description)
                .also { acceptor(it.model) }

fun Model.externalSystem(name: String, description: String? = null, acceptor: SoftwareSystem.() -> Unit = {}): SoftwareSystem =
        system(name, description)
                .also { it.addTags(AdditionalTags.exteralSystem) }
                .also(acceptor)

fun Model.person(name: String, description: String? = null, acceptor: Person.() -> Unit = {}): Person =
        addPerson(name, description)
                .also(acceptor)

fun Model.system(name: String, description: String? = null, acceptor: SoftwareSystem.() -> Unit = {}): SoftwareSystem =
        addSoftwareSystem(name, description).also(acceptor)


fun SoftwareSystem.database(name: String, description: String? = null, technology: String? = null, acceptor: Container.() -> Unit = {}): Container =
        addContainer(name, description, technology)
                .also { it.addTags(AdditionalTags.database) }
                .also(acceptor)

fun SoftwareSystem.container(name: String, description: String? = null, technology: String? = null, acceptor: Container.() -> Unit = {}): Container =
        addContainer(name, description, technology).also(acceptor)

fun Container.uses(container: Container, description: String? = null, technology: String? = null) {
    uses(container, description, technology)
}

fun StaticStructureElement.uses(destination: String, description: String? = null, technology: String? = null) {
    model.elements
            .firstOrNull { it.name == destination }
            .also {
                when (it) {
                    is Container -> uses(it, description, technology)
                    is SoftwareSystem -> uses(it, description, technology)
                    is Component -> uses(it, description, technology)
                    else -> error("Unknown destination type ($it) for 'uses': $description")
                }
            }
            ?: error("Unknown destination for 'uses': $destination")
}

