package slider_range;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Default_functionalites {

	public static WebDriver driver=null;

	@Test(enabled = false)
	public void scrollLoanAmountToMax() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://mvc.syncfusion.com/demos/web/slider/default");
		WebElement slider=driver.findElement(By.xpath("//div[@id='loanSlider']"));
		Dimension dim=slider.getSize();
		int width=dim.getWidth();
		System.out.println(width);

		WebElement sliderButton=driver.findElement(By.xpath("//div[@id='loanSlider']//a[contains(@class,'e-handle')]"));
		Actions action=new Actions(driver);
		action.dragAndDropBy(sliderButton, width, 0).build().perform();
		WebElement  loanamount=driver.findElement(By.xpath("(//*[@id='samplefile']//descendant::span[@class='value'])[1]"));
		String loanamt=loanamount.getText();
		Assert.assertEquals("1000000", loanamt);
		Assert.assertTrue(sliderButton.getAttribute("style").contains("left: 100%"));
	}

	@Test
	public void scrollLoanAmountToMin() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get("https://mvc.syncfusion.com/demos/web/slider/default");		
		WebElement slider=driver.findElement(By.xpath("//div[@id='loanSlider']"));

		WebElement sliderButton=driver.findElement(By.xpath("//div[@id='loanSlider']//a[contains(@class,'e-handle')]"));
		sliderButton.click();
		while(true) {
			Actions action=new Actions(driver);
			if(sliderButton.getAttribute("aria-label").equals("10400")) {
				action.sendKeys(Keys.ESCAPE).build().perform();
				break;
			}
			else {
				action.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
				.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
				.build().perform();
			}

		}
	}



}
