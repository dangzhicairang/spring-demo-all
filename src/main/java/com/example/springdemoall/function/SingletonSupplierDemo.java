package com.example.springdemoall.function;

import org.junit.jupiter.api.Test;
import org.springframework.util.function.SingletonSupplier;
import org.springframework.util.function.SupplierUtils;

public class SingletonSupplierDemo {

    @Test
    public void test() {

        SingletonSupplier<String> supplier
                = new SingletonSupplier<>(null, () -> "default");
        System.out.println(supplier.get());

        supplier = new SingletonSupplier<>(() -> "dd", () -> "default");
        System.out.println(supplier.get());

        supplier = SingletonSupplier.ofNullable("dd");
        System.out.println(supplier.get());

        supplier = SingletonSupplier.ofNullable(() -> "dd");
        System.out.println(supplier.get());

        System.out.println(SupplierUtils.resolve(supplier));
    }
}
