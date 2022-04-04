package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import data.LoginInfo;

public class LoginDao {
	
	private static String realuser = "admin";
	private static String realpassword = "admin21m";   
    
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
