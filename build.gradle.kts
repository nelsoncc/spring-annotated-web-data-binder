plugins {
    java
    jacoco
    `jacoco-report-aggregation`
    alias(libs.plugins.sonarqube)
}

allprojects {
    apply(plugin = "com.mattbertolini.buildlogic.project-conventions")

    group = "com.mattbertolini"
    version = "0.6.0"
}

val rootJacocoDir = "${rootProject.buildDir}/reports/jacoco/testCodeCoverageReport"
val reportXmlFile = "$rootJacocoDir/testCodeCoverageReport.xml"

reporting {
    reports {
        named<JacocoCoverageReport>("testCodeCoverageReport") {
            testType.set(TestSuiteType.UNIT_TEST)
        }
    }
}

dependencies {
    jacocoAggregation(project(":docs"))
    jacocoAggregation(project(":integration-tests"))
    jacocoAggregation(project(":spring-annotated-data-binder-core"))
    jacocoAggregation(project(":spring-webflux-annotated-data-binder"))
    jacocoAggregation(project(":spring-webmvc-annotated-data-binder"))
    jacocoAggregation(project(":webflux-annotated-data-binder-spring-boot-starter"))
    jacocoAggregation(project(":webmvc-annotated-data-binder-spring-boot-starter"))
}

sonar {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "github-mattbertolini")
        property("sonar.projectKey", "mattbertolini_spring-annotated-web-data-binder")
        property("sonar.coverage.jacoco.xmlReportPaths", reportXmlFile)
    }
}
