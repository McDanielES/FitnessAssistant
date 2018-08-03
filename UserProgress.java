public class UserProgress extends UserAccount
{
  private int newWeight;
  private UserAccount currentUser;

  UserProgress(){}
  UserProgress(String _userFirstName, String _isMale, int _userAge, int _userHeightInches, int _userWeight,
    int _userStartingWeight, double _userBMR, int _userLifestyle, String _userID, String _userPassword, DateRecord _userDateJoined, int _newWeight)
  {
    super(_userFirstName, _isMale, _userAge, _userHeightInches, _userWeight, _userStartingWeight, _userBMR, _userLifestyle, _userID, _userPassword, _userDateJoined);
    newWeight = _newWeight;
  }

}
