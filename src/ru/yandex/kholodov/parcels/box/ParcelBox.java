package ru.yandex.kholodov.parcels.box;

import ru.yandex.kholodov.parcels.Parcel;

import java.util.List;

public class ParcelBox<T extends Parcel> {
private final List<T> listOfParcel;
private final int maxWeight;
private int remainingWeight;

    public ParcelBox(List<T> listOfParcel, int maxWeight) {
        this.listOfParcel = listOfParcel;
        this.maxWeight = maxWeight;
        this.remainingWeight = maxWeight;
    }

    public List<T> getAllParcels() {
        return listOfParcel;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void clear() {
        listOfParcel.clear();
        remainingWeight = maxWeight;
    }

    public  void  addParcel(T parsel) {
        if (parsel.getWeight() <= remainingWeight) {
            listOfParcel.add(parsel);
            remainingWeight -= parsel.getWeight();
            return;
        }
        System.out.println("Недопустимый вес посылки, нельзя упаковать в коробку");
    }


}
