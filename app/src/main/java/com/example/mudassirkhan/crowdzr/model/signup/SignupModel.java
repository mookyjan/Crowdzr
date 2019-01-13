package com.example.mudassirkhan.crowdzr.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignupModel {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {

        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("firstName")
        @Expose
        private String firstName;
        @SerializedName("lastName")
        @Expose
        private String lastName;
        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("profileImageURL")
        @Expose
        private String profileImageURL;
        @SerializedName("initialsSqWeb")
        @Expose
        private String initialsSqWeb;
        @SerializedName("initialsCirWeb")
        @Expose
        private String initialsCirWeb;
        @SerializedName("initialsSqMobile")
        @Expose
        private String initialsSqMobile;
        @SerializedName("avgTimeToRespond")
        @Expose
        private Integer avgTimeToRespond;
        @SerializedName("avgStarRating")
        @Expose
        private Integer avgStarRating;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("mayBeInterestedShoutouts")
        @Expose
        private List<Object> mayBeInterestedShoutouts = null;
        @SerializedName("visitedShoutouts")
        @Expose
        private List<Object> visitedShoutouts = null;
        @SerializedName("userDashboardData")
        @Expose
        private UserDashboardData userDashboardData;

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProfileImageURL() {
            return profileImageURL;
        }

        public void setProfileImageURL(String profileImageURL) {
            this.profileImageURL = profileImageURL;
        }

        public String getInitialsSqWeb() {
            return initialsSqWeb;
        }

        public void setInitialsSqWeb(String initialsSqWeb) {
            this.initialsSqWeb = initialsSqWeb;
        }

        public String getInitialsCirWeb() {
            return initialsCirWeb;
        }

        public void setInitialsCirWeb(String initialsCirWeb) {
            this.initialsCirWeb = initialsCirWeb;
        }

        public String getInitialsSqMobile() {
            return initialsSqMobile;
        }

        public void setInitialsSqMobile(String initialsSqMobile) {
            this.initialsSqMobile = initialsSqMobile;
        }

        public Integer getAvgTimeToRespond() {
            return avgTimeToRespond;
        }

        public void setAvgTimeToRespond(Integer avgTimeToRespond) {
            this.avgTimeToRespond = avgTimeToRespond;
        }

        public Integer getAvgStarRating() {
            return avgStarRating;
        }

        public void setAvgStarRating(Integer avgStarRating) {
            this.avgStarRating = avgStarRating;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Object> getMayBeInterestedShoutouts() {
            return mayBeInterestedShoutouts;
        }

        public void setMayBeInterestedShoutouts(List<Object> mayBeInterestedShoutouts) {
            this.mayBeInterestedShoutouts = mayBeInterestedShoutouts;
        }

        public List<Object> getVisitedShoutouts() {
            return visitedShoutouts;
        }

        public void setVisitedShoutouts(List<Object> visitedShoutouts) {
            this.visitedShoutouts = visitedShoutouts;
        }

        public UserDashboardData getUserDashboardData() {
            return userDashboardData;
        }

        public void setUserDashboardData(UserDashboardData userDashboardData) {
            this.userDashboardData = userDashboardData;
        }

    }


    public class UserDashboardData {

        @SerializedName("possibleEarningsLocation")
        @Expose
        private Integer possibleEarningsLocation;
        @SerializedName("possibleEarningsInterests")
        @Expose
        private Integer possibleEarningsInterests;
        @SerializedName("activeStacksLocation")
        @Expose
        private Integer activeStacksLocation;
        @SerializedName("activeStacksInterests")
        @Expose
        private Integer activeStacksInterests;
        @SerializedName("userFeed")
        @Expose
        private List<Object> userFeed = null;

        public Integer getPossibleEarningsLocation() {
            return possibleEarningsLocation;
        }

        public void setPossibleEarningsLocation(Integer possibleEarningsLocation) {
            this.possibleEarningsLocation = possibleEarningsLocation;
        }

        public Integer getPossibleEarningsInterests() {
            return possibleEarningsInterests;
        }

        public void setPossibleEarningsInterests(Integer possibleEarningsInterests) {
            this.possibleEarningsInterests = possibleEarningsInterests;
        }

        public Integer getActiveStacksLocation() {
            return activeStacksLocation;
        }

        public void setActiveStacksLocation(Integer activeStacksLocation) {
            this.activeStacksLocation = activeStacksLocation;
        }

        public Integer getActiveStacksInterests() {
            return activeStacksInterests;
        }

        public void setActiveStacksInterests(Integer activeStacksInterests) {
            this.activeStacksInterests = activeStacksInterests;
        }

        public List<Object> getUserFeed() {
            return userFeed;
        }

        public void setUserFeed(List<Object> userFeed) {
            this.userFeed = userFeed;
        }

    }
}
