import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps", 
        publish = true,
        tags="@AC4",
        plugin = {"pretty", "html:target/TestReport/cucumber-reports.html"}
)
public class RunCucumberTest {
}
