package com.test.infopulse.http.pojo;

import com.google.gson.annotations.SerializedName;

public class EmailValidationResult {

    @SerializedName("email")
    private String email;
    @SerializedName("did_you_mean")
    private String didYouMean;
    @SerializedName("user")
    private String user;
    @SerializedName("domain")
    private String domain;
    @SerializedName("format_valid")
    private Boolean formatValid = false;
    @SerializedName("mx_found")
    private Boolean mxFound;
    @SerializedName("smtp_check")
    private Boolean smtpCheck;
    @SerializedName("catch_all")
    private Boolean catchAll;
    @SerializedName("role")
    private Boolean role;
    @SerializedName("disposable")
    private Boolean disposable;
    @SerializedName("free")
    private Boolean free;
    @SerializedName("score")
    private Float score;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDidYouMean() {
        return didYouMean;
    }

    public void setDidYouMean(String didYouMean) {
        this.didYouMean = didYouMean;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Boolean getFormatValid() {
        return formatValid;
    }

    public void setFormatValid(Boolean formatValid) {
        this.formatValid = formatValid;
    }

    public Boolean getMxFound() {
        return mxFound;
    }

    public void setMxFound(Boolean mxFound) {
        this.mxFound = mxFound;
    }

    public Boolean getSmtpCheck() {
        return smtpCheck;
    }

    public void setSmtpCheck(Boolean smtpCheck) {
        this.smtpCheck = smtpCheck;
    }

    public Boolean getCatchAll() {
        return catchAll;
    }

    public void setCatchAll(Boolean catchAll) {
        this.catchAll = catchAll;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public Boolean getDisposable() {
        return disposable;
    }

    public void setDisposable(Boolean disposable) {
        this.disposable = disposable;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

}
