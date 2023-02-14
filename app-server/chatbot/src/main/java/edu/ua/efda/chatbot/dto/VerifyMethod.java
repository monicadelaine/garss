package edu.ua.efda.chatbot.dto;

public enum VerifyMethod {
	
	PHONE("phone"),
	WEBSITE("WEBSITE"),
	BROCHURE("BROCHURE"),
	FROM_CONTACT("form_contact"),
	AAA("aaa");
	
    private String code;

    private VerifyMethod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
