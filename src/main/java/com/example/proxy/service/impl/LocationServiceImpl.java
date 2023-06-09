package com.example.proxy.service.impl;

import com.example.proxy.dto.response.LocationResDTO;
import com.example.proxy.exception.TechnicalException;
import com.example.proxy.feign.client.locations.LocationsClient;
import com.example.proxy.feign.rest.locations.Locations;
import com.example.proxy.service.ILocationService;
import com.example.proxy.util.ErrorDescriptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService {
    @Value("${feign.accuweather.apikey}")
    String apiKey;

    LocationsClient client;

    @Autowired
    public LocationServiceImpl(LocationsClient client) {
        this.client = client;
    }

    @Override
    public List<LocationResDTO> getLocations(String q) throws TechnicalException {
        List<LocationResDTO> response = new ArrayList<>();

        try {
            List<Locations> locations = client.searchByQ(apiKey, q);

            if (!locations.isEmpty()) {
                locations.forEach(item -> response.add(LocationResDTO.convert(item)));
            }
        }catch (Exception e){
            throw new TechnicalException(ErrorDescriptionUtil.E_GENERAL_EXCEPTION_CODE,
                    ErrorDescriptionUtil.E_GENERAL_EXCEPTION);
        }

        return response;
    }

    @Override
    public LocationResDTO getLocationByKey(String key) throws TechnicalException {
        LocationResDTO response;

        try {
            Locations location = client.searchByLocationKey(apiKey, key);
            response = LocationResDTO.convert(location);
        }catch (Exception e){
            throw new TechnicalException(ErrorDescriptionUtil.E_GENERAL_EXCEPTION_CODE,
                    ErrorDescriptionUtil.E_GENERAL_EXCEPTION);
        }

        return response;
    }
}
