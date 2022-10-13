package com.usa.doctorsapp.service;

import com.usa.doctorsapp.model.Reservation;
import com.usa.doctorsapp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getById(Integer idReservation) {
        return reservationRepository.getById(idReservation);
    }

    public Reservation save(Reservation reservation) {
        if(reservation.getIdReservation()==null){
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> optionalReservation=reservationRepository.getById(reservation.getIdReservation());
            if(optionalReservation.isEmpty()){
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> optionalReservation=reservationRepository.getById(reservation.getIdReservation());
            if(!optionalReservation.isEmpty()){
                if(reservation.getStartDate()!=null){
                    optionalReservation.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    optionalReservation.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    optionalReservation.get().setStatus(reservation.getStatus());
                    optionalReservation.get().setDoctor(reservation.getDoctor());
                    optionalReservation.get().setClient(reservation.getClient());
                }
                reservationRepository.save(optionalReservation.get());
                return optionalReservation.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }

    public boolean delete(Integer idReservation){
        Boolean aBoolean=getById(idReservation).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
