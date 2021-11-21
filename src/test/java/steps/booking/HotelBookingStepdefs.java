package steps.booking;

import com.booking.pages.HotelBookingPage;
import com.booking.utilities.BookingDetails;
import com.booking.utilities.DateUtility;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class HotelBookingStepdefs {

    @Managed
    WebDriver driver;

    HotelBookingPage hotelBookingPage;

    private String firstName;

    private String dateFormat = "yyyy-MM-dd";

    @Given("I am on the Hotel Booking page")
    public void iAmOnHotelBookingPage() {
        hotelBookingPage.open();
    }

    @When("I book the hotel with below details")
    @And("I have an existing hotel booking")
    public void iBookTheHotelWithBelowDetails(List<Map<String, String>> bookingDetails) {
        bookingDetails.forEach(record -> {
            firstName = record.get("firstName");
            String lastName = record.get("lastName");
            String price = record.get("totalAmount");
            String depositPaid = record.get("isdepositpaid");
            String checkin = DateUtility.convertToDate(dateFormat,record.get("checkin"));
            String checkout = DateUtility.convertToDate(dateFormat,record.get("checkout"));
            hotelBookingPage.firstName(firstName);
            hotelBookingPage.lastName(lastName);
            hotelBookingPage.totalPrice(price);
            hotelBookingPage.isdepositPaid(depositPaid);
            hotelBookingPage.checkinDate(checkin);
            hotelBookingPage.checkoutDate(checkout);
            hotelBookingPage.submitBooking();
        });
    }

    @Then("I should see a hotel booked with below details")
    public void iShouldSeeAHotelBookedWithBelowDetails(List<Map<String, String>> bookingDetails) {
        bookingDetails.forEach(record -> {
            String expectedFirstName = record.get("firstName");
            String expectedLastName = record.get("lastName");
            String expectedPrice = record.get("totalAmount");
            String expectedDepositPaid = record.get("isdepositpaid");
            String expectedCheckin = DateUtility.convertToDate(dateFormat,record.get("checkin"));
            String expectedCheckout = DateUtility.convertToDate(dateFormat,record.get("checkout"));
            hotelBookingPage.getBookingsList(expectedFirstName);
            /*BookingDetails bookingDetailsActual = hotelBookingPage.getBookingDetailsWithFirstName(expectedFirstName);
            assertThat("No bookings found with firstName : " + expectedFirstName + " and last name : " + expectedLastName, bookingDetailsActual, is(notNullValue()));
            assertThat("price mismatch for lastName : " + expectedLastName, bookingDetailsActual.getPrice(), is(expectedPrice));
            assertThat("deposit paid mismatch for lastName : " + expectedLastName, bookingDetailsActual.getIsDepositPaid(), is(expectedDepositPaid));
            assertThat("checkin mismatch for lastName : " + expectedLastName, bookingDetailsActual.getCheckinDate(), is(expectedCheckin));
            assertThat("checkout mismatch for lastName : " + expectedLastName, bookingDetailsActual.getCheckoutDate(), is(expectedCheckout));*/
        });
    }

    @When("I delete the booking")
    public void iDeleteTheBooking() {
        WebElement bookingsActual = hotelBookingPage.getExistingBookingsWithFirstName(firstName);
        assertThat("No bookings found with first name : " + firstName, bookingsActual, is(notNullValue()));
        hotelBookingPage.deleteBookingForFirstName(firstName);
    }

    @Then("the booking should get deleted")
    public void theBookingShouldGetDeleted() {
        WebElement bookingsActual = null;
        try{
            bookingsActual = hotelBookingPage.getExistingBookingsWithFirstName(firstName);
        }
        catch (TimeoutException e){
        }
        assertThat("Booking with first name : " + firstName + " is not deleted", bookingsActual, is(nullValue()));
    }

    @After("@saveBooking")
    public void deleteBooking() {
        hotelBookingPage.deleteBookingForFirstName(firstName);
    }
}
