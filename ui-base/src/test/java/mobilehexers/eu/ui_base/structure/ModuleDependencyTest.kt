package mobilehexers.eu.ui_base.structure

import mobilehexers.eu.presentation.structure.TestClassPresentation
import mobilehexers.eu.ui_base.test.TestClassUI
import org.junit.Test

class ModuleDependencyTest {
    @Test
    fun testDependency_internal() {
        val testClassUI = TestClassUI()
    }

    @Test
    fun testDependency_presentation() {
        val testClassPresentation = TestClassPresentation()
    }
}