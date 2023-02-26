/**
The purpose of the Clear class is to implement the Encryptor interface to add
the functionality of a clear encryption, which is no encryption.
@author Press, Joseph
*/
public class Clear implements Encryptor {

/**
The getAlgName method is responsible for simply returning the phrase "clear"
so that the main method can know the name of the algorithm being used.
@return String representing the name of the algorithm.
*/
  public String getAlgName() { return "clear"; }

/**
The init method doesn't need to do anything because there is nothing to initialize
for a clear encryption. However, there is an error check to ensure that the key
has no bad characters.
@param key representing the char array holding the encryption key.
*/
  public void   init(char[] key) {
    String test = new String(key);
    try {
      Encryptor.checkChars(test); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in cipher text";
      throw new RuntimeException(str);
    }
  }

/**
The encrypt method doesn't need to do anything because there is nothing to change
for a clear encryption. However, there is an error check to ensure that the plain
text has no bad characters.
@param plain representing the String holding the plain text.
*/
  public String encrypt(String plain) {
    try {
      Encryptor.checkChars(plain); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in plain text";
      throw new RuntimeException(str);
    }
    return plain;
  }

/**
The decrypt method doesn't need to do anything because there is nothing to change
for a clear encryption. However, there is an error check to ensure that the cipher
text has no bad characters.
@param cipher representing the String holding the cipher text.
*/
  public String decrypt(String cipher){
    try {
      Encryptor.checkChars(cipher); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in cipher text";
      throw new RuntimeException(str);
    }
    return cipher;
  }
}
