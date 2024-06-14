package org.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith (Cucumber.class)
@CucumberOptions(
        features = {"src/Features/Login.feature"},
        glue = {"stepDef"},
        tags = "@run",
        monochrome = true,
        plugin = {"pretty" ,
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/hc-cucumber.xml",
                "rerun:target/hc-rerun.txt",
                "html:target/cucumber-jvm-report",
                "html:target/cucumber-reports",
                "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"
        }
)
class AppTest{

}

