package ru.yandex.kholodov.parcels;

public abstract class Parcel {

    protected final static int COST_STANDARD = 2;
    protected final static int COST_PERISHABLE = 3;
    protected final static int COST_FRAGILE = 4;


    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public int getWeight() {
        return weight;
    }

    public int getSendDay() {
        return sendDay;
    }

    public String getDescription() {
        return description;
    }

    public void packageItem() {
        System.out.println("Посылка " + description + " упакована");
    }

    public  void deliver() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public  int calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    protected abstract int getBaseCost();

}
