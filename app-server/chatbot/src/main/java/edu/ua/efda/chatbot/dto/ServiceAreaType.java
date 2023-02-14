package edu.ua.efda.chatbot.dto;

public enum ServiceAreaType {
	
		
	CITY("city"),
	COUNTY("county"),
	STATE("state"),
	DISTANCE("distance"),
	CUSTOM("custom");
	
    private String code;

    private ServiceAreaType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
