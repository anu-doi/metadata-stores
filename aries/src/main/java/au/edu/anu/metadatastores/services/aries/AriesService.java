package au.edu.anu.metadatastores.services.aries;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.util.properties.PropertyLoader;

public class AriesService {
	static final Logger LOGGER = LoggerFactory.getLogger(AriesService.class);
	
	private static AriesService singleton_;
	private static StaffId staffService = StaffId.getSingleton();;
	private static ContractId contractService = ContractId.getSingleton();
	private static OutputId outputService = OutputId.getSingleton();
	private static PublicationId publicationService = PublicationId.getSingleton();
	private static DepartmentId departmentService = DepartmentId.getSingleton();
	
	private Properties properties_;
	
	/**
	 * Constructor class for the AriesService
	 */
	private AriesService() {
		properties_ = PropertyLoader.loadProperties("aries.properties");
		
		if (properties_ == null) {
			LOGGER.debug("AriesService.init(..): No aries service property file found.");
		}
	}
	
	/**
	 * Get the AriesService
	 * 
	 * @return The AriesService
	 */
	public static AriesService getSingleton() {
		if (singleton_ == null) {
			singleton_ = new AriesService();
		}
		return singleton_;
	}
	
	/**
	 * Get the contracts for the specified staff member
	 * 
	 * @param staffId The id of the staff member to get the contracts for
	 * @return The activity information
	 */
	public ANUActivity[] getContracts(String staffId) {
		return staffService.getContracts(staffId);
	}
	
	/**
	 * Gets the publications for the specified staff member
	 * @param staffId The id of the staff member to get publications for
	 * @return The publications associated with the staff member
	 */
	public Publication[] getPublications(String staffId) {
		return staffService.getPublications(staffId);
	}
	
	/**
	 * Gets the publication with the given aries output 6 code
	 * 
	 * @param publicationId The aries output 6 code of the publication
	 * @return The publication information
	 */
	public Publication getSinglePublication(String publicationId) {
		return publicationService.getSinglePublication(publicationId);
	}
	
	/**
	 * Get the funding authority url for the activity
	 * 
	 * @param anuActivityId THe id of the activity
	 * @return THe funding authority url
	 */
	public String getFundingAuthorityURLforANUActivity(String anuActivityId) {
		String arc_ands_url_rule = (String) properties_.get("ARC_ANDS_url");
		String result = arc_ands_url_rule.replace("ANUActivityId", anuActivityId);
		return result;
	}
	
	/**
	 * Get all the contract investigator codes
	 * 
	 * @return An array of contract investigator codes
	 */
	public String[] getAllContractInvestigatorCode() {
		return contractService.getAllContractInvestigatorCode();
	}
	
	/**
	 * Get all the output 6 codes
	 * 
	 * @return The output 6 codes
	 */
	public String[] getAllOutput6Codes() {
		return outputService.getAllOutput6Codes();
	}
	
	/**
	 * Get the university ids of the investigators assocated with a particular contract
	 * 
	 * @param contractId The id of the contract
	 * @return A list of university ids
	 */
	public String[] getInvestigatorsUniIDs(String contractId) {
		return contractService.getInvestigatorUniId(contractId);
	}
	
	/**
	 * Get the description of the specified activity
	 * 
	 * @param anuActivityId The id of the activity
	 * @return The description of the activity
	 */
	public String getANUActivityDesc(String anuActivityId) {
		return contractService.getANUActivityDesc(anuActivityId);
	}
	
	/**
	 * Get the author id of the activity
	 * @param anuActivityId The id of the activity
	 * @return The author id of the activity
	 */
	public String getANUActivityAuthorizeID(String anuActivityId) {
		return contractService.getANUActivityAuthorizeID(anuActivityId);
	}
	
	/**
	 * Get the investigators for the specified grants
	 * 
	 * @param grantIDs The list of grants to get the investigators for
	 * @return The investigators involved in the specified grants
	 */
	public String[] getInvestigators(String[] grantIDs) {
		String[] contractInvestigatorCodes = getAllContractInvestigatorCode();
		List<String> contractCodes = new ArrayList<String>();
		
		int index_1 = 0;
		int index_2 = 0;
		String tempCode = "";
		for (String contractInvestigatorCode : contractInvestigatorCodes) {
			index_1 = contractInvestigatorCode.indexOf("xx");
			index_2 = contractInvestigatorCode.indexOf('x', index_1 + 2);
			tempCode = contractInvestigatorCode.substring(index_1 + 2, index_2);
			if (!contractCodes.contains(tempCode)) {
				contractCodes.add(tempCode);
			}
		}
		
		return contractCodes.toArray(new String[0]);
	}
	
	/**
	 * Get the grant descriptions
	 * 
	 * @param grantIDs The ids of the grants to get descriptions for
	 * @return An array of the descriptions
	 */
	public String[] getGrantDescriptions(String[] grantIDs) {
		String[] grantDescriptions = new String[grantIDs.length];
		for (int i = 0; i < grantIDs.length; i++) {
			grantDescriptions[i] = getANUActivityDesc(grantIDs[i]);
		}
		return grantDescriptions;
	}
	
	/**
	 * Get the grant names
	 * 
	 * @param grantIDs The ids of the grants to get the names for
	 * @return An array of the grant names
	 */
	public String[] getGrantNames(String[] grantIDs) {
		String[] grantNames = new String[grantIDs.length];
		for (int i = 0; i < grantIDs.length; i++) {
			grantNames[i] = getANUActivityAuthorizeID(grantIDs[i]);
		}
		return grantNames;
	}
	
	/**
	 * Get all the publication codes
	 * 
	 * @return An array of publication codes
	 */
	public String[] getAllPublicationCodes() {
		return publicationService.getAllPublicationCodes();
	}
	
	//Have not added in getVivoUris as we may or may not use vivo
	
	/**
	 * Get the first authors for the specified publications
	 * 
	 * @param publicationCodes The publication codes to retrieve authors for
	 * @return The first authors
	 */
	public String[] getFirstAuthors(String[] publicationCodes) {
		return publicationService.getFirstAuthors(publicationCodes);
	}
	
	/**
	 * Get the university ids of the first authros for the specified publications
	 * @param publicationCodes The publication codes to retrieve authors for
	 * @return The authors university ids
	 */
	public String[] getFirstAuthorsUniIDs(String[] publicationCodes) {
		return publicationService.getFirstAuthorsUniIDs(publicationCodes);
	}
	
	/**
	 * Get the publications for the given year
	 * 
	 * @param year The year to retrieve publications for
	 * @return The publications
	 */
	public Publication[] getPublicationsForYear(String year) {
		return publicationService.getPublicationsForYear(year);
	}
	
	/**
	 * Get the department codes
	 * 
	 * @return A list of department codes
	 */
	public String[] getDepartmentCodes() {
		return departmentService.getDepartmentIDs();
	}
	
	/**
	 * Get the department names
	 * 
	 * @param departmentCodes Codes of the departments to retrieve names for
	 * @return The department names
	 */
	public String[] getDepartmentNames(String[] departmentCodes) {
		return departmentService.getDepartmentNames(departmentCodes);
	}
	
	/**
	 * Get all the contract codes
	 * 
	 * @return The contract codes
	 */
	public String[] getAllContractCodes() {
		String[] contractInvestigatorCodes = getAllContractInvestigatorCode();
		
		List<String> contractCodes = new ArrayList<String>();
		int index_1 = 0;
		String contractCode = null;
		for (String contractInvestigatorCode : contractInvestigatorCodes) {
			index_1 = contractInvestigatorCode.indexOf("xx");
			contractCode = contractInvestigatorCode.substring(0, index_1);
			if (!contractCodes.contains(contractCode)) {
				contractCodes.add(contractCode);
			}
		}
		return contractCodes.toArray(new String[0]);
	}
	
	public ANUStaff getStaffInformation(String staffId) {
		return staffService.getStaffInformation(staffId);
	}
	
	public ExternalStaff getExternalStaffInformation(String staffId) {
		return staffService.getExternalStaffInformation(staffId);
	}
	
	public ANUStaff[] findANUStaffByName(String surname, String givenName) {
		return staffService.findStaff(surname, givenName);
	}
	
	public ExternalStaff[] findExternalStaffByName(String surname, String givenName) {
		return staffService.findExternalStaff(surname, givenName);
	}
}
