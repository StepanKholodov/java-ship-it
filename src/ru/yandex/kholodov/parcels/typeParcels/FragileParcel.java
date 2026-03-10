package ru.yandex.kholodov.parcels.typeParcels;

import ru.yandex.kholodov.parcels.Parcel;
import ru.yandex.kholodov.parcels.interfaces.Trackable;

public class FragileParcel extends Parcel implements Trackable {

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + this.getDescription()  + " обёрнута в защитную плёнку");
        super.packageItem();
    }

    @Override
    protected int getBaseCost() {
        return COST_FRAGILE;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + getDescription() +
                " изменила местоположение на " + newLocation);
    }
}
