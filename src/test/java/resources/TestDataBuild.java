package resources;

import java.util.ArrayList;
import java.util.List;

import StepDefinations.StepDef;
import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	
	public addPlace addPlacePayload(String name, String language, String address) {
		
			addPlace a = new addPlace();
			a.setAccuracy(50);
			a.setAddress(address);
			a.setLanguage(language);
			a.setName(name);
			a.setWebsite("http://google.com");
			
			Location l = new Location();
			l.setLat(-39.383494);
			l.setLng(34.427362);
			a.setLocation(l);
			
			List<String> ls = new ArrayList<String>();
			ls.add("shoe park");
			ls.add("shop");
			
			a.setTypes(ls);
			
			return a;
		
	}
	
	public String deletePlacePayload(String PlaceId) {
		
		
		return "{\r\n"
				+ "    \"place_id\":\""+PlaceId+"\"\r\n"
				+ "}";
	}

}
