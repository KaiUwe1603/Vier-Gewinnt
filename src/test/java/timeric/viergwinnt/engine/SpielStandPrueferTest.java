package timeric.viergwinnt.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import timeric.viergwinnt.data.Chip;
import timeric.viergwinnt.data.Spieler;
import timeric.viergwinnt.data.Spielinfo;
import timeric.viergwinnt.engine.GameEngine.SpielStand;
import timeric.viergwinnt.engine.GameEngine.SpielStandPruefer;

class SpielStandPrueferTest {

	private static final Spieler TEST_SPIELER_1 = new Spieler("TEST_SPIELER_1", null);
	private static final Spieler TEST_SPIELER_2 = new Spieler("TEST_SPIELER_2", null);

	private static final Spielinfo TEST_SPIELINFO = new Spielinfo(2, 7, 6);

	private SpielStandPruefer testObject;

	@BeforeEach
	void setUp() {
		testObject = new SpielStandPruefer(TEST_SPIELINFO);
	}

	@Test
	void testPruefe_ErsterZug() {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();
		spielStandMap.put(0, Arrays.asList(createChip(TEST_SPIELER_1)));
		spielStandMap.put(1, Collections.emptyList());
		spielStandMap.put(2, Collections.emptyList());
		spielStandMap.put(3, Collections.emptyList());
		spielStandMap.put(4, Collections.emptyList());
		spielStandMap.put(5, Collections.emptyList());
		spielStandMap.put(6, Collections.emptyList());
		
		assertEquals(SpielStand.NAECHSTER_SPIELER, testObject.pruefe(spielStandMap, TEST_SPIELER_2));
	}

	@Test
	void testPruefe_AlleVerloren() {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();

		// @formatter:off
		spielStandMap.put(0, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(1, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(2, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(3, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(4, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(5, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(6, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1)
			));
		// @formatter:on

		assertEquals(SpielStand.ALLE_VERLOREN, testObject.pruefe(spielStandMap, TEST_SPIELER_1));
	}

	@Test
	void testPruefe_Spieler2_Gewonnen_Horizontal() {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();

		// @formatter:off
		spielStandMap.put(0, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(1, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(2, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(3, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(4, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(5, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(6, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1)
			));
		// @formatter:on
		
		assertEquals(SpielStand.GEWONNEN, testObject.pruefe(spielStandMap, TEST_SPIELER_1));
	}

	@Test
	void testPruefe_Spieler1_Gewonnen_Vertikal() {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();

		// @formatter:off
		spielStandMap.put(0, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(1, Arrays.asList(
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(2, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(3, Arrays.asList(
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(4, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(5, Arrays.asList(
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(6, Arrays.asList(
				createChip(TEST_SPIELER_2)
			));
		// @formatter:on

		assertEquals(SpielStand.GEWONNEN, testObject.pruefe(spielStandMap, TEST_SPIELER_1));
	}

	@Test
	void testPruefe_Spieler2_Gewonnen_Diagonal() {
		Map<Integer, List<Chip>> spielStandMap = new HashMap<>();

		// @formatter:off
		spielStandMap.put(0, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(1, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(2, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(3, Arrays.asList(
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2),
				createChip(TEST_SPIELER_1),
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(4, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		
		spielStandMap.put(5, Arrays.asList(
				createChip(TEST_SPIELER_2)
			));
		
		spielStandMap.put(6, Arrays.asList(
				createChip(TEST_SPIELER_1)
			));
		// @formatter:on

		assertEquals(SpielStand.GEWONNEN, testObject.pruefe(spielStandMap, TEST_SPIELER_1));
	}

	private static Chip createChip(Spieler testSpieler) {
		return new Chip(testSpieler, 0, 0);
	}

}
