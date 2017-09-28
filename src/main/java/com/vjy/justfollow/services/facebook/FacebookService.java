package com.vjy.justfollow.services.facebook;

import com.vjy.justfollow.vo.user.UserFbLoginVo;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookService {


    public UserFbLoginVo getFbDetails(String fbId, String fbAccessToken) {

        final String uri="https://graph.facebook.com/me?access_token=" + fbAccessToken;

        RestTemplate restTemplate = new RestTemplate();

        try {

            String stringObject = restTemplate.getForObject(uri,String.class);

            JSONObject jsonObject = new JSONObject(stringObject);

            String id = jsonObject.has("id") ? jsonObject.getString("id") : fbId;

            return new UserFbLoginVo(200, id);
        }catch (HttpClientErrorException e) {
            return new UserFbLoginVo(e.getRawStatusCode(), fbId);
        }

    }


    public UserFbLoginVo getFbDetails(String fbAccessToken) {

        final String uri="https://graph.facebook.com/me?access_token=" + fbAccessToken;

        RestTemplate restTemplate = new RestTemplate();

        try {

            String stringObject = restTemplate.getForObject(uri,String.class);

            JSONObject jsonObject = new JSONObject(stringObject);

            String id = jsonObject.has("id") ? jsonObject.getString("id") : null;

            return new UserFbLoginVo(200, id);
        }catch (HttpClientErrorException e) {
            return new UserFbLoginVo(e.getRawStatusCode(), null);
        }

    }
}
