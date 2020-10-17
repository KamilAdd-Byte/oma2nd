package com.oma.dao;

import com.oma.model.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AddressDAOImplementation implements AddressDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Address> getAllAddresses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Address ", Address.class).getResultList();
    }

    @Override
    public void saveAddress(Address address) {
        Session session = sessionFactory.getCurrentSession();
        session.save(address);
    }

    @Override
    public Address getAddressForId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Address address where address.id = :id", Address.class);
        query.setParameter("id", id);
        return (Address) query.getSingleResult();
    }
}
