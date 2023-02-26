/**
The purpose of the EncryptorException class is to extend RuntimeException to add
functionality of a more specific type of exception.
@author Press, Joseph
*/
public class EncryptorException extends RuntimeException {
  private char illegalChar; // stores the illegal character

/**
The EncryptorException() method is responsible for initializing an EncryptorException
object.
@param s representing a message to be displayed.
@param illegal representing the illegal character to be stored.
*/
  public EncryptorException(String s, char illegal)
  {
    super(s);
    this.illegalChar = illegal;
  }

/**
The getIllegal() method allows for subclasses to access the char that caused the
problem.
@return char representing the problem character.
*/
  public char getIllegal() { return this.illegalChar; }
}
