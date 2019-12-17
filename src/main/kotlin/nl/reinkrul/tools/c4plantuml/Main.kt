package nl.reinkrul.tools.c4plantuml

import com.structurizr.Workspace
import com.structurizr.io.plantuml.PlantUMLWriter
import com.structurizr.view.View
import net.sourceforge.plantuml.SourceStringReader
import java.io.FileOutputStream
import java.io.OutputStream
import java.io.StringWriter
import java.nio.file.FileSystems
import java.nio.file.Path

fun Workspace.render(theme: Theme = CustomTheme, directory: Path = FileSystems.getDefault().getPath(".")) {
    theme.apply(views.configuration)

    val ownSoftwareSystem = model.softwareSystems.firstOrNull { !it.tags.contains(AdditionalTags.exteralSystem) }
            ?: error("No system defined (other than external systems).")
    val contextView = views.createSystemContextView(ownSoftwareSystem, "SystemContext", null)
    contextView.addAllElements()
    val containerView = views.createContainerView(ownSoftwareSystem, "Container", null)
    containerView.addAllElements()

    if (!directory.toFile().isDirectory) {
        directory.toFile().mkdirs()
    }
    FileOutputStream(directory.resolve("system-context-diagram.png").toFile()).use {
        generateDiagram(contextView, it, theme)
    }
    FileOutputStream(directory.resolve("container-diagram.png").toFile()).use {
        generateDiagram(containerView, it, theme)
    }
}

private fun generateDiagram(view: View, outputStream: OutputStream, theme: Theme) {
    val stringWriter = StringWriter()
    with(PlantUMLWriter()) {
        theme.apply(this)
        write(view, stringWriter)
    }
    SourceStringReader(stringWriter.toString()).outputImage(outputStream)
}
