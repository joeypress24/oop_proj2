import java.util.*;
import java.io.*;

/**
@author Press, Joseph
*/
public class Vault {
  public static void main(String[] args)
  {
    ArrayList<Hasher> E = new ArrayList<Hasher>();
  //  E.add(new Clear());
  //  E.add(new Caesar());
  //  E.add(new Vigenere());
    E.add(new HashClear());
    E.add(new HashCaesar());
    E.add(new HashVigenere());

    // open a scanner with the filename
    String fname = "";
    if(args.length > 0 && !args[0].equals("-au"))
    {
      fname = args[0];
    }
    else if(args.length > 0 && args[0].equals("-au"))
    {
      // add a user
      fname = args[1];

      PrintWriter pw = null;
      try {
        pw = new PrintWriter(new BufferedWriter(new FileWriter(fname, true)));
      } catch (FileNotFoundException fnfe) {
        fnfe.printStackTrace();
      }
      catch(IOException e)
      {
      }

      // Do whatever you need to do
      System.out.print("username: ");
      String username = System.console().readLine();

      System.out.print("password: ");
      char[] password = System.console().readPassword();

      System.out.print("Hash Algorithm: ");
      String hashAlg = System.console().readLine();
      // hash the password to get it stored
      String pswd = "";
      try {
        for( Hasher i : E)
        {
          if(i.getAlgName().equals(hashAlg))
          {
          //  System.out.println("Hashing alg: " + i.getAlgName());
            pswd = i.hash(password);
          }
        }
      } catch(IndexOutOfBoundsException e) {
        throw new NoSuchElementException("Unknown algorithm '"); //+ U.get(i).getEncalg() +"'.");
      }
      catch(Throwable e)
      {
        // do nothing to allow program to continue
      }

      pw.println("user " + username + " " + hashAlg + " " + pswd);

      if (pw != null) pw.close();
      System.exit(0);
    }

    Scanner sc = null; // create a scanner object
    Scanner scan = null;
    try {
      sc = new Scanner(new FileReader(fname));
      scan = new Scanner(new FileReader(fname));
    } catch(IOException e) {
      // exit gracefully
      System.out.println("Error! File '" + fname + "' could not be opened.");
      System.exit(0);
    }

    ArrayList<User> U = new ArrayList<User>();

    // check if file has the right amount of contents

    try{
      while(scan.hasNext())
      {
        scan.next();
        scan.next();
        scan.next();
        scan.next();
      }
    }
    catch(Exception e)
    {
      System.out.println("Error! File '" + fname + "' improperly formatted.");
      System.exit(0);
    }

    //read in user input
    System.out.print("username: ");
    String username = System.console().readLine();

    System.out.print("password: ");
    char[] password = System.console().readPassword();

    int inc = 0;
      try {
      while(sc.hasNext())
      {
        U.add(User.read(sc, fname));
        inc++;
      }
      }
    catch(NoSuchElementException e) // issue happened reading in file contents
    {
      //exit gracefully
      System.out.println("Error! File '" + fname + "' improperly formatted.");
      System.exit(0);
    }

    // initialize the keys in each Encryptor parent class
    try{
      for( Hasher h : E )
      {
        h.init(password);
      }
    }
    catch(Throwable e)
    {
      //System.out.println("TEST");
    }

    // test each combination of hashers to see if any are correct
    boolean access = false; // initially assume user has no access
    try {
      for( Hasher i : E)
      {
        for( User j : U)
        {
          if(j.getEncalg().equals(i.getAlgName()))
          {
          //  System.out.println("alg: " + j.getEncalg());
            if( j.testPassword(i.hash(password)) )
              access = true;
          }
        }
      }
    } catch(IndexOutOfBoundsException e) {
      throw new NoSuchElementException("Unknown algorithm '"); //+ U.get(i).getEncalg() +"'.");
    }
    catch(Throwable e)
    {
      // do nothing to allow program to continue
    }

    if(access)
    {
      System.out.println("Access granted!");
    }
    else
    {
      System.out.println("Access denied!");
      System.exit(0);
    }

    String cmd;
    while(true)
    {
      System.out.print("> ");
      cmd = System.console().readLine();

      if(cmd.equals("quit"))
        break;
    }

  }
}
