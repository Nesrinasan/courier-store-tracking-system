package com.asan.couirertracking.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.asan.couirertracking.dto.modeldto.LocationDto;
import com.asan.couirertracking.dto.modeldto.StoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoresUtil {
	private final ObjectMapper objectMapper;
	private static final String STORES_JSON_PATH = "/static/stores.json";
	public static List<StoreDto> storeDtoList = new ArrayList<>();

	/**
	 * This method must work after project running. And store info must be loaded into memory
	 */
	@PostConstruct
	private void loadStores() {
		try {
			String storeDtoAsJSON = getStoreDtoAsJSON();
			convertToStoreDto(storeDtoAsJSON);
			System.out.println();
		} catch (IOException e) {
			log.error("Datalar yüklenirken bir hata oluştu.", e);
		}
	}

	public void convertToStoreDto(String storeInfoJson) throws JsonProcessingException {

		storeDtoList = objectMapper.readValue(storeInfoJson, new TypeReference<List<StoreDto>>() {});
	}

	public static String getStoreDtoAsJSON() throws IOException {

		InputStream is = new ClassPathResource(STORES_JSON_PATH).getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		StringBuilder storeDtoSB = new StringBuilder();

		String line;
		while ((line = bufferedReader.readLine()) != null) {
			storeDtoSB.append(line);
		}

		return storeDtoSB.toString();
	}

	public static SplitLocationFunction<LocationDto, String, String> splitLocationFunction = (latitudeLongitudeStr, regex) -> {
		String[] split = latitudeLongitudeStr.split(regex);
		String lat = split[0];
		String lng = split[1];
		LocationDto locationDto = new LocationDto(Double.parseDouble(lat), Double.parseDouble(lng));
		return locationDto;
	};

}
