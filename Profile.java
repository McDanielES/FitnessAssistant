import java.util.Scanner;
import java.lang.NumberFormatException;
import java.io.FileNotFoundException;

public class Profile
{
  private UserAccount currentUser;
  private long        lastLogin;
  private FileReader  userDatabase;

  Profile(){}
  Profile(UserAccount _currentUser, long _lastLogin, FileReader _userDatabase)
  {
    currentUser  = _currentUser;
    lastLogin    = _lastLogin;
    userDatabase = _userDatabase;
  }

  public void start() throws FileNotFoundException
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
          int updateProfileInput = -1;
          System.out.print("\n\t---------------\n\t| Update Menu |\n\t---------------\n\nWhat would you like to update?\n\n\t1.) Name\n\t2.) Age"
          + "\n\t3.) Height\n\t4.) Lifestyle\n\n\t9.) Save Changes and Exit\n\n***Reminder: you can update your weight from the profile menu***\n\nSelection: ");
          try
          {
            updateProfileInput = Integer.parseInt(userInput.next());
          }
          catch (NumberFormatException nfe)
          {
          }

          // User selects "Update Name"
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
              System.out.print("\n\nSuccess! Your profile name has been updated to: " + newProfileName + "\n\n----------------------------------------\nReturning to Profile Menu");
              FitnessAssistant.timedClearScreen();
            }
            else
            {
              System.out.print("\n\nSorry, profile name could NOT be updated.\n\n------------------------\nReturning to Update Menu");
              FitnessAssistant.timedClearScreen();
            }

          }
          // User selects "Update Age"
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
                System.out.print("\n\nSuccess! Your profile age has been updated to: " + newProfileAge + "\n\n----------------------------------------\nReturning to Profile Menu");
                FitnessAssistant.timedClearScreen();
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
          // User selects "Update Height"
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
                    System.out.print(currentUser.getHeightCompleteString() + " makes you " + currentUser.getHeightInches() + " inches tall.\n\n"
                      + "Your new height has been updated.\n\n----------------------------------------\nReturning to Profile Menu");
                    FitnessAssistant.timedClearScreen();
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

          // User selects "Update Lifestyle"
          else if (updateProfileInput == 4)
          {

          }
          // User selects "Save and Exit"
          else if (updateProfileInput == 9)
          {
            userDatabase.updateProfileDatabase();
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
      + getLastLogin() + " since\nyou've last logged in.\n---------------------------------\n");
  } // End initialWelcomeMenu()

}
