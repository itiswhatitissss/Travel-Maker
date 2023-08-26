package org.zerock.travelmaker;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.travelmaker.service.MainService;

@SpringBootTest
class TravelMakerApplicationTests {

	@Autowired
	private MainService mainService;
	@Test
	public void deletePLAN() {
		mainService.deletePlan(5L);
	}

}
