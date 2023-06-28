package com.asan.couirertracking.unittest;

import com.asan.couirertracking.utility.StoresUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class StoresUtilUnitTest {


	@Test
	public void test() throws IOException {
		String expectedMStoreJson = " [" +
				"  {" +
				"    \"name\": \"Ataşehir Market\"," +
				"    \"locationDto\": {" +
				"    \"lat\": 40.9923307," +
				"    \"lng\": 29.1244229" +
				"    }" +
				"  }," +
				"  {" +
				"    \"name\": \"Novada Market\"," +
				"    \"locationDto\": {" +
				"    \"lat\": 40.986106," +
				"    \"lng\": 29.1161293" +
				"    }" +
				"  }," +
				"  {" +
				"    \"name\": \"Beylikdüzü Market\"," +
				"    \"locationDto\": {" +
				"    \"lat\": 41.0066851," +
				"    \"lng\": 28.6552262" +
				"    }" +
				"  }," +
				"  {" +
				"    \"name\": \"Ortaköy Market\"," +
				"    \"locationDto\": {" +
				"    \"lat\": 41.055783," +
				"    \"lng\": 29.0210292" +
				"    }" +
				"  }," +
				"  {" +
				"    \"name\": \"Caddebostan Market\"," +
				"    \"locationDto\": {" +
				"    \"lat\": 40.9632463," +
				"    \"lng\": 29.0630908" +
				"    }" +
				"  }" +
				"]";

		String storeDtoAsJSON = StoresUtil.getStoreDtoAsJSON();

		Assertions.assertEquals(storeDtoAsJSON, expectedMStoreJson);
	}

}
