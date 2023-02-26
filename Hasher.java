/**
The purpose of the Hasher interface is to standardize the classes that will be
producing hashes. Actual functionality of each method varies.
@author Press, Joseph
*/
public interface Hasher {
  public void   init(char[] key) throws Throwable;
  public String getAlgName() throws Throwable;
  public String hash(char[] password) throws Throwable;
}
