package com.food;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}