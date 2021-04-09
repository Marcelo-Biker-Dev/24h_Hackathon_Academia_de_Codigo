package org.academiadecodigo.rememberthename.persistence.dao.jpa;

import org.academiadecodigo.rememberthename.persistence.dao.ReservationDao;
import org.academiadecodigo.rememberthename.persistence.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class JpaReservationDao extends GenericJpaDao<Reservation> implements ReservationDao {

    public JpaReservationDao() {
        super(Reservation.class);
    }
}
