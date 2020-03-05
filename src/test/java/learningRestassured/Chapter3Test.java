package learningRestassured;

import com.tngtech.java.junit.dataprovider.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class Chapter3Test {
	
	@DataProvider
	public static Object[][] zipCodesAndPlaces(){
		return new Object[][] {
			{ "us", "90210", "Beverly Hills" },
            { "us", "12345", "Schenectady" },
            { "ca", "B2R", "Waverley"},
            {"nl", "1001", "Amsterdam"}
		};
	}
	
	@Test
	@UseDataProvider("zipCodesAndPlaces")
	public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedPlaceName(String countryCode, String zipCode, String expectedPlaceName) {
		given().
			pathParam("cC", countryCode).pathParam("zC", zipCode).
		when().
			get("http://zippopotam.us/{cC}/{zC}").
		then().
        	assertThat().
        	body("places[0].'place name'", equalTo(expectedPlaceName));
	}
}
