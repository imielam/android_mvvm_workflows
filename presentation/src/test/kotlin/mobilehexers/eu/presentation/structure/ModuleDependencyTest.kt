package mobilehexers.eu.presentation.structure

import mobilehexers.eu.domain.structure.TestClassDomain
import org.junit.Test

class ModuleDependencyTest {
    @Test
    fun testDependency_internal() {
        val testClassPresentation = TestClassPresentation()
    }

    @Test
    fun testDependency_domain() {
        val testClassPresentation = TestClassDomain()
    }
}