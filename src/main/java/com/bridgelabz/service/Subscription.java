package com.bridgelabz.service;

public enum Subscription {
    ABCD(2, 3), XYZ(3, 4);
    int a;
    int b;

    Subscription(int a, int b) {
        this.a = a;
        this.b = b;
    }
}