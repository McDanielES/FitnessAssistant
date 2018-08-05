import java.text.DecimalFormat;
public class UserProgress
{
  private int newWeight;
  private UserAccount currentUser;

  UserProgress(){}

  public void setUser(UserAccount _currentUser) { currentUser = _currentUser; }
  public void setNewWeight(int _newWeight) { newWeight = _newWeight; }
  public int getNewWeight() { return newWeight; }

  public void updatedBMRStart()
  {
    System.out.print("\n\t     _____________\n\t    | Progression |\n----------------------------------------\nWeight:\t\t"
      + currentUser.getWeight() + " lbs.   ⇨    " + getNewWeight() + " lbs.\n   ↳  Difference:   "
      + Math.abs(currentUser.getWeight() - getNewWeight()) + " lbs. \n\nSince Start:\t" + currentUser.getStartingWeight()
      + " lbs.   ⇨    " + getNewWeight() + " lbs.\n   ↳  Gross Difference:   "
      + Math.abs(currentUser.getStartingWeight() - getNewWeight()) + " lbs.\n\n\t\t/-----\\\nPercent Change:\t"
      + percentChange(currentUser.getWeight()) + "% " + direction(currentUser.getWeight()) + " from "
      + currentUser.getWeight() + " lbs.\nSince Start:\t" + percentChange(currentUser.getStartingWeight()) + "% "
      + direction(currentUser.getStartingWeight()) + " from " + currentUser.getStartingWeight() + " lbs.");

  }

  public String percentChange(int _weight)
  {
    DecimalFormat formatter = new DecimalFormat("#0.00");
    return formatter.format((Math.abs(((double) getNewWeight() - _weight) / _weight) * 100));
  }

  public String direction(int _weight)
  {
    if (newWeight < _weight)
      return "decrease";
    return "increase";
  }

}
