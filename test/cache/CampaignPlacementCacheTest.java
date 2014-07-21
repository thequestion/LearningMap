/**
 * 
 */
package cache;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

/**
 * @author wenbyuan
 *
 */
public class CampaignPlacementCacheTest {

	CampaignPlacementCache cache = null;
	
	static String[] DEFINITIONS = {
		"(NOT (pds.EbayRewardsBalance = null)) AND (NOT (pds.EbayRewardsBalance MATCHES (\"\")))"
	};
	
	static String[] VALID_LOGICS = {
		"PNL:25;PPA:10046;NEQ;GIF:2;1#NOP;PSL:male;PPA:10047;MCH:1,1;2#NOP;",
		"PNL:25;PPA:10046;NNE;GIF:1;PPA:10188,0;NOTNULL;1#NOP;GIF:2;PNL:100;PPA:10223,0;NEQ;2#NOP;",
		"PNL:25;PPA:10046;NNE;GIF:1;PNL:1;PPA:10028,0;NNE;1#NOP;GIF:2;PNL:10;PPA:10153;NNE;2#NOP;",
		"PNL:20476;PPA:10188,0,categoryId;ICH:1;GIT:1;PNL:20476;PPA:10180,0,categoryId;ICH:1;1&#35;NOP;GIT:2;PNL:20476;PPA:10184,0,categoryId;ICH:1;2&#35;NOP;GIT:3;PNL:20476;PPA:10186,0,categoryId;ICH:1;3&#35;NOP;GIT:7;PNL:20481;PPA:10188,0,categoryId;ICH:1;GIT:4;PNL:20481;PPA:10180,0,categoryId;ICH:1;4&#35;NOP;GIT:5;PNL:20481;PPA:10184,0,categoryId;ICH:1;5&#35;NOP;GIT:6;PNL:20481;PPA:10186,0,categoryId;ICH:1;6&#35;NOP;7&#35;NOP;GIT:11;PNL:20486;PPA:10188,0,categoryId;ICH:1;GIT:8;PNL:20486;PPA:10180,0,categoryId;ICH:1;8&#35;NOP;GIT:9;PNL:20486;PPA:10184,0,categoryId;ICH:1;9&#35;NOP;GIT:10;PNL:20486;PPA:10186,0,categoryId;ICH:1;10&#35;NOP;11&#35;NOP;GIT:15;PNL:20625;PPA:10188,0,categoryId;ICH:1;GIT:12;PNL:20625;PPA:10180,0,categoryId;ICH:1;12&#35;NOP;GIT:13;PNL:20625;PPA:10184,0,categoryId;ICH:1;13&#35;NOP;GIT:14;PNL:20625;PPA:10186,0,categoryId;ICH:1;14&#35;NOP;15&#35;NOP;GIT:19;PNL:20697;PPA:10188,0,categoryId;ICH:1;GIT:16;PNL:20697;PPA:10180,0,categoryId;ICH:1;16&#35;NOP;GIT:17;PNL:20697;PPA:10184,0,categoryId;ICH:1;17&#35;NOP;GIT:18;PNL:20697;PPA:10186,0,categoryId;ICH:1;18&#35;NOP;19&#35;NOP;GIF:20;SEG:4,UK_PandA_Segments_20110825_MD_TC_B01CA6B-00_OV_007ET&#36;;20&#35;NOP;GIF:21;SEG:4,GB_UK_RTM_BHVL_banner_HO_20120515_LK_BHVL_HO&#36;;NOT;21&#35;NOP;",
		"PPA:10180",
		"PNL:1200;PNC:p181;NLT;GIF:1;PDL:30 days;DF-Today:0;DSB;PPA:10180,lastUpdate;DGT;1&#35;NOP;GIF:2;PPA:10180,0;NOTNULL;2&#35;NOP;GIF:3;PPA:10180,1;NOTNULL;3&#35;NOP;GIF:4;PPA:10180,2;NOTNULL;4&#35;NOP;GIF:5;PPA:10180,3;NOTNULL;5&#35;NOP;",
        "PNL:0;PUA:82;SEL:_,,{PNL:11554;PRG:_;POV:PdsCatSiteWeightAspect,categoryId;ICH:1;};LEN;NGT;",
        "PNL:0;PUA:82;SEL:_,{PRG:_;POV:PdsCatSiteWeightAspect,categoryId;},{PNL:51580;PRG:_;POV:PdsCatSiteWeightAspect,categoryId;ICH:1;PSL:NEXT;PRG:_;PMV:PdsCatSiteWeightAspect,aspect,Brand;POV:AspectAndValues,values;MCH:1;AND;};LEN;NGT;",
        "1#NOP;PSL:760;PSL:940;PSC:p264;MCH:2,1;",
        "SEG:9,febseg1;GIF:1;PCCU:loyalty,flashData,code,CUSDYNISS;NOTNULL;1#NOP;",
        "PNL:279;PNC:p0;ICH:1;",
	};
	
	String[] INVALID_LOGICS = {
	        "PNL:279;PNC:p0;ICH:1",                         // ';' is missing at the end of the rule
	        "PNL:279;UNK:p0;ICH:1;",                         // unknown operation 'UNK'
			";;;",
			"sdf",
			"PPA",
			"PPA;PPA;PPA",
			"",
			"PPA:;PPA:;"
	};
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cache = CampaignPlacementCache.getInstance();
	}

	@Test
	public void testGetAllBullseyeAttributesFromLogics() {
		System.out.println("Test valid logics -- begin");
		for(int i = 0; i < VALID_LOGICS.length; i++) {
			HashSet<BullseyeAttribute> attributes = cache.getAllBullseyeAttributesFromLogic(VALID_LOGICS[i]);
			System.out.println(attributes);
		}
		System.out.println("Test valid logics -- end");
	}
	
	@Test
	public void testGetAllBullseyeAttributesFromInvalidLogis() {
		System.out.println("Test invalid logics -- begin");
		for(int i = 0; i < INVALID_LOGICS.length; i++) {
			HashSet<BullseyeAttribute> attributes = cache.getAllBullseyeAttributesFromLogic(INVALID_LOGICS[i]);
			assertTrue(attributes.size() == 0);
		}
		assertNull( cache.getAllBullseyeAttributesFromLogic(null) );
		
		System.out.println("Test invalid logics -- end");
	}

}
