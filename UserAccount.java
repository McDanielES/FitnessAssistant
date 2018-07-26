public class UserAccount
{
  private String     userFirstName;
  private boolean    isMale = false;
  private int        userAge;
  private int        userHeightInches;
  private int        userWeight;
  private int        userLifestyle;
  private String     userID;
  private String     userPassword;
  private DateRecord userDateJoined;

  UserAccount(){}

  UserAccount(String _userFirstName, String _isMale, int _userAge, int _userHeightInches,
    int _userWeight, int _userLifestyle, String _userID, String _userPassword, DateRecord _userDateJoined)
    {
      userFirstName    = _userFirstName;
      if (_isMale.equals("true"))
        isMale         = true;
      userAge          = _userAge;
      userHeightInches = _userHeightInches;
      userWeight       = _userWeight;
      userLifestyle    = _userLifestyle;
      userID           = _userID;
      userPassword     = _userPassword;
      userDateJoined   = _userDateJoined;
    }

  public String  getName()          { return userFirstName;    }
  public boolean getGender()        { return isMale;           }
  public int     getAge()           { return userAge;          }
  public int     getHeightInches()  { return userHeightInches; }
  public String  getHeightCompleteString()
  {
    int userHeightFeet = userHeightInches / 12;
    userHeightInches = userHeightInches % 12;
    return userHeightFeet + "\'" + userHeightInches + "\"";
  }
  public int    getWeight()    { return userWeight;    }
  public int    getLifestyle() { return userLifestyle; }
  public String getUserID()    { return userID;        }
  public String getPassword()  { return userPassword;  }

  public long   getJoined()          { return userDateJoined.getUserJoined(); }
  public String getJoinedDayOfWeek() { return userDateJoined.getDayOfWeek();  }
  public String getJoinedMonth()     { return userDateJoined.getUserMonth();  }
  public String getJoinedDay()       { return userDateJoined.getUserDay();    }
  public String getJoinedYear()      { return userDateJoined.getUserYear();   }
  public Long   getLastLogin()       { return userDateJoined.getLastLogin();  }
  public String getJoinedFillPrint()
  {
    return getJoinedDayOfWeek() + " " + getJoinedMonth() + "/" + getJoinedDay() + "/" + getJoinedYear();
  }

  public void setLastLogin(long _lastLogin) { userDateJoined.setLastLogin(_lastLogin); }
}
