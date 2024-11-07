package page.module;

import baseActions.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModulePage extends BaseSeleniumPage {

    @FindBy(xpath = "//div[@class='ib']//input[@class='form-control ']")
    private WebElement inputIdQuestion;

    @FindBy(xpath = "//input[@class='form-control ']")
    private WebElement inputName;

    @FindBy(xpath = "//div[@class='ib']//button[@class='btn-primary btn ']")
    private WebElement buttonMoveById;
    @FindBy(xpath = "//button[@class='btn btn-default']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//div[@_key='5']//button")
    private WebElement buttonCreateModule;

    @FindBy(xpath = "//input[@class='search form-control search_table_item']")
    private WebElement search;

    @FindBy(xpath = "//tbody//span")
    private WebElement nameModule;

    @FindBy(xpath = "//tbody//td[2]//span")
    private WebElement idModule;

    public ModulePage() {
        PageFactory.initElements(driver, this);
    }

    public String getNameModule(String name) throws InterruptedException {
        search.sendKeys(name);
        Thread.sleep(1000);
        return nameModule.getText();
    }

    public String getIdModule(String name) {
        search.sendKeys(name);
        return idModule.getText();
    }

    public void createNewModule(String name, String id) {
        buttonAdd.click();
        inputName.sendKeys(name);
        inputIdQuestion.sendKeys(id);
        buttonMoveById.click();
        buttonCreateModule.click();
    }
}
