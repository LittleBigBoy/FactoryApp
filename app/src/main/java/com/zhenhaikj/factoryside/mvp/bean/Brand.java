package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.Collection;

public class Brand implements Serializable {

    /**
     * Id : 6
     * FBrandID : 6
     * UserID : admin
     * FBrandName : 格力
     * IsUse : Y
     * Version : 0
     */

    private String Id;
    private String FBrandID;
    private String UserID;
    private String FBrandName;
    private String IsUse;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFBrandID() {
        return FBrandID;
    }

    public void setFBrandID(String FBrandID) {
        this.FBrandID = FBrandID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getFBrandName() {
        return FBrandName;
    }

    public void setFBrandName(String FBrandName) {
        this.FBrandName = FBrandName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }
}

