package br.com.acaipaideguaweb.util;

public class NullUtil {
	
	public static boolean isNullOrBlank(String s){
        System.out.println("num "+s);
        if (s == null || s.isEmpty()){
            System.out.println("aq null");
        }
        return (s == null || s.trim().isEmpty());
    }

}
