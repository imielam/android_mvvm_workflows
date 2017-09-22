package mobilehexers.eu.driversweek.structure

import mobilehexers.eu.driversweek.test.TestClassApp
import mobilehexers.eu.ui_base.TestClassData
import mobilehexers.eu.ui_base.test.TestClassUI
import org.junit.Test

class ModuleDependencyTest {
    @Test
    fun testDependency_baseUI() {
        val testClassApp = TestClassApp()
        val testClassUI = TestClassUI()
    }

    @Test
    fun testDependency_data() {
        val testClassApp = TestClassApp()
        val testClassData = TestClassData()
    }
}
