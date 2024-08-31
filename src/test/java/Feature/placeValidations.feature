Feature: Validating Place APIs
@AddPlaceTest @Regression
Scenario Outline: Verify if the place is being added successfully by addPlaceApi

			Given Add place payload "<name>" "<language>" "<address>"
			When User calls "AddPlaceAPI" using "POST" method
			Then API call got success with status code 200
			And "status" in response body is "OK"
			And "scope" in response body is "APP"
			And Verify place_id created maps to "<name>" using "getPlaceApi"
			
Examples: 
		|name 	 | language |address		   |
	|AAhouse |  English |World cross center|
	#|BBhouse | Spanish  |Sea cross center  |
	#|CChouse | Portugese  |Land cross center  |		
	
@DeletePlaceTest @Regression
	Scenario: Verify if delete place functionality is working
			
			Given DeletePlace payload
			When User calls "deletePlaceApi" using "POST" method
			Then API call got success with status code 200
			And "status" in response body is "OK"