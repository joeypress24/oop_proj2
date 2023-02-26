/**
The purpose of the hashClear class is to extend the Clear class to increase
the functionality by adding a hashing option.
@author Press, Joseph
*/
public class HashClear extends Clear implements Hasher {

/**
getAlgName() simply returns a String representing the name of the algorithm
@return String representing the name of the algorithm
*/
  public String getAlgName() { return "clear"; }

/**
The purpose of the hash() method is to actually hash the password. In the case
of "clear", a hash is simply filling in the empty space with "x" characters.
@param password representing the char[] holding the user input
@return String representing the final hashed password
*/
  public String hash(char[] password) throws Throwable
  {
    // screen for bad characters
    String pswd = new String(password); // convert char* to string to make easier
    try {
      Encryptor.checkChars(pswd); // check for bad characters
    }
    catch(EncryptorException e)
    {
      String str = "error " + e.getIllegal() + " not allowed in key";
      throw new Throwable(str);
      // System.out.println("error " + e.getIllegal() + " not allowed in key");
      // System.exit(0);
    }

    for(int i = pswd.length(); i < 16; i++)
    {
      pswd = pswd + "x"; // add an "x" for each time until 16 chars in string
    }
    return pswd;
  }

}
