package com.asan.couirertracking.unittest;

import com.asan.couirertracking.utility.CalculateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculateUnitTest {

	@Test
	public void calculateDistance_Test(){

		double courierLat = 44.055783;
		double courierLng = 36.21523;
		double venueLat = 40.9923307;
		double venueLng = 29.1244229;

		double calculateDistance = CalculateUtil.calculateDistance(courierLat, courierLng, venueLat, venueLng);
		Assertions.assertEquals(calculateDistance, 673.0);
	}

}
