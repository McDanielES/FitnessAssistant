import java.text.DecimalFormat;
public class UserProgress
{
  private int newWeight;
  private UserAccount currentUser;
  private int hoursSinceStart;

  UserProgress(){}

  public void setUser(UserAccount _currentUser) { currentUser = _currentUser; }
  public void setNewWeight(int _newWeight) { newWeight = _newWeight; }
  public int getNewWeight() { return newWeight; }

  public void updatedWeightStats()
  {
    System.out.print(printUserInfo());

    System.out.print("\n\t     ____________\n\t    | Statistics |\n             ‾‾‾‾‾‾‾‾‾‾‾‾\nNew Weight:\t"
      + currentUser.getWeight() + " lbs.   ⇨    " + getNewWeight() + " lbs.\n   ↳  Difference:   "
      + Math.abs(currentUser.getWeight() - getNewWeight()) + " lbs. \n\nSince Start:\t" + currentUser.getStartingWeight()
      + " lbs.   ⇨    " + getNewWeight() + " lbs.\n   ↳  Gross Difference:   "
      + Math.abs(currentUser.getStartingWeight() - getNewWeight()) + " lbs.\n\n\n");

    System.out.print("Percent Change:\t" + percentChange(currentUser.getWeight()) + "% "
      + direction(currentUser.getWeight()) + " from " + currentUser.getWeight() + " lbs.\n ↳ Since Start:\t"
      + percentChange(currentUser.getStartingWeight()) + "% " + direction(currentUser.getStartingWeight()) + " from "
      + currentUser.getStartingWeight() + " lbs.\n\n—————————————————————————————————————————————\n");

    System.out.print("Average Daily Loss:\t" + dailyAverage(currentUser.getWeight()) + " lbs/day\n    ↳  Weekly Loss:\t"
      + weeklyAverage(currentUser.getWeight()) + " lbs/week\n\nSince Beginning:\t"
      + dailyAverage(currentUser.getStartingWeight()) + " lbs/day\n      ↳  Weekly:\t"
      + weeklyAverage(currentUser.getStartingWeight()) + " lbs/week\n\n");

    System.out.print("____________________________________________\n       Press \"Enter\" key to Continue...\n");
    try
    {
      System.in.read();
    }
    catch(Exception e){}

    //TODO that was just the general stats. After ENTER key, user should get new BMR calories a day, Percent deficit, etc..
  }

  private String percentChange(int _weight)
  {
    DecimalFormat formatter = new DecimalFormat("#0.00");
    return formatter.format((Math.abs(((double) getNewWeight() - _weight) / _weight) * 100));
  }

  private double calcAverage(int _weight)
  {
    if (hoursSinceStart < 23)
      return (_weight - newWeight);
    else
      return Math.abs((double) (_weight - newWeight) / (hoursSinceStart / 24.0));
  }

  private String dailyAverage(int _weight)
  {
    DecimalFormat formatter = new DecimalFormat("#0.00");
    //double totalHours = calcAverage(_weight);
    return formatter.format(calcAverage(_weight));
  }
  private String weeklyAverage(int _weight)
  {
    DecimalFormat formatter = new DecimalFormat("#0.00");
    // double totalHours = calcAverage(_weight);
    return (formatter.format(calcAverage(_weight) * 7));
  }

  private String direction(int _weight)
  {
    if (newWeight < _weight)
      return "decrease";
    return "increase";
  }

  private String profileDuration()
  {
    hoursSinceStart = (int) ((((currentUser.getLastLogin() - currentUser.getJoined()) / 1000) / 60) / 60);
    int hoursSinceStartDuration = hoursSinceStart;
    if (hoursSinceStartDuration < 23)
      return (hoursSinceStartDuration + " hours");
    else
    {
      if (hoursSinceStartDuration == 1)
        return ((hoursSinceStartDuration / 24) + " day and " + (hoursSinceStartDuration % 24) + " hours");
      else
        return ((hoursSinceStartDuration / 24) + " days and " + (hoursSinceStartDuration % 24) + " hours");
    }
  } // End String getLastLogin()

  private String printUserInfo()
  {
    return ("___________________________________________\nName:\t\t" + currentUser.getName() + "\nAge:\t\t"
      + currentUser.getAge() + " years old\nStart Date:\t" + currentUser.getJoinedFullPrint() + "\nDuration:\t"
      + profileDuration() + "\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾\n");
  }

}
