package com.example.framework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper extends HelperBase {
	
	protected WebDriver driver;
	public boolean acceptNextAlert = true;
	private WebDriverWait wait;
	
		public WebDriverHelper(ApplicationManager manager) {
			super(manager);
			this.manager = manager;
			String browser = manager.getProperty("browser");
			if ("firefox".equals(browser)) {
				driver = new FirefoxDriver();
			} else if ("ie".equals(browser)) {
				driver = new InternetExplorerDriver();
			} else {
				driver = new HtmlUnitDriver();
			}
			this.driver = manager.getDriver();
			wait = new WebDriverWait(driver, 10);
	}
	
	public boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	public boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	public String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	    	acceptNextAlert = true;
	    }
	  }

	protected void type(By locator, String text) {
		if (text != null) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
		}
	}

	protected void select(By locator, String text) {
		if (text != null) {
		new Select(driver.findElement(locator)).selectByVisibleText(text);
		}
	}
	
	protected void click(By locator) {
		driver.findElement(locator).click();
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void stop() {
	    driver.quit();
	    }

	public WebDriver getDriver() {
		return driver;
	}
	
}
