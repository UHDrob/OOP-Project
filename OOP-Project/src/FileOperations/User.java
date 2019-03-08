/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileOperations;

/**
 * User.java
 * Created Feb 19 2019
 * Modified: Mar 3, 2019
 * @author Roberto Gomez
 * @version 1
 */
// Feb 19, 2019 Roberto : For the ArrayList Part A
    public class  User{
        public String idArray;
        public String fnameArray;
        public String lnameArray;
        public String titleArray;
        public String phoneNumberArray;
        public String userNameArray;
        public String passWordArray;
        
        public User(String Id, String FName, String LName, String Title, String PhoneNumber, String UserName, String PassWord)
        {
            this.idArray = Id;
            this.fnameArray = FName;
            this.lnameArray = LName;
            this.titleArray = Title;
            this.phoneNumberArray = PhoneNumber;
            this.userNameArray = UserName;
            this.passWordArray = PassWord;
            
        }
    }   