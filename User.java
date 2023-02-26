import java.util.*;
import java.io.*;

/**
The purpose of the User class is to store a single User object.
@author Press, Joseph
*/
public class User {
  private String username;
  private String encalg;
  private String password;
  private String passwordAfterHash;
  // String[] array with all of the alg names

/**
The User constructor is responsible for initalizing an instance of a User.
@param uname representing the username of the user.
@param encalg representing the encryption algorithm.
@param password representing the password that is inputed into the program in main.
*/
  public User(String uname, String encalg, String password)
  {
    // initialize data fields
    this.username = uname;
    this.encalg = encalg;
    this.password = password;
    // hash or encrypt password with encalg and store

  }

/**
The read() method is responsible for taking in a scanner object and storing the
important elements in data fields for the User class object.
@param sc representing the Scanner class object from which the data will be read.
@param fname representing the name of the file that is being read from for error
checking.
@return User representing the filled user class object.
*/
  public static User read(Scanner sc, String fname)
  {
    String trash, username = "", encalg = "", password = "";
    User usr;
    try{
       trash = sc.next();
       username = sc.next();
       encalg = sc.next();
       password = sc.next();
    }
    catch(NoSuchElementException e)
    {
      //exit gracefully
      System.out.println("Error! File '" + fname + "' improperly formatted.");
      System.exit(0);
    }
    // make a new static method that checks the algname and throws an error if not allowed

    usr = new User(username, encalg, password);

    try{
      testForBadAlg(encalg);
    }
    catch(IndexOutOfBoundsException e)
    {
      System.out.println("Error! Hash algorithm '" + encalg + "' not supported.");
      System.exit(0);
    }
  //  testForBadAlg(encalg);

    return usr;
  }

/**
The purpose of the testForBadChars method is to take in the name of an algorithm
and throw an exception if it is not expected.
@param alg representing an algorithm name.
*/
  public static void testForBadAlg(String alg) throws IndexOutOfBoundsException {
    // check if the algorithm exists...
    // make a temporary ArrayList to get the respective encryption alg names
    ArrayList<Hasher> E = new ArrayList<Hasher>();
    E.add(new HashClear());
    E.add(new HashCaesar());
    E.add(new HashVigenere());

    int i = -1;
    try {
      while( !E.get(++i).getAlgName().equals(alg) ) ;
    } catch(IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("Unknown algorithm '" + alg +"'.");
    }
    catch(Throwable e)
    {
      // do nothing
    }

  }

/**
getEncalg() is a helper method to allow other classes to peek at an encryption
algorithm name.
@return String representing the name of the stored algorithm.
*/
  public String getEncalg()
  {
    return this.encalg;
  }

/**
getUsername() is a helper method to allow other classes to peek at a username.
@return String representing the name of the stored Username.
*/
  public String getUsername()
  {
    return this.username;
  }

/**
testPassword() is a helper method that essentailly tests if a password is equal
to an instance of a hash.
@return boolean representing whether or not the password is equal to the hash.
@param hash representing the hashed value of the plaintext.
*/
  public boolean testPassword(String hash)
  {
    if(hash.equals(this.password))
      return true;
    else
      return false;
  }
}
