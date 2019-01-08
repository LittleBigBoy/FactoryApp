package com.zhenhaikj.factoryside.mvp.event;

import com.tencent.mm.opensdk.modelbase.BaseResp;

public class WXpaySuccessEvent {
    private BaseResp resp;

    public WXpaySuccessEvent(BaseResp resp) {
        this.resp = resp;
    }

    public BaseResp getResp() {
        return resp;
    }

    public void setResp(BaseResp resp) {
        this.resp = resp;
    }
}
