package com.usa.doctorsapp.repository.crudrepository;

import com.usa.doctorsapp.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
}
