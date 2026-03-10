package ru.yandex.kholodov;

import ru.yandex.kholodov.parcels.Parcel;
import ru.yandex.kholodov.parcels.box.ParcelBox;
import ru.yandex.kholodov.parcels.interfaces.Trackable;
import ru.yandex.kholodov.parcels.typeParcels.FragileParcel;
import ru.yandex.kholodov.parcels.typeParcels.PerishableParcel;
import ru.yandex.kholodov.parcels.typeParcels.StandardParcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardBox;
    private static ParcelBox<FragileParcel> fragileBox;
    private static ParcelBox<PerishableParcel> perishableBox;

    public static void main(String[] args) {
        standardBox = new ParcelBox<>(new ArrayList<>(), 10);
        fragileBox = new ParcelBox<>(new ArrayList<>(), 5);
        perishableBox = new ParcelBox<>(new ArrayList<>(), 3);

        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    allReportStatus();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }



    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже
    private static void allReportStatus() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет отслеживаемых посылок.");
            return;
        }

        for (Trackable parcel : trackableParcels) {
            System.out.println("Введите локацию: ");
            String newLocation = scanner.nextLine();

            parcel.reportStatus(newLocation);
        }

    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите вес (в кг):");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес доставки:");
        String address = scanner.nextLine();
        System.out.println("Введите день отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        Parcel parcel;
        switch (type) {
            case 1:
                parcel = new StandardParcel(description, weight, address, sendDay);
                standardBox.addParcel((StandardParcel) parcel);
                break;
            case 2:
                parcel = new FragileParcel(description, weight, address, sendDay);
                trackableParcels.add((Trackable) parcel);
                fragileBox.addParcel((FragileParcel) parcel);
                break;
            case 3:
                System.out.println("Введите срок годности (количество дней):");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                parcel = new PerishableParcel(description, weight, address, sendDay, timeToLive);
                perishableBox.addParcel((PerishableParcel) parcel);
                break;
            default:
                System.out.println("Неверный тип. Посылка не добавлена.");
                return;
        }
        allParcels.add(parcel);
        System.out.println("Посылка \"" + description + "\" добавлена в список.");
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправки.");
            return;
        }
        for (Parcel p : allParcels) {
            p.packageItem();
            p.deliver();
        }
        allParcels.clear();
        trackableParcels.clear();
        System.out.println("Все посылки успешно отправлены.");
    }

    private static void calculateCosts() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для расчёта стоимости.");
            return;
        }
        double total = 0;
        for (Parcel p : allParcels) {
            total += p.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость доставки всех посылок: " + total);
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки: 1 — стандартная, 2 — хрупкая, 3 — скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        List<? extends Parcel> parcels = null;
        String boxType = "";
        switch (type) {
            case 1:
                parcels = standardBox.getAllParcels();
                boxType = "стандартной";
                break;
            case 2:
                parcels = fragileBox.getAllParcels();
                boxType = "хрупкой";
                break;
            case 3:
                parcels = perishableBox.getAllParcels();
                boxType = "скоропортящейся";
                break;
            default:
                System.out.println("Неверный тип коробки.");
                return;
        }

        if (parcels.isEmpty()) {
            System.out.println("В " + boxType + " коробке нет посылок.");
        } else {
            System.out.println("Содержимое " + boxType + " коробки:");
            for (Parcel p : parcels) {
                System.out.println(" - " + p.getDescription() + " (вес: " + p.getWeight() + ")");
            }
        }
    }
}


