/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.demo.structure

import mobilehexers.eu.demo.test.TestClassApp
import mobilehexers.eu.ui_base.TestClassData
import mobilehexers.eu.uibase.test.TestClassUI
import org.junit.Test

class ModuleDependencyTest {
    @Test
    fun testDependency_internal() {
        val testClassApp = TestClassApp()
    }

    @Test
    fun testDependency_baseUI() {
        val testClassUI = TestClassUI()
    }

    @Test
    fun testDependency_data() {
        val testClassData = TestClassData()
    }
}
