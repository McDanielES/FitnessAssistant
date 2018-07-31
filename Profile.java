import java.util.Scanner;
import java.lang.NumberFormatException;

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
    boolean continueProgram = true;
    Scanner userInput = new Scanner(System.in);
    int input = -1;
    do
    {
      System.out.print("\n\t----------------\n\t| Profile Menu |\n\t----------------\n\n\nPlease select an option:\n\n\t1.) Update Weight / Recalculate "
        + "BMR\n\t2.) Update User Profile\n\n\t9.) Exit\n\nSelection: ");
      try
      {
        input = Integer.parseInt(userInput.next());
      }
      catch (NumberFormatException nfe)
      {
        continueProgram = true;
      }

      //// User selects "Update Weight / Recalculate BMR" ////
      if (input == 1)
      {
        System.out.println("\t\tSuccessful");
        FitnessAssistant.clearScreen();
      }

      //// User selects "Update User Profile" ////
      else if (input == 2)
      {
        boolean continueUpdateProfile = true;
        do
        {
          FitnessAssistant.clearScreen();
          int Menu2input = -1;
          System.out.print("\n\t---------------\n\t| Update Menu |\n\t---------------\n\nWhat would you like to update?\n\n\t1.) Name\n\t2.) Age"
          + "\n\t3.) Height\n\t4.) Lifestyle\n\n\t9.) None, Exit\n\n***Reminder: you can update your weight from the profile menu***\n\nSelection: ");
          try
          {
            Menu2input = Integer.parseInt(userInput.next());
          }
          catch (NumberFormatException nfe)
          {
            continueUpdateProfile = true;
          }

          // User selects "Update Name"
          if (Menu2input == 1)
          {
            FitnessAssistant.clearScreen();
            System.out.print("\n\t---------------\n\t| Update Name |\n\t---------------\n\n");
            System.out.print("\tCurrent profile name: " + currentUser.getName() + "\n\n\tUpdated profile name: ");
            String newProfileName = userInput.next();
            System.out.print("\tConfirm updated name: ");
            String newProfileNameConfirm = userInput.next();
            if (newProfileName.equals(newProfileNameConfirm))
            {
              System.out.print("\nSuccess. Name to be changed");
              continueUpdateProfile = false;
              FitnessAssistant.timedClearScreen();
            }
            else
            {
              System.out.print("\n\nSorry, profile name could NOT be updated.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }

          }
          // User selects "Update Age"
          else if (Menu2input == 2)
          {

          }
          // User selects "Update Height"
          else if (Menu2input == 3)
          {

          }
          // User selects "Update Lifestyle"
          else if (Menu2input == 4)
          {

          }
          // User selects "Exit"
          else if (Menu2input == 9)
          {
            continueProgram = true;
            continueUpdateProfile = false;
          }
          // User provides invalid input.
          else
          {

          }


        } while (continueUpdateProfile);
      input = -1;
      FitnessAssistant.clearScreen();
      }
      else if (input == 9)
      {
        FitnessAssistant.clearScreen();
        continueProgram = false;
        System.out.print("\n\nThank you for using The Fitness Assistant!\nWritten by Eric S McDaniel, July-August 2018.\n\n");
      }

      else
      {
        System.out.println("Other input detected.");
        FitnessAssistant.clearScreen();
        continueProgram = true;
      }

    } while (continueProgram);





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
