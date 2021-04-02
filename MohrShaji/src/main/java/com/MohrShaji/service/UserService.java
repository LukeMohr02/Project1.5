/*

package com.MohrShaji.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;

import com.MohrShaji.Application.ManageUser;
import com.MohrShaji.model.User;

public class UserService {
	private ManageUser mu;
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	
	public UserService() {
		mu = new ManageUser();
	}
	
	public List<User> fetchAllUsers() {
		return mu.listUsers();
	}
	
	public User getUserById(int id) {
		return mu.getByUserId(id);
	}
	
	public User getUserByUsername(String username) {
		User u = mu.getByUsername(username);
		if (u != null) {
			u.setPassword(""); //Remove the hashed password for security reasons.
			LOGGER.trace("Password info removed from username " + username + ".");
			return u;
		}
		return null;
	}
	
	public User getUserByLogin(String user, String pass) {
		User u = mu.getByUsername(user);
		
		if(u != null) {
		String full = user + pass + "salt";
			try {
				//Let MessageDigest know that we want to hash using MD5
				MessageDigest m = MessageDigest.getInstance("md5");
				//Convert our full string to a byte array.
				byte[] messageDigest = m.digest(full.getBytes());
				//Convert our byte array into a signum representation of its former self.
				BigInteger n = new BigInteger(1, messageDigest);
				
				//Convert the whole array into a hexadecimal string.
				String hash = n.toString(16);
				while(hash.length() < 32) {
					hash = "0" + hash;
				}
				
				if(u.getPassword().equals(hash)) {
					//System.out.println("Hash matched!");
					return u;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
*/

