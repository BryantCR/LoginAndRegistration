package com.loginandregistration.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.loginandregistration.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	/*List<User> selectUserByEmail( String email );*/
	
	@Modifying
	@Transactional
	@Query( "SELECT u FROM User u WHERE email = ?1" )
	List<User> selectUserByEmail( String email );
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO users(email, password) " +
	"VALUES(?1, ?2)", nativeQuery=true)
	Integer insertNewUser( String email, String encryptedpassword);
	
}
