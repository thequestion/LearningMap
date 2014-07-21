/**
 * 
 */
package cache;

import java.util.HashSet;

/**
 * @author wenbyuan
 *
 */
public class CampaignPlacementCache {

	private static CampaignPlacementCache cache = new CampaignPlacementCache();
	
	private static final String BULLSEYE = "PPA";
	private static final String DELIMITER = ";";
	
	public static CampaignPlacementCache getInstance() {
		return cache;
	}
	
	public HashSet<BullseyeAttribute> getAllBullseyeAttributesFromLogic(
			String logic) {
		if(logic == null) return null; 
		HashSet<BullseyeAttribute> attributes = new HashSet<BullseyeAttribute>();
		String[] logicTokens = getLogicTokens(logic);
		try {
			attributes = getBullseyeAttributesFromLogic(logicTokens);
		} catch (Exception e) {
			String errorMessage = "Invalid logic string: " + logic;
			System.out.println(errorMessage);
		}
		return attributes;
	}

	private String[] getLogicTokens(String logic) {
		return logic.split(DELIMITER);
	}

	private HashSet<BullseyeAttribute> getBullseyeAttributesFromLogic(String[] logicTokens) throws Exception {
		HashSet<BullseyeAttribute> attributes = new HashSet<BullseyeAttribute>();
		for(String logicToken : logicTokens){
			boolean hasBullseyeAttr = hasBullseyeAttributes(logicToken);
			if(hasBullseyeAttr){
				String name = getAttributeName(logicToken);
				if(name != null && name.length() != 0){
					BullseyeAttribute attribute = new BullseyeAttribute(name);
					attributes.add(attribute);
				}
			}
		}
		return attributes;
	}

	private boolean hasBullseyeAttributes(String split) {
		return split.indexOf(BULLSEYE) != -1;
	}

	private String getAttributeName(String logicToken) throws Exception {
		if(logicToken == null || logicToken.length() == 0){
			throw new Exception();	//should not happen
		}
		
		int length = logicToken.length();
		int currentPos = 0;
		int beginIndex = logicToken.indexOf(":") + 1; 
		if(beginIndex == 0) {		//Invalid logic string
			throw new Exception();
		}
		while(currentPos < length){
			char currentChar = logicToken.charAt(currentPos);
			if(currentChar == ','){
				return logicToken.substring(beginIndex, currentPos);
			}
			currentPos++;
		}
		
		return logicToken.substring(beginIndex);
	}
}
