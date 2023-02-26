/**
The purpose of the hashVigenere class is to extend the Vigenere class to increase
the functionality by adding a hashing option.
@author Press, Joseph
*/
public class HashVigenere extends Vigenere implements Hasher {

  private String x = "GO_NAVY_2018^mid"; // store the initialization vector

/**
getAlgName() simply returns a String representing the name of the algorithm
@return String representing the name of the algorithm
*/
public String getAlgName() { return "shift+vigenere"; }

/**
The purpose of the hash() method is to actually hash the password, using the
help of the parent classes encrypt method.
@param password representing the char[] holding the user input
@return String representing the final hashed password
*/
public String hash(char[] password) throws Throwable
{
  // test password for
  String pswd = new String(password);
  try {
    Encryptor.checkChars(pswd);
  }
  catch(EncryptorException e)
  {
    String str = "error " + e.getIllegal() + " not allowed in key";
    throw new Throwable();
  }


  for(int i = 0; i < 16; i++)
    {
      char c = this.x.charAt(i);
      int k = ((int)c) % 16;
      shiftXLeft(k); // shift the init. vector k times
      x = encrypt(x);
    }
    return x;
  }

/**
This private helper method is responsible for shifting String x left by k times.
It supports the hash() method.
@param k representing the number of times that hash() is going to shift the init.
vector by.
*/
  private void shiftXLeft(int k)
  {
    for(int i = 0; i < k; i++)
    {
      char first = x.charAt(0);
      x = x.substring(1);
      x = x + first;
    }

  }

}
