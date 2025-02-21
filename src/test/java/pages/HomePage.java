package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//ul[@class='oxd-main-menu']//li[2]")
    WebElement pimMenuOption;
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public PIMPage accessPIMModule(WebDriver driver) {
        this.pimMenuOption.click();
        return new PIMPage(driver);
    }

    public WebElement getPimMenuOption() {
        return this.pimMenuOption;
    }
}
