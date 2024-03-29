package edu.ua.efda.chatbot.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class AlabamaLocations {
	
	public static final String[] countyArray= {
			"Autauga",
			"Baldwin",
			"Barbour",
			"Bibb",
			"Blount",
			"Bullock",
			"Butler",
			"Calhoun",
			"Chambers",
			"Cherokee",
			"Chilton",
			"Choctaw",
			"Clarke",
			"Clay",
			"Cleburne",
			"Coffee",
			"Colbert",
			"Conecuh",
			"Coosa",
			"Covington",
			"Crenshaw",
			"Cullman",
			"Dale",
			"Dallas",
			"De Kalb",
			"Elmore",
			"Escambia",
			"Etowah",
			"Fayette",
			"Franklin",
			"Geneva",
			"Greene",
			"Hale",
			"Henry",
			"Houston",
			"Jackson",
			"Jefferson",
			"Lamar",
			"Lauderdale",
			"Lawrence",
			"Lee",
			"Limestone",
			"Lowndes",
			"Macon",
			"Madison",
			"Marengo",
			"Marion",
			"Marshall",
			"Mobile",
			"Monroe",
			"Montgomery",
			"Morgan",
			"Perry",
			"Pickens",
			"Pike",
			"Randolph",
			"Russell",
			"St. Clair",
			"Shelby",
			"Sumter",
			"Talladega",
			"Tallapoosa",
			"Tuscaloosa",
			"Walker",
			"Washington",
			"Wilcox",
			"Winston"
	};
	public static final String[] cityArray={
			"Abbeville",
			"Adamsville",
			"Addison",
			"Akron",
			"Alabaster",
			"Albertville",
			"Alexander City",
			"Alexandria",
			"Aliceville",
			"Allgood",
			"Altoona",
			"Andalusia",
			"Anderson",
			"Anniston",
			"Arab",
			"Ardmore",
			"Argo",
			"Ariton",
			"Arley",
			"Ashford",
			"Ashland",
			"Ashville",
			"Athens",
			"Atmore",
			"Attalla",
			"Auburn",
			"Autaugaville",
			"Avon",
			"Babbie",
			"Baileyton",
			"Banks",
			"Bay Minette",
			"Bayou La Batre",
			"Bear Creek",
			"Beatrice",
			"Beaverton",
			"Belk",
			"Benton",
			"Berry",
			"Bessemer",
			"Billingsley",
			"Birmingham",
			"Black",
			"Blountsville",
			"Blue Mountain",
			"Blue Ridge",
			"Blue Springs",
			"Boaz",
			"Boligee",
			"Bon Air",
			"Branchville",
			"Brantley",
			"Brent",
			"Brewton",
			"Bridgeport",
			"Brighton",
			"Brilliant",
			"Brookside",
			"Brookwood",
			"Brundidge",
			"Butler",
			"Bynum",
			"Cahaba Heights",
			"Calera",
			"Camden",
			"Camp Hill",
			"Carbon Hill",
			"Cardiff",
			"Carolina",
			"Carrollton",
			"Castleberry",
			"Cedar Bluff",
			"Center Point",
			"Centre",
			"Centreville",
			"Chalkville",
			"Chatom",
			"Chelsea",
			"Cherokee",
			"Chickasaw",
			"Childersburg",
			"Citronelle",
			"Clanton",
			"Clay",
			"Clayhatchee",
			"Clayton",
			"Cleveland",
			"Clio",
			"Coaling",
			"Coffee Springs",
			"Coffeeville",
			"Coker",
			"Collinsville",
			"Colony",
			"Columbia",
			"Columbiana",
			"Concord",
			"Coosada",
			"Cordova",
			"Cottonwood",
			"County Line",
			"Courtland",
			"Cowarts",
			"Creola",
			"Crossville",
			"Cuba",
			"Cullman",
			"Dadeville",
			"Daleville",
			"Daphne",
			"Dauphin Island",
			"Daviston",
			"Dayton",
			"Deatsville",
			"Decatur",
			"Demopolis",
			"Detroit",
			"Dodge City",
			"Dora",
			"Dothan",
			"Double Springs",
			"Douglas",
			"Dozier",
			"Dutton",
			"East Brewton",
			"Eclectic",
			"Edgewater",
			"Edwardsville",
			"Elba",
			"Elberta",
			"Eldridge",
			"Elkmont",
			"Elmore",
			"Emelle",
			"Enterprise",
			"Epes",
			"Ethelsville",
			"Eufaula",
			"Eunola",
			"Eutaw",
			"Eva",
			"Evergreen",
			"Excel",
			"Fairfield",
			"Fairhope",
			"Fairview",
			"Falkville",
			"Faunsdale",
			"Fayette",
			"Five Points",
			"Flomaton",
			"Florala",
			"Florence",
			"Foley",
			"Forestdale",
			"Forkland",
			"Fort Deposit",
			"Fort Payne",
			"Fort Rucker",
			"Franklin",
			"Frisco City",
			"Fruithurst",
			"Fulton",
			"Fultondale",
			"Fyffe",
			"Gadsden",
			"Gainesville",
			"Gantt",
			"Gantts Quarry",
			"Garden City",
			"Gardendale",
			"Gaylesville",
			"Geiger",
			"Geneva",
			"Georgiana",
			"Geraldine",
			"Gilbertown",
			"Glen Allen",
			"Glencoe",
			"Glenwood",
			"Goldville",
			"Good Hope",
			"Goodwater",
			"Gordo",
			"Gordon",
			"Gordonville",
			"Goshen",
			"Grand Bay",
			"Grant",
			"Grayson Valley",
			"Graysville",
			"Greensboro",
			"Greenville",
			"Grimes",
			"Grove Hill",
			"Guin",
			"Gulf Shores",
			"Guntersville",
			"Gurley",
			"Gu-Win",
			"Hackleburg",
			"Haleburg",
			"Haleyville",
			"Hamilton",
			"Hammondville",
			"Hanceville",
			"Harpersville",
			"Hartford",
			"Hartselle",
			"Harvest",
			"Hayden",
			"Hayneville",
			"Hazel Green",
			"Headland",
			"Heath",
			"Heflin",
			"Helena",
			"Henagar",
			"Highland Lake",
			"Hillsboro",
			"Hobson City",
			"Hodges",
			"Hokes Bluff",
			"Holly Pond",
			"Hollywood",
			"Holt",
			"Homewood",
			"Hoover",
			"Horn Hill",
			"Hueytown",
			"Huguley",
			"Huntsville",
			"Hurtsboro",
			"Hytop",
			"Ider",
			"Indian Springs Village",
			"Irondale",
			"Jackson",
			"Jacksons' Gap",
			"Jacksonville",
			"Jasper",
			"Jemison",
			"Kansas",
			"Kennedy",
			"Killen",
			"Kimberly",
			"Kinsey",
			"Kinston",
			"Ladonia",
			"La Fayette",
			"Lake Purdy",
			"Lakeview",
			"Lake View",
			"Lanett",
			"Langston",
			"Leeds",
			"Leesburg",
			"Leighton",
			"Lester",
			"Level Plains",
			"Lexington",
			"Libertyville",
			"Lincoln",
			"Linden",
			"Lineville",
			"Lipscomb",
			"Lisman",
			"Littleville",
			"Livingston",
			"Loachapoka",
			"Lockhart",
			"Locust Fork",
			"Louisville",
			"Lowndesboro",
			"Loxley",
			"Luverne",
			"Lynn",
			"McDonald Chapel",
			"Macedonia",
			"McIntosh",
			"McKenzie",
			"McMullen",
			"Madison",
			"Madrid",
			"Malvern",
			"Maplesville",
			"Margaret",
			"Marion",
			"Maytown",
			"Meadowbrook",
			"Memphis",
			"Mentone",
			"Meridianville",
			"Midfield",
			"Midland City",
			"Midway",
			"Mignon",
			"Millbrook",
			"Millport",
			"Millry",
			"Minor",
			"Mobile",
			"Monroeville",
			"Montevallo",
			"Montgomery",
			"Moody",
			"Moores Mill",
			"Mooresville",
			"Morris",
			"Mosses",
			"Moulton",
			"Moundville",
			"Mountainboro",
			"Mountain Brook",
			"Mount Olive",
			"Mount Vernon",
			"Mulga",
			"Munford",
			"Muscle Shoals",
			"Myrtlewood",
			"Napier Field",
			"Natural Bridge",
			"Nauvoo",
			"Nectar",
			"Needham",
			"Newbern",
			"New Brockton",
			"New Hope",
			"New Market",
			"New Site",
			"Newton",
			"Newville",
			"North Bibb",
			"North Courtland",
			"North Johns",
			"Northport",
			"Notasulga",
			"Oak Grove",
			"Oak Hill",
			"Oakman",
			"Odenville",
			"Ohatchee",
			"Oneonta",
			"Onycha",
			"Opelika",
			"Opp",
			"Orange Beach",
			"Orrville",
			"Owens Cross Roads",
			"Oxford",
			"Ozark",
			"Paint Rock",
			"Parrish",
			"Pelham",
			"Pell City",
			"Pennington",
			"Petrey",
			"Phenix City",
			"Phil Campbell",
			"Pickensville",
			"Piedmont",
			"Pike Road",
			"Pinckard",
			"Pine Apple",
			"Pine Hill",
			"Pine Ridge",
			"Pinson",
			"Pisgah",
			"Pleasant Grove",
			"Pleasant Groves",
			"Point Clear",
			"Pollard",
			"Powell",
			"Prattville",
			"Priceville",
			"Prichard",
			"Providence",
			"Ragland",
			"Rainbow City",
			"Rainsville",
			"Ranburne",
			"Red Bay",
			"Red Level",
			"Redstone Arsenal",
			"Reece City",
			"Reform",
			"Rehobeth",
			"Repton",
			"Ridgeville",
			"River Falls",
			"Riverside",
			"Riverview",
			"Roanoke",
			"Robertsdale",
			"Rock Creek",
			"Rockford",
			"Rock Mills",
			"Rogersville",
			"Rosa",
			"Russellville",
			"Rutledge",
			"St. Florian",
			"Saks",
			"Samson",
			"Sand Rock",
			"Sanford",
			"Saraland",
			"Sardis City",
			"Satsuma",
			"Scottsboro",
			"Section",
			"Selma",
			"Selmont-West Selmont",
			"Sheffield",
			"Shiloh",
			"Shorter",
			"Silas",
			"Silverhill",
			"Sipsey",
			"Skyline",
			"Slocomb",
			"Smiths",
			"Smoke Rise",
			"Snead",
			"Somerville",
			"Southside",
			"South Vinemont",
			"Spanish Fort",
			"Springville",
			"Steele",
			"Stevenson",
			"Sulligent",
			"Sumiton",
			"Summerdale",
			"Susan Moore",
			"Sweet Water",
			"Sylacauga",
			"Sylvania",
			"Sylvan Springs",
			"Talladega",
			"Talladega Springs",
			"Tallassee",
			"Tarrant",
			"Taylor",
			"Theodore",
			"Thomaston",
			"Thomasville",
			"Thorsby",
			"Tillmans Corner",
			"Town Creek",
			"Toxey",
			"Trafford",
			"Triana",
			"Trinity",
			"Troy",
			"Trussville",
			"Tuscaloosa",
			"Tuscumbia",
			"Tuskegee",
			"Underwood-Petersville",
			"Union",
			"Union Grove",
			"Union Springs",
			"Uniontown",
			"Valley",
			"Valley Head",
			"Vance",
			"Vernon",
			"Vestavia Hills",
			"Vina",
			"Vincent",
			"Vredenburgh",
			"Wadley",
			"Waldo",
			"Walnut Grove",
			"Warrior",
			"Waterloo",
			"Waverly",
			"Weaver",
			"Webb",
			"Wedowee",
			"West Blocton",
			"West End-Cobb Town",
			"West Jefferson",
			"West Point",
			"Wetumpka",
			"White Hall",
			"Wilsonville",
			"Wilton",
			"Winfield",
			"Woodland",
			"Woodville",
			"Yellow Bluff",
			"York"
	};

	public static ArrayList<String> countyList = new ArrayList<String>(Arrays.asList(countyArray));
	public static ArrayList<String> cityList = new ArrayList<String>(Arrays.asList(cityArray));


}
