package com.example.parkinGenie.Entities;

public class Tariff extends CarPark {

    private int carParkId;
    private int orderId;
    private String tariffInfo;

    public Tariff(int carParkId, String name, String website, String address, String phone, String gps,
                  int tot_spaces, int free_spaces, String height_restrictions, String payment_methods,
                  int orderId, String tariffInfo) {
        super(carParkId, name, website, address, phone, gps, tot_spaces, free_spaces, height_restrictions, payment_methods);
        this.carParkId = carParkId;
        this.orderId = orderId;
        this.tariffInfo = tariffInfo;
    }

    public Tariff(int carParkId, int orderId, String tariffInfo) {
        this.carParkId = carParkId;
        this.orderId = orderId;
        this.tariffInfo = tariffInfo;
    }

    public Tariff(){

    }

    @Override
    public int getCarParkId() {
        return carParkId;
    }

    @Override
    public void setCarParkId(int carParkId) {
        this.carParkId = carParkId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTariffInfo() {
        return tariffInfo;
    }

    public void setTariffInfo(String tariffInfo) {
        this.tariffInfo = tariffInfo;
    }
}
