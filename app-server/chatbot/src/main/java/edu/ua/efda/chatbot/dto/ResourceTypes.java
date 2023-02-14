
package edu.ua.efda.chatbot.dto;

import java.util.HashMap;

public enum ResourceTypes {
	

	activity_enrichment("activity_enrichment"),
	bill_assist("bill_assist"),
	counseling("counseling"),
	daycare("daycare"),
	death_burial("death_burial"),
	devices_prosthetics("devices_prosthetics"),
	drug_assist("drug_assist"),
	durable_equip("durable_equip"),
	food_pantry("food_pantry"),
	food_stamps("food_stamps"),
	food_vouchers("food_vouchers"),
	geriatricians("geriatricians"),
	hearing_aids("hearing_aids"),
	home_maintain("home_maintain"),
	home_meals("home_meals"),
	hospice_advd_care("hospice_advd_care"),
	house_cleaning("house_cleaning"),
	illness_disease("illness_disease"),
	incontinence_items("incontinence_items"),
	infrastr_chgs("infrastr_chgs"),
	legal_assist("legal_assist"),
	medical_supplies("medical_supplies"),
	medicare("medicare"),
	medicare_providers("medicare_providers"),
	medicare_waivers("medicare_waivers"),
	medic_alerting("medic_alerting"),
	memory_care("memory_care"),
	neurologists("neurologists"),
	nursing("nursing"),
	nursing_home("nursing_home"),
	occupational_therapy("occupational_therapy"),
	oral_dentures("oral_dentures"),
	oxygen("oxygen"),
	personal_care("personal_care"),
	physical_therapy("physical_therapy"),
	procedures("procedures"),
	psychiatrist("psychiatrist"),
	referral("referral"),
	rehabilitation_facility("rehabilitation_facility"),
	reimbursement("reimbursement"),
	report_problem("report_problem"),
	respite_care("respite_care"),
	specialists("specialists"),
	studies_trials("studies_trials"),
	supplier_research("supplier_research"),
	support_groups("support_groups"),
	technology("technology"),
	training("training"),
	transportation("transportation"),
	vision_aids("vision_aids"),
	housing_directory("housing_directory"),
	housing_assistance("housing_assistance"),
	veterans_affair("veterans_affairs"),
	financial_general("financial_general"),
	senior_discount("senior_discount"),
	job_training("job_training"),
	substance_abuse("substance_abuse"),
	pain_management("pain_management"),
	tax_help("tax_help"),
	clinics("clinics");
	
    private String code;
	private static HashMap<String,ResourceTypes> mMap;

    private ResourceTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    public static ResourceTypes getResourceTypeByString(String code) {
        if (mMap == null) {
            initializeMapping();
        }
        if (mMap.containsKey(code)) {
            return mMap.get(code);
        }
        return null;
    }

    private static void initializeMapping() {
        mMap = new HashMap<String, ResourceTypes>();
        for (ResourceTypes s : ResourceTypes.values()) {
            mMap.put(s.code, s);
        }
    }
	
}
