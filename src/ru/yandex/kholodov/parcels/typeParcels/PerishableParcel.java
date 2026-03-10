package ru.yandex.kholodov.parcels.typeParcels;

import ru.yandex.kholodov.parcels.Parcel;

public class PerishableParcel extends Parcel {

private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    @Override
    protected int getBaseCost() {
        return COST_PERISHABLE;
    }

    public boolean isExpired(int currentDay) {
        return timeToLive + getSendDay() < currentDay;
    }

}
