import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileReader extends File
{
  private String     filename;
  private String     userFirstName;
  private String     isMale = "false"; // Reads as String, will convert to boolean
  private int        userAge;
  private int        userHeightInches;
  private int        userWeight;
  private double     userBMR;
  private int        userLifestyle;
  private String     userID;
  private String     userPassword;
  private DateRecord userDateJoined;
  private ArrayList<UserAccount> UserCollection;

  FileReader(String _filename, ArrayList<UserAccount> _UserCollection)
  {
    super(_filename);
    filename = _filename;
    UserCollection = _UserCollection;
  }

  public void initialDataLoad() throws FileNotFoundException
  {
    Scanner databaselineReader = new Scanner(new File(filename));
    long tempReaderLong = 0;

    while (databaselineReader.hasNextLine())
    {
      userFirstName = databaselineReader.nextLine();
      if (databaselineReader.nextLine().equals("true"))
        isMale = "true";
      userAge          = Integer.parseInt(databaselineReader.nextLine());
      userHeightInches = Integer.parseInt(databaselineReader.nextLine());
      userWeight       = Integer.parseInt(databaselineReader.nextLine());
      userBMR          = Double.parseDouble(databaselineReader.nextLine());
      userLifestyle    = Integer.parseInt(databaselineReader.nextLine());
      userID           = databaselineReader.nextLine();
      userPassword     = databaselineReader.nextLine();
      tempReaderLong   = Long.parseLong(databaselineReader.nextLine());

      userDateJoined = new DateRecord(tempReaderLong);
      userDateJoined.setDayOfWeek(databaselineReader.nextLine());
      userDateJoined.setUserMonth(databaselineReader.nextLine());
      userDateJoined.setUserDay(databaselineReader.nextLine());
      userDateJoined.setUserYear(databaselineReader.nextLine());
      userDateJoined.setLastLogin(Long.parseLong(databaselineReader.nextLine()));

      // Instantiate objects of class UserAccount and append to arraylist
      UserAccount completeUserRecord = new UserAccount(userFirstName, isMale, userAge, userHeightInches, userWeight, userBMR, userLifestyle, userID, userPassword, userDateJoined);
      UserCollection.add(completeUserRecord);
    }
    databaselineReader.close();
  }  // End void initialDataLoad()

  public void updateProfileDatabase() throws FileNotFoundException
  {
    PrintWriter updateWriter = new PrintWriter(filename);
    for (int i = 0; i < UserCollection.size(); ++i)
    {
      updateWriter.println(UserCollection.get(i).getName());
      updateWriter.println(UserCollection.get(i).getGender());
      updateWriter.println(UserCollection.get(i).getAge());
      updateWriter.println(UserCollection.get(i).getHeightInches());
      updateWriter.println(UserCollection.get(i).getWeight());
      updateWriter.println(UserCollection.get(i).getBMR());
      updateWriter.println(UserCollection.get(i).getLifestyle());
      updateWriter.println(UserCollection.get(i).getUserID());
      updateWriter.println(UserCollection.get(i).getPassword());

      updateWriter.println(UserCollection.get(i).getJoined());
      updateWriter.println(UserCollection.get(i).getJoinedDayOfWeek());
      updateWriter.println(UserCollection.get(i).getJoinedMonth());
      updateWriter.println(UserCollection.get(i).getJoinedDay());
      updateWriter.println(UserCollection.get(i).getJoinedYear());
      updateWriter.println(UserCollection.get(i).getLastLogin());
    }
    updateWriter.close();
  }        // End addNewProfile(...)
  public void clearLocalMemory() throws FileNotFoundException
  {
    UserCollection.clear();
    initialDataLoad();
  }
  public UserAccount getReloadedProfile(int _userProfileIndex)
  {
    return UserCollection.get(_userProfileIndex);
  }

}  // End class FileReader()
