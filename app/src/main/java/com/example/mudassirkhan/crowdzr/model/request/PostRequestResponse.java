package com.example.mudassirkhan.crowdzr.model.request;

import com.example.mudassirkhan.crowdzr.api.eventmessages.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostRequestResponse extends BaseResponse {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("expiry")
    @Expose
    private String expiry;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("shouter")
    @Expose
    private String shouter;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("CreaterInitialsSqWeb")
    @Expose
    private String createrInitialsSqWeb;
    @SerializedName("CreaterInitialsCirWeb")
    @Expose
    private String createrInitialsCirWeb;
    @SerializedName("CreaterInitialsSqMobile")
    @Expose
    private String createrInitialsSqMobile;
    @SerializedName("CreaterInitialsCirMobile")
    @Expose
    private String createrInitialsCirMobile;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("viewCount")
    @Expose
    private Integer viewCount;
    @SerializedName("usersWhoVisitedThisShoutoutsAlsoVisited")
    @Expose
    private List<Object> usersWhoVisitedThisShoutoutsAlsoVisited = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("findStacks")
    @Expose
    private Boolean findStacks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getShouter() {
        return shouter;
    }

    public void setShouter(String shouter) {
        this.shouter = shouter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreaterInitialsSqWeb() {
        return createrInitialsSqWeb;
    }

    public void setCreaterInitialsSqWeb(String createrInitialsSqWeb) {
        this.createrInitialsSqWeb = createrInitialsSqWeb;
    }

    public String getCreaterInitialsCirWeb() {
        return createrInitialsCirWeb;
    }

    public void setCreaterInitialsCirWeb(String createrInitialsCirWeb) {
        this.createrInitialsCirWeb = createrInitialsCirWeb;
    }

    public String getCreaterInitialsSqMobile() {
        return createrInitialsSqMobile;
    }

    public void setCreaterInitialsSqMobile(String createrInitialsSqMobile) {
        this.createrInitialsSqMobile = createrInitialsSqMobile;
    }

    public String getCreaterInitialsCirMobile() {
        return createrInitialsCirMobile;
    }

    public void setCreaterInitialsCirMobile(String createrInitialsCirMobile) {
        this.createrInitialsCirMobile = createrInitialsCirMobile;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public List<Object> getUsersWhoVisitedThisShoutoutsAlsoVisited() {
        return usersWhoVisitedThisShoutoutsAlsoVisited;
    }

    public void setUsersWhoVisitedThisShoutoutsAlsoVisited(List<Object> usersWhoVisitedThisShoutoutsAlsoVisited) {
        this.usersWhoVisitedThisShoutoutsAlsoVisited = usersWhoVisitedThisShoutoutsAlsoVisited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getFindStacks() {
        return findStacks;
    }

    public void setFindStacks(Boolean findStacks) {
        this.findStacks = findStacks;
    }


    public class Location {

        @SerializedName("formattedAddress")
        @Expose
        private String formattedAddress;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("streetname")
        @Expose
        private String streetname;
        @SerializedName("streetnumber")
        @Expose
        private Integer streetnumber;
        @SerializedName("countrycode")
        @Expose
        private String countrycode;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("stateCode")
        @Expose
        private String stateCode;
        @SerializedName("longlat")
        @Expose
        private Longlat longlat;

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getStreetname() {
            return streetname;
        }

        public void setStreetname(String streetname) {
            this.streetname = streetname;
        }

        public Integer getStreetnumber() {
            return streetnumber;
        }

        public void setStreetnumber(Integer streetnumber) {
            this.streetnumber = streetnumber;
        }

        public String getCountrycode() {
            return countrycode;
        }

        public void setCountrycode(String countrycode) {
            this.countrycode = countrycode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public Longlat getLonglat() {
            return longlat;
        }

        public void setLonglat(Longlat longlat) {
            this.longlat = longlat;
        }


    }


    public class Longlat {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

    }


    public class ShoutoutQuality {

        @SerializedName("quality")
        @Expose
        private Integer quality;
        @SerializedName("lastComputed")
        @Expose
        private String lastComputed;

        public Integer getQuality() {
            return quality;
        }

        public void setQuality(Integer quality) {
            this.quality = quality;
        }

        public String getLastComputed() {
            return lastComputed;
        }

        public void setLastComputed(String lastComputed) {
            this.lastComputed = lastComputed;
        }

    }


    public class StackCreatedFromThisShoutout {


    }

    public class CommitedToThisStack {


    }
}

