package ru.yandex.kholodov.parcels.typeParcels;

import ru.yandex.kholodov.parcels.Parcel;

public class StandardParcel extends Parcel {

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return COST_STANDARD;
    }
}
