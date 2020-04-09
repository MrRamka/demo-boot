package com.ybcompany.demo.security;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


@Component
public class IpAuthenticationProvider implements AuthenticationProvider {

    private String RUSSIA = "Russian Federation";

    /*
     * File location: resources/GeoLite2-City.mmdb
     */
    private final String FILE_LOCATION = "GeoLite2-City.mmdb";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String userIp = details.getRemoteAddress();

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (isSuitableIpAddress(userIp)) {
            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());

        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * Checking Ip Address. It must be from certain country.
     * Here from Russia
     * <p>
     * Geolocation by IP: https://www.baeldung.com/geolocation-by-ip-with-maxmind
     *
     * @param ip
     * @return Allowed or not ip address
     */
    private boolean isSuitableIpAddress(String ip) {
        try {
            File database = new File(
                    getClass()
                            .getClassLoader()
                            .getResource(FILE_LOCATION)
                            .getFile()
            );
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = dbReader.city(ipAddress);
            String countryName = response.getCountry().getName();


            if (countryName.equals(RUSSIA)) {
                return true;
            } else {
                return false;
            }

        } catch (IOException | GeoIp2Exception e) {
            return false;
        }

    }

}
