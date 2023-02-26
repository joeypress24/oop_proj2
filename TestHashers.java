import java.util.*;
/**
@author Press, Joseph
*/
public class TestHashers {
  public static void main(String[] args) throws Throwable {
    // Create ArrayList of all supported encryptors
    ArrayList<Hasher> E = new ArrayList<Hasher>();
    E.add(new HashClear());
    E.add(new HashCaesar());
    E.add(new HashVigenere());

    // Get alg,psw,msg from user
    System.out.print("algorithm: ");
    String encalg = System.console().readLine();

    System.out.print("password : ");
    char[] password = System.console().readPassword();

    // Find index of encryptor (throw exception if not found)
    int i = -1;
    try {
      while( !E.get(++i).getAlgName().equals(encalg) ) ;
    } catch(IndexOutOfBoundsException e) {
      throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
    }

    // now that we have i (index), initialize the password to do error checking
    E.get(i).init(password);

    // by the time we're here there are no errors in the code
    // display the password read as specified in the instructions
    String str = new String(password); // convert password to string
    System.out.println("password read : " + str);

    // Encrypt, decrypt print sumamry of results
    String hash = E.get(i).hash(password);
    System.out.println("hash computed : " + hash);

  }
}
