import java.util.Scanner;
import java.lang.NumberFormatException;
import java.io.FileNotFoundException;

public class Profile
{
  private UserAccount currentUser;
  private long        lastLogin;
  private FileReader  userDatabase;
  private int         userProfileIndex;

  Profile(){}
  Profile(UserAccount _currentUser, long _lastLogin, FileReader _userDatabase, int _userProfileIndex)
  {
    currentUser  = _currentUser;
    lastLogin    = _lastLogin;
    userDatabase = _userDatabase;
    userProfileIndex = _userProfileIndex;
  }

  public void start() throws FileNotFoundException
  {
    boolean continueProgram = true;
    Scanner userInput = new Scanner(System.in);
    int input = -1;
    do
    {
      System.out.print("\n----------------------------------\n\t" + currentUser.getName() + "\'s Main Menu"
        + "\n----------------------------------\n\n\nPlease select an option:\n\n\t1.) Get " + currentUser.getName()
        + "\'s Statistics\n\t2.) Update Weight / Recalculate "
        + "BMR\n\t3.) Update User Fitness Profile Attributes\n\n\t9.) Exit Application\n\nSelection: ");
      try
      {
        input = Integer.parseInt(userInput.next());
      }
      catch (NumberFormatException nfe)
      {
        continueProgram = true;
      }

      /***************************************************************************************|
      |                       (1) "Update Weight / Recalculate BMR"                           |
      |***************************************************************************************/
      if (input == 1)
      {
        System.out.println("\t\tSuccessful");
        FitnessAssistant.clearScreen();
      }

      /***************************************************************************************|
      |                       (2) "Update Weight / Recalculate BMR"                           |
      |***************************************************************************************/
      else if (input == 2)
      {
        FitnessAssistant.clearScreen();
      }

      /***************************************************************************************|
      |                       (3) "Update User Profile" Update Menu                           |
      |***************************************************************************************/
      else if (input == 3)
      {
        boolean continueUpdateProfile = true;
        boolean profileChangeRequested = false;
        do
        {
          FitnessAssistant.clearScreen();
          int updateProfileInput = -1;

          System.out.print("\n\t---------------------------\n\t|   Profile Update Menu   |\n\t---------------------------\n\n");
          if (profileChangeRequested)
            System.out.print("--------------------------------------------------------------------\n***You have unsaved updates. "
              + "Select Option 9 to save the changes!***\n--------------------------------------------------------------------");
          else
            System.out.print(" What attribute would you like to update?\n------------------------------------------");

          System.out.print("\n\n\tSelection:\tCurrent Attribute:\n\t----------------------------------\n\t1.) Name\t" + currentUser.getName()
            + "\n\t2.) Age\t\t" + currentUser.getAge() + "\n\t3.) Height\t" + currentUser.getHeightCompleteString()
            + "\n\t4.) Lifestyle\t" + currentUser.getLifestyle() + ": " + UserAccount.getLifestyleText(currentUser.getLifestyle()));
          if (profileChangeRequested)
          {
            System.out.print("\n\n\t9.) Save Changes and Exit\n\t10.) Exit Without Saving Changes"
              + "\n\n***Reminder: you can update your weight from the profile menu***\n\nSelection: ");
          }
          else
          {
            System.out.print("\n\n\t9.) Exit Without Making Changes\n\n***Reminder: You can update your weight from the profile menu***\n\nSelection: ");
          }
          try
          {
            updateProfileInput = Integer.parseInt(userInput.next());
          }
          catch (NumberFormatException nfe)
          { // Nothing, the loop will reiterate and clear the screen.
          }

          /////////////////////////       (3) "Update User Profile", (1) "Update Name"     /////////////////////////
          if (updateProfileInput == 1)
          {
            FitnessAssistant.clearScreen();
            System.out.print("\n\t---------------\n\t| Update Name |\n\t---------------\n\n");
            System.out.print("Current profile name: " + currentUser.getName() + "\n-------------------------------\n\tUpdated profile name: ");
            String newProfileName = userInput.next();
            System.out.print("\tConfirm updated name: ");
            String newProfileNameConfirmed = userInput.next();
            if (newProfileName.equals(newProfileNameConfirmed))
            {
              currentUser.setName(newProfileName);
              profileChangeRequested = true;
            }
            else
            {
              System.out.print("\n\nSorry, profile name could NOT be updated.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }
          }
          /////////////////////////       (3) "Update User Profile", (2) "Update Age"     /////////////////////////
          else if (updateProfileInput == 2)
          {
            FitnessAssistant.clearScreen();
            System.out.print("\n\t--------------\n\t| Update Age |\n\t--------------\n\n");
            System.out.print("Current profile age: " + currentUser.getAge() + "\n------------------------------\n\tUpdated profile age: ");
            int newProfileAge = -1;
            try
            {
              newProfileAge = Integer.parseInt(userInput.next());
              if (!(newProfileAge <= 2 || newProfileAge > 120))
              {
                currentUser.setAge(newProfileAge);
                profileChangeRequested = true;
              }
              else
              {
                System.out.print("\n\nSorry, Invalid age provided.\n\n------------------------\nReturning to Update Menu");
                FitnessAssistant.timedClearScreen();
              }

            }
            catch (NumberFormatException nfe)
            {
              System.out.print("\n\nSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }
          }
          /////////////////////////       (3) "Update User Profile", (3) "Update Height"     /////////////////////////
          else if (updateProfileInput == 3)
          {
            FitnessAssistant.clearScreen();
            System.out.print("\n\t-----------------\n\t| Update Height |\n\t-----------------\n\n");
            System.out.print("Current profile height: " + currentUser.getHeightCompleteString() + "\n------------------------------\nUpdated profile height\n\tFeet: ");

            int newUserHeightFeet = -1;
            int newUserHeightInches = -1;
            try
            {
              newUserHeightFeet = Integer.parseInt(userInput.next());
              if (newUserHeightFeet <= 2 || newUserHeightFeet > 12)
              {
                System.out.print("\n\nSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
                FitnessAssistant.timedClearScreen();
              }
              else
              {
                System.out.print("\tInches: ");
                try
                {
                  newUserHeightInches = Integer.parseInt(userInput.next());
                  if (newUserHeightInches < 0 || newUserHeightInches >= 12)
                  {
                    System.out.print("\n\nSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
                    FitnessAssistant.timedClearScreen();
                  }
                  else
                  {
                    currentUser.setHeight((newUserHeightFeet * 12) + newUserHeightInches);
                    profileChangeRequested = true;
                  }
                }
                catch (NumberFormatException nfe)
                {
                  System.out.print("\n\nSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
                  FitnessAssistant.timedClearScreen();
                }
              }
            }
            catch (NumberFormatException nfe)
            {
              System.out.print("\n\nSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }
          }

          /////////////////////////       (3) "Update User Profile", (4) "Update Lifestyle"     /////////////////////////
          else if (updateProfileInput == 4)
          {
            FitnessAssistant.clearScreen();
            System.out.print("\n\t--------------------\n\t| Update Lifestyle |\n\t--------------------\n\n");
            System.out.print("Lifestyle Options:\n\tOption 1: " + UserAccount.getLifestyleText(1) + "\n\tOption 2: " + UserAccount.getLifestyleText(2)
              + "\n\tOption 3: " + UserAccount.getLifestyleText(3) + "\n\tOption 4: " + UserAccount.getLifestyleText(4)
              + "\n\tOption 5: " + UserAccount.getLifestyleText(5) + "\n\t---------------------------------------------------------------------"
              + "\n\nCurrent lifestyle setting: Option " + currentUser.getLifestyle() + " - " + UserAccount.getLifestyleText(currentUser.getLifestyle())
              + "\n___________________________________\nUpdated lifestyle setting: Option ");
            int newProfileLifestyle = -1;
            try
            {
              newProfileLifestyle = Integer.parseInt(userInput.next());
              if (!(newProfileLifestyle <= 0 || newProfileLifestyle > 5))
              {
                currentUser.setLifestyle(newProfileLifestyle);
                profileChangeRequested = true;
              }
              else
              {
                System.out.print("\n\n\tSorry, Invalid lifestyle option provided.\n\n------------------------\nReturning to Update Menu");
                FitnessAssistant.timedClearScreen();
              }

            }
            catch (NumberFormatException nfe)
            {
              System.out.print("\n\n\tSorry, Invalid input provided.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }
          }
          /////////////////////////       (3) "Update User Profile", (9) "Save Changes and Exit"     /////////////////////////
          else if (updateProfileInput == 9)
          {
            if (profileChangeRequested)
            {
              FitnessAssistant.clearScreen();
              System.out.print("/---------------------------------------\\\n|\t Authorization Required \t|\n\\---------------------------------------/"
                + "\n\nPlease enter your password to save your changes.\n\tPassword: ");
              String userUpdateProfilePassword = "";
              userUpdateProfilePassword = userInput.next();
              if (userUpdateProfilePassword.equals(currentUser.getPassword()))
              {
                userDatabase.updateProfileDatabase();
                profileChangeRequested = false;
                System.out.print("\n\nYour changes have been successfully saved.\n\n----------------------------------------\nReturning to Update Menu");
                FitnessAssistant.timedClearScreen();
                continueUpdateProfile = false;
              }
              else
              {
                System.out.print("\n\tInvalid Password!\nYour request has been denied. Please try again");
                FitnessAssistant.timedClearScreen();
              }
            }
            else
            {
              continueUpdateProfile = false;
            }
          }
          /////////////////////////       (3) "Update User Profile", (10) "Exit Without Saving Changes"     /////////////////////////
          else if ((profileChangeRequested) && updateProfileInput == 10)
          {
            userDatabase.clearLocalMemory();
            currentUser = userDatabase.getReloadedProfile(userProfileIndex);
            profileChangeRequested = false;
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
      /***********************************************************************|
      |                       (9) "Exit Application"                          |
      |***********************************************************************/
      else if (input == 9)
      {
        FitnessAssistant.clearScreen();
        continueProgram = false;
        System.out.print("\n\nThank you for using The Fitness Assistant!\nWritten by Eric S McDaniel, July-August 2018.\n\n");
      }
      // If the user inputs any integer not listed in the main menu
      else
      {
        FitnessAssistant.clearScreen();
        continueProgram = true;
      }

    } while (continueProgram);





  } // End void Start(), the main of the Profile class

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
  } // End String getLastLogin()

  public String initialWelcomeMenu()
  {
    return ("---------------------------------\n\t Welcome, " + currentUser.getName() + "!\n---------------------------------\n\nIt's been "
      + getLastLogin() + " since\nyou've last logged in.\n\n");
  } // End initialWelcomeMenu()
} // End class Profile
