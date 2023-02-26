/**
The purpose of the Vigenere class is to implement the Encryptor interface to add
the functionality of a Vignere Cipher.
@author Press, Joseph
*/
public class Vigenere implements Encryptor {
  private String key;
  private char shift;

/**
The getAlgName method is responsible for simply returning the phrase "vigenere"
so that the main method can know the name of the algorithm being used.
@return String representing the name of the algorithm.
*/
  public String getAlgName()
  {
    return "vigenere";
  }

/**
The init method simply initializes the key private data field.
@param key representing the char array holding the encryption key.
*/
  public void init(char[] key)
  {
    // screen for bad characters
    String test = new String(key); // convert char* to string to make easier
    try {
      Encryptor.checkChars(test); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in key";
      throw new RuntimeException(str);
    //  System.out.println("error " + e.getIllegal() + " not allowed in key");
    }

    this.key = new String(key);
  }

/**
The purpose of the encrypt method is to take in plain text and convert it into
cipher text using the method specified in the course website.
@param plain representing plaintext
@return String representing resultant ciphertext
*/
  public String encrypt(String plain)
  {
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
    for(int i = 0, j = 0; i < plain.length(); i++)
    {
      char pc = plain.charAt(i); // current index of character of plain
      char sc = key.charAt(j); // current index of character of key
      j++;
      if(j == key.length())
        j = 0;

      char k = (char)((int)sc - 42);
      char p = (char)((int)pc - 42);
      char c = (char)(((int)p + (int)k)%81);
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
  public String decrypt(String cipher)
  {
    // screen for bad characters
    try {
      Encryptor.checkChars(cipher); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in cipher text";
      throw new RuntimeException(str);
      // System.out.println("error " + e.getIllegal() + " not allowed in cipher text");
      // System.exit(0);
    }

    String fin = "";
    for(int i = 0, j = 0; i < cipher.length(); i++)
    {
      char cc = cipher.charAt(i); // current index of character of cipher
      char sc = key.charAt(j); // current index of character of key
      j++;
      if(j == key.length())
        j = 0;

      char k = (char)((int)sc - 42);
      char c = (char)((int)cc - 42);
      char p = (char)(((int)c + (81 - (int)k))%81);
      char pc = (char)(42 + (int)p);
      fin = fin + pc;
    }

    return fin;
  }
}
