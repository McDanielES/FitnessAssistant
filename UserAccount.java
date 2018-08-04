public class UserAccount
{
  private String     userFirstName;
  private boolean    isMale = false;
  private int        userAge;
  private int        userHeightInches;
  private int        userWeight;
  private int        userStartingWeight;
  private double     userBMR;
  private int        userLifestyle;
  private String     userID;
  private String     userPassword;
  private DateRecord userDateJoined;

  UserAccount(){}

  public String  getName()          { return userFirstName;    }
  public boolean getGender()        { return isMale;           }
  public int     getAge()           { return userAge;          }
  public int     getHeightInches()  { return userHeightInches; }
  public String  getHeightCompleteString()
  {
    int ByValUserHeightInches = userHeightInches;
    int userHeightFeet = ByValUserHeightInches / 12;
    ByValUserHeightInches = ByValUserHeightInches % 12;
    return userHeightFeet + "\'" + ByValUserHeightInches + "\"";
  } // End String getHeightCompleteString()

  public int    getWeight()         { return userWeight;         }
  public int    getStartingWeight() { return userStartingWeight; }
  public int    getLifestyle()      { return userLifestyle;      }
  public String getUserID()         { return userID;             }
  public String getPassword()       { return userPassword;       }
  public static String getLifestyleText(int _lifestyleIndex)
  {
    if (_lifestyleIndex == 1)
      return "Sedentary (little or no exercise)";
    else if (_lifestyleIndex == 2)
      return "Lightly active (light exercise/sports 1-3 days/week)";
    else if (_lifestyleIndex == 3)
      return "Moderately active (moderate exercise/sports 3-5 days/week)";
    else if (_lifestyleIndex == 4)
      return "Very active (hard exercise/sports 6-7 days a week)";
    else if (_lifestyleIndex == 5)
      return "Extra active (very hard exercise/sports & physical job or 2x training)";
    else
      return "Error";
  }

  public DateRecord getDateJoined()      { return userDateJoined; }
  public long       getJoined()          { return userDateJoined.getUserJoined(); }
  public String     getJoinedDayOfWeek() { return userDateJoined.getDayOfWeek();  }
  public String     getJoinedMonth()     { return userDateJoined.getUserMonth();  }
  public String     getJoinedDay()       { return userDateJoined.getUserDay();    }
  public String     getJoinedYear()      { return userDateJoined.getUserYear();   }
  public Long       getLastLogin()       { return userDateJoined.getLastLogin();  }
  public String     getJoinedFullPrint()
  {
    return getJoinedDayOfWeek() + " " + getJoinedMonth() + "/" + getJoinedDay() + "/" + getJoinedYear();
  } // end String getJoinedFullPrint

  public void setLastLogin(long _lastLogin) { userDateJoined.setLastLogin(_lastLogin); }
  private void calculateBMR()
  {
    if (isMale)
      userBMR = 66 + (6.23 * userWeight) + (12.7 * userHeightInches) - (6.8 * userAge);
    else
      userBMR = 655 + (4.35 * userWeight) + (4.7 * userHeightInches) - (4.7 * userAge);
  } // End void calculateBMR()

  public double getBMR()
  {
    calculateBMR();
    return userBMR;
  } // End double getBMR()

  public void setName(String _setProfileName)  { userFirstName      = _setProfileName;   }
  public void setGender(boolean _setGender)    { isMale             = _setGender;        }
  public void setAge(int _setProfileAge)       { userAge            = _setProfileAge;    }
  public void setHeight(int _setProfileHeight) { userHeightInches   = _setProfileHeight; }
  public void setWeight(int _setProfileWeight) { userWeight         = _setProfileWeight; }
  public void setStartingWeight(int _setSW)    { userStartingWeight = _setSW;            }
  public void setBMR(double _setUserBMR)       { userBMR            = _setUserBMR;       }
  public void setLifestyle(int _setLifestyle)  { userLifestyle      = _setLifestyle;     }
  public void setUsername(String _setUsername) { userID             = _setUsername;      }
  public void setPassword(String _setPassword) { userPassword       = _setPassword;      }
  public void setDateJoined(DateRecord _setDR) { userDateJoined     = _setDR;            }
}
