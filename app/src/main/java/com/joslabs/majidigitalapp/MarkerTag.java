package com.joslabs.majidigitalapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by OSCAR on 10/8/2017.
 */

public class MarkerTag
{
    String spname,sproviderId,url,markertype,imgadvert,username,desc,firebaseId;
    LatLng lats;


    public MarkerTag(String spname, String sproviderId, String url, String markertype, String imgadvert, String username, String desc, LatLng lats,String firebaseId) {
        this.spname = spname;
        this.sproviderId = sproviderId;
        this.url = url;
        this.markertype = markertype;
        this.imgadvert = imgadvert;
        this.username = username;
        this.desc = desc;
        this.lats = lats;
        this.firebaseId=firebaseId;
    }

    public String getSpname() {
        return spname;
    }



    public String getImgadvert() {
        return imgadvert;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public void setImgadvert(String imgadvert) {
        this.imgadvert = imgadvert;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarkertype() {
        return markertype;
    }

    public void setMarkertype(String markertype) {
        this.markertype = markertype;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getSproviderId() {
        return sproviderId;
    }

    public void setSproviderId(String sproviderId) {
        this.sproviderId = sproviderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LatLng getLats() {
        return lats;
    }

    public void setLats(LatLng lats) {
        this.lats = lats;
    }

    public MarkerTag() {
    }
}
