package com.example.parkinGenie.Entities;

public class OpeningTimes extends CarPark {

    private int carParkId;
    private String orderId;
    private String dayOfWeek;
    private String openingInfo;

    public OpeningTimes(int carParkId, String name, String website, String address, String phone,
                        String gps, int tot_spaces, int free_spaces, String height_restrictions,
                        String payment_methods, String orderId, String dayOfWeek, String openingInfo) {
        super(carParkId, name, website, address, phone, gps, tot_spaces, free_spaces, height_restrictions, payment_methods);

        this.carParkId = carParkId;
        this.orderId = orderId;
        this.dayOfWeek = dayOfWeek;
        this.openingInfo = openingInfo;

    }

    public OpeningTimes(int carParkId, String orderId, String dayOfWeek, String openingInfo) {
        this.carParkId = carParkId;
        this.orderId = orderId;
        this.dayOfWeek = dayOfWeek;
        this.openingInfo = openingInfo;
    }

    @Override
    public int getCarParkId() {
        return carParkId;
    }

    @Override
    public void setCarParkId(int carParkId) {
        this.carParkId = carParkId;
    }
}
