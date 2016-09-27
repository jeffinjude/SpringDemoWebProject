package com.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.springdemo.jpamanager.JpaManager;
import com.springdemo.models.UserDetails;

public class UserDetailsDao {
	
	UserDetails userDetails;
	
	public UserDetails getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}


	public void insertData(String userName, String phoneNumber)
	{
		EntityManager entityManager = JpaManager.getEntityManagerFactory().createEntityManager();
		
		entityManager.getTransaction().begin();
		
		userDetails.setUserName(userName);
		userDetails.setPhoneNumber(phoneNumber);
		
		entityManager.merge(userDetails);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<UserDetails> fetchAllUserDetails()
	{
		EntityManager entityManager = JpaManager.getEntityManagerFactory().createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Query queryUsers = entityManager.createNamedQuery("UserDetails.getAllUsers");
		List<UserDetails> fetchedUserDetails = queryUsers.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();
		
		return fetchedUserDetails;
	}
}
