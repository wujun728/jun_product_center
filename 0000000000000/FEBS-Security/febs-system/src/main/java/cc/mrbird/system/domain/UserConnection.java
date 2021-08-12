package cc.mrbird.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "t_userconnection")
public class UserConnection implements Serializable {

    private static final long serialVersionUID = 4524865782905130277L;

    @Id
    @Column(name = "userId")
    private String userid;

    @Id
    @Column(name = "providerId")
    private String providerid;

    @Id
    @Column(name = "providerUserId")
    private String provideruserid;

    private Integer rank;

    @Column(name = "displayName")
    private String displayname;

    @Column(name = "profileUrl")
    private String profileurl;

    @Column(name = "imageUrl")
    private String imageurl;

    @Column(name = "accessToken")
    private String accesstoken;

    private String secret;

    @Column(name = "refreshToken")
    private String refreshtoken;

    @Column(name = "expireTime")
    private Long expiretime;

    /**
     * @return userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * @return providerId
     */
    public String getProviderid() {
        return providerid;
    }

    /**
     * @param providerid
     */
    public void setProviderid(String providerid) {
        this.providerid = providerid == null ? null : providerid.trim();
    }

    /**
     * @return providerUserId
     */
    public String getProvideruserid() {
        return provideruserid;
    }

    /**
     * @param provideruserid
     */
    public void setProvideruserid(String provideruserid) {
        this.provideruserid = provideruserid == null ? null : provideruserid.trim();
    }

    /**
     * @return rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * @return displayName
     */
    public String getDisplayname() {
        return displayname;
    }

    /**
     * @param displayname
     */
    public void setDisplayname(String displayname) {
        this.displayname = displayname == null ? null : displayname.trim();
    }

    /**
     * @return profileUrl
     */
    public String getProfileurl() {
        return profileurl;
    }

    /**
     * @param profileurl
     */
    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl == null ? null : profileurl.trim();
    }

    /**
     * @return imageUrl
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * @param imageurl
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    /**
     * @return accessToken
     */
    public String getAccesstoken() {
        return accesstoken;
    }

    /**
     * @param accesstoken
     */
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    /**
     * @return secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret
     */
    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    /**
     * @return refreshToken
     */
    public String getRefreshtoken() {
        return refreshtoken;
    }

    /**
     * @param refreshtoken
     */
    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken == null ? null : refreshtoken.trim();
    }

    /**
     * @return expireTime
     */
    public Long getExpiretime() {
        return expiretime;
    }

    /**
     * @param expiretime
     */
    public void setExpiretime(Long expiretime) {
        this.expiretime = expiretime;
    }
}