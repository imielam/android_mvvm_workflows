/*
 * Copyright (c) 2017.  All rights reserved - Maciej Imiela.
 */

package mobilehexers.eu.presentation.structure

import mobilehexers.eu.domain.test.TestClassDomain
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