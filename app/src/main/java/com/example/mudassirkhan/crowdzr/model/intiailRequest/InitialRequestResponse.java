package com.example.mudassirkhan.crowdzr.model.intiailRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitialRequestResponse {

    @SerializedName("initials")
    @Expose
    private Initials initials;

    public Initials getInitials() {
        return initials;
    }

    public void setInitials(Initials initials) {
        this.initials = initials;
    }

    public class Initials {

        @SerializedName("initialsSqWeb")
        @Expose
        private String initialsSqWeb;
        @SerializedName("initialsCirWeb")
        @Expose
        private String initialsCirWeb;
        @SerializedName("initialsSqMobile")
        @Expose
        private String initialsSqMobile;
        @SerializedName("initialsCirMobile")
        @Expose
        private String initialsCirMobile;

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

        public String getInitialsCirMobile() {
            return initialsCirMobile;
        }

        public void setInitialsCirMobile(String initialsCirMobile) {
            this.initialsCirMobile = initialsCirMobile;
        }

    }
}
