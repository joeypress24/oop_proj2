/**
The Encryptor interface allows for added functionality because classes that extend
the interface must define the listed functions.
@author Press, Joseph
*/
public interface Encryptor {
  public String getAlgName();
  public void   init(char[] key);
  public String encrypt(String plain);
  public String decrypt(String cipher);

  // note, this must be default but doing so causes an error so idk
/**
The purpose of the checkChars() method is to check if the characters in a given
String of text are illegal.
@param text representing the text that is being checked for illegal characters.
*/
  public static void checkChars(String text) throws EncryptorException
  {
    // "screen" for errors in text
    for(int i = 0; i < text.length(); i++)
    {
      if(text.charAt(i) < 42 || text.charAt(i) > 122)
      {
        String exc = "";
        throw new EncryptorException(exc, text.charAt(i));
      }
    }

  }

}
