package StepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before
	public void beforeScenario() throws IOException{
		
		StepDef sd = new StepDef();
		if (sd.placeId==null) {
			
			sd.add_place_payload("Bikram", "Hindi", "World cross center");
			sd.user_calls_using_method("AddPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("Bikram", "getPlaceApi");
		}
		
		
		
	}

}
