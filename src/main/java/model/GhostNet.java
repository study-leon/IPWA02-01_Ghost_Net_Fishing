package com.ghostnet.ghostnet.model;

import jakarta.persistence.*;

import com.ghostnet.ghostnet.model.Person;

@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gps;

    private String size;

    private String status;

    // NEU: Bezug zur bergenden Person
    @ManyToOne
    private Person rescuer;

    public Long getId() {
        return id;
    }

    public String getGps() {
        return gps;
    }
    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // NEU: Getter und Setter für rescuer
    public Person getRescuer() {
        return rescuer;
    }

    public void setRescuer(Person rescuer) {
        this.rescuer = rescuer;
    }
}
