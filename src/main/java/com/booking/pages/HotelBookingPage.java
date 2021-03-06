package com.booking.pages;

import com.booking.model.BookingDetails;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

@At("http://hotel-test.equalexperts.io")
public class HotelBookingPage extends PageObject {

    @FindBy(css = "#firstname")
    private WebElementFacade firstname;

    @FindBy(css = "#lastname")
    private WebElementFacade lastname;

    @FindBy(css = "#totalprice")
    private WebElementFacade totalprice;

    @FindBy(css = "#depositpaid")
    private WebElementFacade depositpaid;

    @FindBy(css = "#checkin")
    private WebElementFacade checkin;

    @FindBy(css = "#checkout")
    private WebElementFacade checkout;

    @FindBy(css = "input[value*=Save]")
    private WebElementFacade save;

    @FindBy(css = ".container")
    private WebElementFacade formContainer;

    @FindBy(css = "div#bookings > div.row")
    private List<WebElement> bookingTableRecords;

    private By columnBy = By.cssSelector("div");

    private By deleteBy = By.cssSelector("div:nth-child(7)");

    private By firstNameBy = By.cssSelector("div:nth-child(1)");

    //This is another way to get the exact row from a table in UI and get column values.
    //This way no need to iterate through the table and store the data
    //refer methods getExistingBookingsWithFirstName, getBookingDetailsWithFirstName
    /*private String bookedRecordXpath = "//*[@id='bookings']//*[text()='{firstName}']//ancestor::div[@class='row']";
    private By lastNameTable = By.xpath("./div[2]");
    private By priceNameTable = By.xpath("./div[3]");
    private By depositPaidTable = By.xpath("./div[4]");
    private By checkinTable = By.xpath("./div[5]");
    private By checkoutTable = By.xpath("./div[6]");
    private By deleteBooking = By.xpath("./div[7]");*/

    @WhenPageOpens
    public void waitUntilCheckout() {
        getDriver().manage().window().maximize();
        formContainer.waitUntilVisible();
    }

    public void firstName(String firstName) {
        typeInto(firstname, firstName);
    }

    public void lastName(String lastName) {
        typeInto(lastname, lastName);
    }

    public void totalPrice(String price) {
        typeInto(totalprice, price);
    }

    public void isdepositPaid(String depositPaid) {
        depositpaid.selectByVisibleText(depositPaid);
    }

    public void checkinDate(String checkInDate) {
        typeInto(checkin, checkInDate);
    }

    public void checkoutDate(String checkoutDate) {
        typeInto(checkout, checkoutDate);
    }

    public void submitBooking() {
        save.click();
    }


    public BookingDetails getBooking(String firstName) {
        WebElement row = getBookingWithFirstName(firstName)
                .orElseThrow(() -> new IllegalArgumentException("No booking found with first name : " + firstName));
        BookingDetails booking = new BookingDetails();
        List<WebElement> columns = row.findElements(columnBy);
        for (int i = 0; i <= 5; i++) {
            switch (i) {
                case 0:
                    booking.setFirstName(columns.get(i).getText());
                    break;
                case 1:
                    booking.setLastName(columns.get(i).getText());
                    break;
                case 2:
                    booking.setPrice(Double.parseDouble(columns.get(i).getText()));
                    break;
                case 3:
                    booking.setIsDepositPaid(columns.get(i).getText());
                    break;
                case 4:
                    booking.setCheckinDate(columns.get(i).getText());
                    break;
                case 5:
                    booking.setCheckoutDate(columns.get(i).getText());
                    break;
            }
        }
        return booking;
    }

    public boolean isBookingExistWithFirstName(String firstName) {
        return getBookingWithFirstName(firstName)
                .isPresent();
    }

    public void deleteBookingForFirstName(String firstName) {
        WebElement bookingToDelete = getBookingWithFirstName(firstName)
                .orElseThrow(() -> new IllegalArgumentException("No booking found with first name : " + firstName));
        bookingToDelete.findElement(deleteBy).click();
    }

    //just for reference
    /*public WebElement getExistingBookingsWithFirstName(String firstName) throws TimeoutException {
        By byXpath = By.xpath(bookedRecordXpath.replace("{firstName}", firstName));
        waitForRenderedElements(byXpath);
        return getDriver().findElement(byXpath);
    }

    public BookingDetails getBookingDetailsWithFirstName(String firstName) {
        WebElement bookingsUI = getExistingBookingsWithFirstName(firstName);
        BookingDetails booking = new BookingDetails();
        booking.setFirstName(firstName);
        booking.setLastName(bookingsUI.findElement(lastNameTable).getText());
        booking.setPrice(bookingsUI.findElement(priceNameTable).getText());
        booking.setIsDepositPaid(bookingsUI.findElement(depositPaidTable).getText());
        booking.setCheckinDate(bookingsUI.findElement(checkinTable).getText());
        booking.setCheckoutDate(bookingsUI.findElement(checkoutTable).getText());

        return booking;
    }*/

    private Optional<WebElement> getBookingWithFirstName(String firstName) {
        return bookingTableRecords
                .stream()
                .filter(k -> k.findElement(firstNameBy).getText().equals(firstName))
                .findFirst();
    }

}
