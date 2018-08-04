public class UserProgress extends UserAccount
{
  private int newWeight;
  private UserAccount currentUser;

  UserProgress(){}
  UserProgress(String _userFirstName, String _isMale, int _userAge, int _userHeightInches, int _userWeight,
    int _userStartingWeight, double _userBMR, int _userLifestyle, String _userID, String _userPassword, DateRecord _userDateJoined)
  {
    super(_userFirstName, _isMale, _userAge, _userHeightInches, _userWeight, _userStartingWeight, _userBMR, _userLifestyle, _userID, _userPassword, _userDateJoined);
  }

  public void setNewWeight(int _newWeight) { newWeight = _newWeight; }
  public int getNewWeight() { return newWeight; }

  public void recalcBMR()
  {
    System.out.print("\n\nPrevious Weight: " + getWeight());
  }

}
