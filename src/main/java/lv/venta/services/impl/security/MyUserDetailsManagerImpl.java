package lv.venta.services.impl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import lv.venta.models.security.MyUser;
import lv.venta.repo.security.IMyUserRepo;
import lv.venta.utils.MyUserDetails;

public class MyUserDetailsManagerImpl implements UserDetailsManager{
	
	@Autowired
	private IMyUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepo.findByUsername(username);
		if (user != null) { // ir atrasts datubāzē
			MyUserDetails details = new MyUserDetails(user);
			return details;
		}
		else {
			throw new UsernameNotFoundException(username + " nav atrasts datubāzē!");
		}
	}

	@Override
	public void createUser(UserDetails user) {
		MyUserDetails myDetails = (MyUserDetails) user;
		MyUser myUser = myDetails.getUser();
		userRepo.save(myUser);
		
	}

	@Override
	public void updateUser(UserDetails user) {
		MyUserDetails myDetails = (MyUserDetails) user;
		MyUser myUser = myDetails.getUser();
		userRepo.save(myUser);
		
	}

	@Override
	public void deleteUser(String username) {
		MyUser user = userRepo.findByUsername(username);
		if (user != null) { //eksistē tāds lietotājs pēc norādītā username 
			userRepo.delete(user);
		}
		
	}

	//TODO implementēt kad tas ir nepieciešams 
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		
		
	}

	@Override
	public boolean userExists(String username) {
		MyUser user = userRepo.findByUsername(username);
		if (user != null) { //eksistē tāds lietotājs pēc norādītā username 
			return true;
		}
		else {
			return false;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
