package com.epi.wallee;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.epi.wallee");

        noClasses()
            .that()
            .resideInAnyPackage("com.epi.wallee.service..")
            .or()
            .resideInAnyPackage("com.epi.wallee.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.epi.wallee.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
