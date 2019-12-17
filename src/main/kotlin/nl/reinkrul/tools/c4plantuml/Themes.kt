package nl.reinkrul.tools.c4plantuml

import com.structurizr.io.plantuml.PlantUMLWriter
import com.structurizr.model.Tags
import com.structurizr.view.Configuration
import com.structurizr.view.Shape


interface Theme {
    fun apply(configuration: Configuration)
    fun apply(writer: PlantUMLWriter)
}

object AdditionalTags {
    const val exteralSystem = "ExternalSystem"
    const val database = "Database"
}

class StructurizrTheme : Theme {
    override fun apply(writer: PlantUMLWriter) {
        with(writer) {
            addSkinParam("rectangleFontColor", "#ffffff")
            addSkinParam("rectangleStereotypeFontColor", "#ffffff")
        }
    }

    override fun apply(configuration: Configuration) {
        with(configuration.styles) {
            addElementStyle(Tags.SOFTWARE_SYSTEM)
                    .background("#1168bd")
                    .color("#ffffff")
            addElementStyle(Tags.CONTAINER)
                    .background("#1168bd")
                    .color("#ffffff")
            addElementStyle(Tags.PERSON)
                    .background("#08427b")
                    .color("#ffffff")
                    .shape(Shape.Person)
        }
    }
}

object CustomTheme : Theme {
    private const val blue = "#5797ac"
    private const val purple = "#9076b9"
    private const val gray = "#3d4856"

    override fun apply(writer: PlantUMLWriter) {
        with(writer) {
            addSkinParam("rectangleFontColor", "#ffffff")
            addSkinParam("rectangleStereotypeFontColor", "#ffffff")
        }
    }

    override fun apply(configuration: Configuration) {
        with(configuration.styles) {
            addElementStyle(Tags.SOFTWARE_SYSTEM)
                    .background(blue)
                    .color(blue)
                    .border(null)
            addElementStyle(Tags.CONTAINER)
                    .background(blue)
                    .color("#ffffff")
                    .border(null)
            addElementStyle(Tags.PERSON)
                    .background("#08427b")
                    .color("#ffffff")
                    .shape(Shape.Person)
            addElementStyle(AdditionalTags.exteralSystem)
                    .background(purple)
                    .color(purple)
                    .border(null)
            addElementStyle(AdditionalTags.database)
                    .background(blue)
                    .color("#ffffff")
                    .shape(Shape.Cylinder)
                    .border(null)
        }
    }
}