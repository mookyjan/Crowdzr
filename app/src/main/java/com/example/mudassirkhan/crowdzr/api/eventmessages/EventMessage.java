package com.example.mudassirkhan.crowdzr.api.eventmessages;


/**
 * Created by muhammadrashid on 21/02/2018.
 */

public class EventMessage {
    private String title;
    private String message;
    private boolean retry;
    private boolean loadMore;
    private boolean isValidUser;
    private boolean isValidPassword;
    private StatusCode statusCode;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRetry() {
        return retry;
    }

    public boolean isLoadMore() {
        return loadMore;
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    public boolean isValidPassword() {
        return isValidPassword;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public static class Builder {
        private String title;
        private String message;
        private boolean retry;
        private boolean loadMore;
        private boolean isValidUser;
        private boolean isValidEmail;
        private StatusCode statusCode;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder witRetry(boolean retry) {
            this.retry = retry;
            return this;
        }

        public Builder withLoadMore(boolean loadMore) {
            this.loadMore = loadMore;
            return this;
        }
        public Builder withIsValidUser(boolean isValidUser) {
            this.isValidUser = isValidUser;
            return this;
        }

        public Builder withIsValidPassword(boolean isValidPassword) {
            this.isValidEmail = isValidPassword;
            return this;
        }

        public Builder withStatusCode(StatusCode statusCode) {
            this.statusCode = statusCode;
            return this;
        }


        public EventMessage build() {
            EventMessage eventMessage = new EventMessage();
            eventMessage.title = title;
            eventMessage.message = message;
            eventMessage.retry = retry;
            eventMessage.loadMore = loadMore;
            eventMessage.isValidUser = isValidUser;
            eventMessage.isValidPassword = isValidEmail;
            eventMessage.statusCode = statusCode;
            return eventMessage;
        }
    }
}
