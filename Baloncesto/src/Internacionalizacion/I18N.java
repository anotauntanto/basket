/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Internacionalizacion;

/**
 *
 * @author inftel06
 */
public class I18N {
     private static final String BUNDLE_NAME=I18N.class.getPackage().getName()+".messages";

    public static String getBUNDLE_NAME() {
        return BUNDLE_NAME;
    }
     
      
    // Locale locale=new Locale("en","GB");
    //  ResourceBundle rs=ResourceBundle.getBundle(BUNDLE_NAME,locale);
      

}
