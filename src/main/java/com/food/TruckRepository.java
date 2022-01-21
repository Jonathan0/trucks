package com.food;

import org.springframework.data.jpa.repository.JpaRepository;

interface TruckRepository extends JpaRepository<Truck, Long> {
}