public class Profile
{
  private UserAccount currentUser;
  private int         userIndex;
  private long        lastLogin;

  Profile(){}
  Profile(UserAccount _currentUser, int _userIndex, long _lastLogin)
  {
    currentUser = _currentUser;
    userIndex   = _userIndex;
    lastLogin   = _lastLogin;
  }

  public void start()
  {
    System.out.print("\n\t----------------\n\t| Profile Menu |\n\t----------------\n\nPlease select an option:\n\t1.) ");
    System.out.println(currentUser.getName() + "\'s BMR: " + currentUser.getBMR());



  }
  private String getLastLogin()
  {
    int hoursSinceLastLogin = (int) ((((System.currentTimeMillis() - lastLogin) / 1000) / 60) / 60);
    if (hoursSinceLastLogin < 23)
      return (hoursSinceLastLogin + " hours");
    else
    {
      if (hoursSinceLastLogin == 1)
        return ((hoursSinceLastLogin / 24) + " day and " + (hoursSinceLastLogin % 24) + " hours");
      else
        return ((hoursSinceLastLogin / 24) + " days and " + (hoursSinceLastLogin % 24) + " hours");
    }
  }
  public String initialWelcomeMenu()
  {
    return ("---------------------------------\n\t Welcome, " + currentUser.getName() + "!\n---------------------------------\n\nIt's been "
      + getLastLogin() + " since\nyou've last logged in.\n---------------------------------\n");
  }

}
