package com.zhenhaikj.factoryside.mvp.requestbody;

public class GetUserInfo {

    /**
     * userName : string
     * adminToken : string
     * timestamp : string
     * RoleId : string
     */

    private String userName;
    private String adminToken;
    private String timestamp;
    private String RoleId;

    public GetUserInfo() {
    }

    public GetUserInfo(String userName, String adminToken, String timestamp, String roleId) {
        this.userName = userName;
        this.adminToken = adminToken;
        this.timestamp = timestamp;
        RoleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String RoleId) {
        this.RoleId = RoleId;
    }
}
