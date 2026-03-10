package ru.yandex.kholodov.parcels.box;

import ru.yandex.kholodov.parcels.Parcel;

import java.util.List;

public class ParcelBox<T extends Parcel> {
private final List<T> listOfParcel;
private final int maxWeight;

    public ParcelBox(List<T> listOfParcel, int maxWeight) {
        this.listOfParcel = listOfParcel;
        this.maxWeight = maxWeight;
    }

    public List<T> getAllParcels() {
        return listOfParcel;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public  void  addParcel(T parsel) {
        if (parsel.getWeight() <= maxWeight) {
            listOfParcel.add(parsel);
            return;
        }
        System.out.println("Недопустимый вес посылки, нельзя упаковать в коробку");
    }


}
