package stepDefinitions;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.io.IOException;

public class CucumberHooks {
	
	public BaseClass baseClass;
	public CucumberHooks(BaseClass baseClass) {
		this.baseClass = baseClass;
	}
	
	@Before
	public void beforeSuite(Scenario scenario) throws IOException, AWTException {
		baseClass.setUp();
	}
	
	@After
	public void afterSuite() throws IOException {
		baseClass.tearDown();
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if(scenario.isFailed()) {
			TakesScreenshot scrShot = ((TakesScreenshot) baseClass.getDriver());
			byte[] SrcFile = scrShot.getScreenshotAs(OutputType.BYTES);
			scenario.attach(SrcFile, "image/png", "image/png" + System.currentTimeMillis());
		}
	}
}
