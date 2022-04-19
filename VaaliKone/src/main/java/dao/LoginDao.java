package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import data.LoginInfo;

/**
 * Date: 19.04.2022
 * This class contains password and username checking and crypting
 * @author Ari-Jussi Ahonen
 * @author Arsi Arola
 * @author Oskari Ahoniemi
 * @version 1.0
 * 
 *
 */
public class LoginDao {
	
	/**
	 * String value for real user name
	 */
	private static String realuser = "admin";
	/**
	 * String value for real password
	 */
	private static String realpassword = "admin21m";   
    
	/**
	 * This method checks if the given user name and password are equal to the real user name and password. It returns a boolean value
	 * whether the operation was successful or not 
	 * @param logininfo LoginInfo -object
	 * @return returning a boolean value whether the comparison was equal or not
	 */
	public static Boolean check(LoginInfo logininfo)
	{
		String user = logininfo.getUsername();
		String pass = logininfo.getPasswword();
		
		if (crypt(user).equals(crypt(realuser)) && crypt(pass).equals(crypt(realpassword)))
	    {
	    	return true;
	    }
	    else 
	    {
	    	return false;
	    }
	}
	
	/**
	 * This method crypts a given string using the MD5-crypting and returns the crypted hash of the string
	 * @param str a String value for given string
	 * @return returning the given string as a hash
	 */
	public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("MD5");

            digester.update(str.getBytes());
            byte[] hash = digester.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
