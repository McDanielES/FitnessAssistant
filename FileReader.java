import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileReader extends File
{
  private String filename;
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
      UserAccount completeUserRecord = new UserAccount();
      completeUserRecord.setName(databaselineReader.nextLine());
      if (databaselineReader.nextLine().equals("true"))
        completeUserRecord.setGender(true);
      else
        completeUserRecord.setGender(false);
      completeUserRecord.setAge(Integer.parseInt(databaselineReader.nextLine()));
      completeUserRecord.setHeight(Integer.parseInt(databaselineReader.nextLine()));
      completeUserRecord.setWeight(Integer.parseInt(databaselineReader.nextLine()));
      completeUserRecord.setStartingWeight(Integer.parseInt(databaselineReader.nextLine()));
      completeUserRecord.setBMR(Double.parseDouble(databaselineReader.nextLine()));
      completeUserRecord.setLifestyle(Integer.parseInt(databaselineReader.nextLine()));
      completeUserRecord.setUsername(databaselineReader.nextLine());
      completeUserRecord.setPassword(databaselineReader.nextLine());

      tempReaderLong = Long.parseLong(databaselineReader.nextLine());
      DateRecord userDateJoined = new DateRecord(tempReaderLong);
      userDateJoined.setDayOfWeek(databaselineReader.nextLine());
      userDateJoined.setUserMonth(databaselineReader.nextLine());
      userDateJoined.setUserDay(databaselineReader.nextLine());
      userDateJoined.setUserYear(databaselineReader.nextLine());
      userDateJoined.setLastLogin(Long.parseLong(databaselineReader.nextLine()));
      completeUserRecord.setDateJoined(userDateJoined);

      // Instantiate objects of class UserAccount and append to arraylist
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
      updateWriter.println(UserCollection.get(i).getStartingWeight());
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
  } // End addNewProfile(...)

  public void clearLocalMemory() throws FileNotFoundException
  {
    UserCollection.clear();
    initialDataLoad();
  } // End clearLocalMemory()

  public UserAccount getReloadedProfile(int _userProfileIndex)
  {
    return UserCollection.get(_userProfileIndex);
  } // End getReloadedProfile(...)

  public boolean searchExistingUsernames(String _newUsername)
  {
    for (int i = 0; i < UserCollection.size(); ++i)
    {
      if (UserCollection.get(i).getUserID().equals(_newUsername))
        return true;
    }
    return false;
  } // End searchExistingUsernames(...)
}  // End class FileReader()
