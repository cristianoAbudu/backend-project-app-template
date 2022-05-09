package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private String systemName;
    @ManyToOne
    private DeviceType type;

    public Device(){}
    public Device(Long id, String systemName, DeviceType type) {
        this.id = id;
        this.systemName = systemName;
        this.type = type;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getSystemName() {
        return systemName;
    }

    public void setSystemName(java.lang.String systemName) {
        this.systemName = systemName;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }
}
