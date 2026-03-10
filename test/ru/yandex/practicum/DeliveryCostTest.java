package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.kholodov.parcels.box.ParcelBox;
import ru.yandex.kholodov.parcels.typeParcels.FragileParcel;
import ru.yandex.kholodov.parcels.typeParcels.PerishableParcel;
import ru.yandex.kholodov.parcels.typeParcels.StandardParcel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void testStandardParcelCost() {
        StandardParcel p = new StandardParcel("Книги", 5,
                "ул. Ленина, 1", 10);
        assertEquals(10, p.calculateDeliveryCost());
    }

    @Test
    public void testFragileParcelCost() {
        FragileParcel p = new FragileParcel("Стекло", 3,
                "ул. Пушкина, 5", 12);
        assertEquals(12, p.calculateDeliveryCost());
    }

    @Test
    public void testPerishableParcelCost() {
        PerishableParcel p = new PerishableParcel("Мясо", 2,
                "ул. Тверская, 10", 7, 5);
        assertEquals(6, p.calculateDeliveryCost());
    }

    @Test
    public void testIsExpiredNotExpired() {
        PerishableParcel p = new PerishableParcel("Молоко", 1,
                "ул. Мира, 2", 5, 3);
        assertFalse(p.isExpired(7));
    }

    @Test
    public void testIsExpiredExactlyOnExpirationDay() {
        PerishableParcel p = new PerishableParcel("Молоко", 1,
                "ул. Мира, 2", 5, 3);
        assertFalse(p.isExpired(8));
    }

    @Test
    public void testIsExpiredExpired() {
        PerishableParcel p = new PerishableParcel("Молоко", 1,
                "ул. Мира, 2", 5, 3);
        assertTrue(p.isExpired(9));
    }

    @Test
    public void testAddParcelWithinWeightLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(new ArrayList<>(), 10);
        StandardParcel p = new StandardParcel("Книги", 8, "ул. Ленина", 1);
        int before = box.getAllParcels().size();
        box.addParcel(p);
        int after = box.getAllParcels().size();
        assertEquals(before + 1, after, "Посылка должна быть добавлена");
    }

    @Test
    public void testAddParcelAtWeightLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(new ArrayList<>(), 10);
        StandardParcel p = new StandardParcel("Книги", 10, "ул. Ленина", 1);
        int before = box.getAllParcels().size();
        box.addParcel(p);
        int after = box.getAllParcels().size();
        assertEquals(before + 1, after, "Посылка с весом равным лимиту должна добавляться");
    }

    @Test
    public void testAddParcelExceedingWeightLimit() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(new ArrayList<>(), 10);
        StandardParcel p = new StandardParcel("Книги", 12, "ул. Ленина", 1);
        int before = box.getAllParcels().size();
        box.addParcel(p);
        int after = box.getAllParcels().size();
        assertEquals(before, after, "Посылка с превышением веса не должна добавляться");
    }

}
