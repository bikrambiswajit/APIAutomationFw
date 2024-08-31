package resources;

public enum APIResources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	getPlaceApi("maps/api/place/get/json"),
	deletePlaceApi("maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource) {

		this.resource = resource;
	}
	
	public String getResource() {
		return resource;

	}

}
