/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.uibase.structure

import mobilehexers.eu.presentation.structure.TestClassPresentation
import mobilehexers.eu.uibase.test.TestClassUI
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