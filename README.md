# Getting Started
## Prerequisites
Install Graphviz using Homebrew:
<pre>
brew install graphviz
</pre>

Why do I need this? This library uses PlantUML, which uses *Graphviz* a Python library which in turn uses *dot*, a native library. 

## Generating diagrams
Create a unit test (or something else that runs during your build), build the C4 model of your application using the Kotlin DSL and simply render it.
See src/test/kotlin/nl/reinkrul/tools/c4plantuml/Example.kt for an example. 

