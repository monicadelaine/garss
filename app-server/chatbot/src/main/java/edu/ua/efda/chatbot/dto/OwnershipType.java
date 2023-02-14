package edu.ua.efda.chatbot.dto;

public enum OwnershipType {
	
//	select distinct (ownership_type) from providers;
//	 np_private
//	 local
//	 np_religious
//	 state_county
//	 np_other
//	 private
//	 combination
//	 unknown
	
	PRIVATE("private"),
	NP_RELIGIOUS("np_religious"),
	PRIVATE_NP("np_private"),
	COMBINATION("combination"),
	COUNTY_STATE("state_county"),
	LOCAL("local"),
	NP_OTHER("np_other"),
	UNKNOWN("unknown"),
	AAA("aaa");
	
    private String code;

    private OwnershipType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
