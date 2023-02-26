/**
The purpose of the Caesar class is to implement the Encryptor interface to add
the functionality of a Caeser-shift Cipher.
@author Press, Joseph
*/
public class Caesar implements Encryptor{
  private String key; // private data field to store the key
  private char shift; // private data field to store the shift character

/**
The getAlgName method is responsible for simply returning the phrase "caesar"
so that the main method can know the name of the algorithm being used.
@return String representing the name of the algorithm.
*/
  public String getAlgName() { return "caesar"; }

/**
The init method acts like a constructor, saving the key as a private data field.
It also calculates the shift value and sets that private data field as well.
@param key representing the char array holding the encryption key.
*/
  public void init(char[] key) {
    // first, screen for bad characters
    String test = new String(key); // convert char* to string to make easier
    try {
      Encryptor.checkChars(test); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in key";
      throw new RuntimeException(str);
      //System.out.println("error " + e.getIllegal() + " not allowed in key");
    //  System.exit(0);
    }

    this.key = new String(key);
    // determine the shift value
    int sum = 18; // initially 18
    for(int i = 0; i < key.length; i++)
    {
      int temp = (int)this.key.charAt(i) - 42;
      sum += temp;
    }
    sum = sum % 81;
    sum = sum + 42;
    this.shift = (char)sum;
    //System.out.println("KEY: " + this.key);
  }

/**
The purpose of the encrypt method is to take in plain text and convert it into
cipher text using the method specified in the course website.
@param plain representing plaintext
@return String representing resultant ciphertext
*/
  public String encrypt(String plain) {
    // screen for bad characters
    try {
      Encryptor.checkChars(plain); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in plain text";
      throw new RuntimeException(str);
      // System.out.println("error " + e.getIllegal() + " not allowed in plain text");
      // System.exit(0);
    }

    String fin = "";
    for(int i = 0; i < plain.length(); i++)
    {
      char pc = plain.charAt(i);
      char k = (char)((int)this.shift - 42);
      char p = (char)((int)pc - 42);
      char c = (char)((int)(p+k)%81);
      char cc = (char)(42 + (int)c);
      fin = fin + cc;
    }
    return fin;
  }

/**
The purpose of the decrypt method is to take in cipher text and convert it into
plain text using the method specified in the course website.
@param cipher representing cipher text
@return String representing resultant plain text
*/
  public String decrypt(String cipher){
    // screen for bad characters
    try {
      Encryptor.checkChars(cipher); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in cipher text";
      throw new RuntimeException(str);
    }

    String fin = "";
    for(int i = 0; i < cipher.length(); i++)
    {
      char cc = cipher.charAt(i);
      char k = (char)((int)this.shift - 42);
      char c = (char)((int)cc - 42);
      char p = (char)(((int)c + (81 - (int)k)) % 81);
      char pc = (char)(42 + (int)p);
      fin = fin + pc;
    }
    return fin;
  }

}
