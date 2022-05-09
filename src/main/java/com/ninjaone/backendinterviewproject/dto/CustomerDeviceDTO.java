package com.ninjaone.backendinterviewproject.dto;

import java.util.List;

public class CustomerDeviceDTO {
    private Long deviceId;

    private List<Long> serviceIdList;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public List<Long> getServiceIdList() {
        return serviceIdList;
    }

    public void setServiceIdList(List<Long> serviceIdList) {
        this.serviceIdList = serviceIdList;
    }

}
