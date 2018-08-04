import java.util.Scanner;
import java.util.Date;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;

public class FitnessAssistant
{
  public static void main(String[] args) throws FileNotFoundException
  {
    clearScreen();
    String databaseFilename = "ProfilesDatabase.txt";
    ArrayList<UserAccount> UserCollection = new ArrayList<UserAccount>();
    FileReader userDatabase = new FileReader(databaseFilename, UserCollection);
    if (!userDatabase.exists())
      System.out.print("Error: User Profiles could not be loaded. Please contact the developer to resolve this issue.\n");
    else
    {
      // Read from the file and store user profiles into memory. Yes this isn't practical. This database doesn't have ten million accounts however.
      userDatabase.initialDataLoad();
      boolean userContinue = true;

      do
      {
        // Determine if user has previously established an account
        System.out.print("\n_________________________________\nWelcome to the Fitness Assistant!\n\n\t-----------------\n\t|   Main Menu   |\n\t-----------------\n\n\n"
          + "Do you have an existing account?\n\nType Y/n to login or QUIT to exit: ");
        char hasAccount;
        hasAccount = '0';
        Scanner userInput = new Scanner(System.in);
        hasAccount = userInput.next().charAt(0);

        if (hasAccount == 'Q' || hasAccount == 'q')
        {
          userContinue = false;
          System.out.print("\n\n\tThank you for using The Fitness Assistant!\n\tWritten by Eric S McDaniel, July-August 2018.\n\n");
        }
        else
        {
          // Loop until valid user input is received
          while (hasAccountValidInput(hasAccount))
          {
            System.out.print("\n\tInvalid entry. Please try again.\n\nType Y/n to login or QUIT to exit: ");
            hasAccount = userInput.next().charAt(0);
          }
          Scanner userInputTemp = new Scanner(System.in);

          // Establishes account if none exists
          if (!hasAccountVerified(hasAccount))
          {
            UserAccount newProfileCreate = new UserAccount();

            System.out.print("\nLet's establish an account for yourself.\nThis way I can calculate your measurables and track your progress.\n\tWhat is your name? ");
            newProfileCreate.setName(userInput.next());

            // Get users' gender.
            boolean validGender = false;
            String userGender = "";
            while (!validGender)
            {
              System.out.print("\tWhat gender are you? Type M/F: ");
              userGender = userInputTemp.next();
              if (!(userGender.charAt(0) == 'M' || userGender.charAt(0) == 'm' || userGender.charAt(0) == 'F' || userGender.charAt(0) == 'f'))
                System.out.print("\t\t" + userGender + "? Sorry, please revise your input.\n");
              else
                validGender = true;
              // If user entered M/m/F/f, then determin gender.
              if (userGender.charAt(0) == 'M' || userGender.charAt(0) == 'm')
                newProfileCreate.setGender(true);
              else
                newProfileCreate.setGender(false);
            }

            // Get users' age.
            System.out.print("\tWhat is your age? ");
            int userAge = 0;
            boolean validUserAge = false;
            do
            {
              try
              {
                userAge = Integer.parseInt(userInputTemp.next());
                if (userAge <= 2 || userAge > 120)
                  System.out.print("\t\tYou seriously aren't " + userAge + " years old. Let's try again.\n\tWhat is your age? ");
                else
                  validUserAge = true;
              }
              catch (NumberFormatException nfe)
              {
                System.out.print("\t\tInvalid input. Please try again.\n\tWhat is your age? ");
              }
            } while (!validUserAge);
            newProfileCreate.setAge(userAge);

            // Get users' height. Will convert feet/inches to total inches.
            System.out.print("\tWhat is your height?\n\t\tFeet: ");
            boolean validUserHeight = false;
            int userHeightFeet = 0;
            int userHeightInches = 0;
            do
            {
              try
              {
                userHeightFeet = Integer.parseInt(userInputTemp.next());
                if (userHeightFeet <= 2 || userHeightFeet > 12)
                  System.out.print("\t\t\tYou seriously aren't " + userHeightFeet + " feet \"tall\". Let's try again.\n\t\tYour height in feet? ");
                else
                  validUserHeight = true;
              }
              catch (NumberFormatException nfe)
              {
                System.out.print("\t\t\tInvalid input. Please try again.\n\t\tYour height in feet? ");
              }
            } while (!validUserHeight);

            validUserHeight = false;
            System.out.print("\t\tInches: ");
            do
            {
              try
              {
                userHeightInches = Integer.parseInt(userInputTemp.next());
                if (userHeightInches < 0)
                  System.out.print("\t\t\tYou seriously aren't " + userHeightInches + " inches \"tall\". Let's try again.\n\t\tInches above " + userHeightFeet + " feet? ");
                else if (userHeightInches >= 12)
                  System.out.print("\t\t\tWell, there are only 12 inches in a foot. Try again.\n\t\tInches above " + userHeightFeet + " feet? ");
                else
                  validUserHeight = true;
              }
              catch (NumberFormatException nfe)
              {
                System.out.print("\t\t\tInvalid input. Please try again.\n\t\tInches above " + userHeightFeet + " feet? ");
              }

            } while (!validUserHeight);
            System.out.print("\t\t------------------------------\n\t\t" + userHeightFeet + "\'" + userHeightInches + "\" makes you ");
            userHeightInches = (userHeightFeet * 12) + userHeightInches;
            newProfileCreate.setHeight(userHeightInches);

            // Get users' weight.
            System.out.print(userHeightInches + " inches tall.\n\tWhat is your weight? ");
            boolean validUserWeight = false;
            int userWeight = 0;
            do
            {
              try
              {
                userWeight = Integer.parseInt(userInputTemp.next());
                if (userWeight < 40 || userWeight > 600)
                  System.out.print("\t\tYou seriously aren't " + userWeight + "lbs. Let's try again.\n\tWhat is your weight? ");
                else
                  validUserWeight = true;
              }
              catch (NumberFormatException nfe)
              {
                System.out.print("\t\tInvalid input. Please try again.\n\tWhat is your weight? ");
              }
            } while (!validUserWeight);
            newProfileCreate.setWeight(userWeight);
            newProfileCreate.setStartingWeight(userWeight);
            newProfileCreate.setBMR(-1.0);


            // Determine user's lifestyle
            System.out.print("\tHow active is your lifestyle?\n\t\tOption 1: " + UserAccount.getLifestyleText(1) + "\n\t\tOption 2: "
              + UserAccount.getLifestyleText(2) + "\n\t\tOption 3: " + UserAccount.getLifestyleText(3) + "\n\t\tOption 4: "
              + UserAccount.getLifestyleText(4) + "\n\t\tOption 5: " + UserAccount.getLifestyleText(5)
              + "\n\t\t---------------------------------------------------------------------\n\tLifestyle option (1-5): ");
            boolean validLifestyleRange = false;
            int userLifestyle = -1;
            do
            {
              try
              {
                userLifestyle = Integer.parseInt(userInputTemp.next());
                if (userLifestyle <= 0 || userLifestyle > 5)
                  System.out.print("\t\tThat was not one of the listed options. Try again.\n\tLifestyle option (1-5): ");
                else
                  validLifestyleRange = true;
              }
              catch (NumberFormatException nfe)
              {
                System.out.print("\t\tInvalid input. Please try again.\n\tLifestyle option (1-5): ");
              }
            } while (!validLifestyleRange);
            newProfileCreate.setLifestyle(userLifestyle);

            // Create username and make sure it isn't found in the database
            System.out.print("\nExcellent! Your profile has been created.\nLastly, let's create a username and password for when you return.\n\n");
            Random IDGenerator = new Random();
            int userAccountIDNumber = -1;
            String userID = "";
            int collectionCounter;
            boolean linearSearchMatchFound = false;
            do
            {
              // A userID is generating by concatenating the name with a random three-digit number.
              linearSearchMatchFound = false;
              collectionCounter = 0;
              userAccountIDNumber = IDGenerator.nextInt(799) + 101;
              userID = String.format("%s%d", newProfileCreate.getName(), userAccountIDNumber);

              // A Linear search of if the generated username is found in the records.
              while (collectionCounter < UserCollection.size())
              {
                if (UserCollection.get(collectionCounter).getUserID().toUpperCase().equalsIgnoreCase(userID.toUpperCase()))
                {
                  collectionCounter = UserCollection.size();
                  linearSearchMatchFound = true;
                }
                ++collectionCounter;
              }
            } while (linearSearchMatchFound);
            newProfileCreate.setUsername(userID);

            // Create the user's password
            System.out.print("\tYour auto generated username is \n\t-------------------------------\n\t\t  "
              + userID + "\n\t-------------------------------\n\tTry not to forget it!\n\n\tProvide a password: ");
            String userPassword = userInputTemp.next();
            System.out.print("\t-------------------------------\n\t\t|| Username: " + newProfileCreate.getUserID() + " \n\t\t|| Password: " + newProfileCreate.getPassword()
              + "\n\t-------------------------------\n\nThanks for creating an account, " + newProfileCreate.getName() + "!\n");

            // Format the date
            DateRecord userDateJoined = new DateRecord(System.currentTimeMillis());
            userDateJoined.setDayOfWeek(String.format("%ta", userDateJoined));
            userDateJoined.setUserMonth(String.format("%tm", userDateJoined));
            userDateJoined.setUserDay(String.format("%td", userDateJoined));
            userDateJoined.setUserYear(String.format("%ty", userDateJoined));
            userDateJoined.setLastLogin(System.currentTimeMillis());
            newProfileCreate.setDateJoined(userDateJoined);

            // Add object to the ArrayList, and method updateProfileDatabase writes the database file with new info.
            // TODO Edit comment above
            UserCollection.add(newProfileCreate);
            userDatabase.updateProfileDatabase();

            System.out.print("Congratulations for being a member since " + newProfileCreate.getJoinedFullPrint()
              + "!\n\n\t________________________________________\n\tPlease return to the Main Menu to login.\n");
            userContinue = continueProgram(userInput);
          }

          // Opposite case where if the user has an account and input "y" in the beginning.
          else
          {
            System.out.print("\nPlease login below. If you cannot remember your username or\n     password, please type HELP for the recovery menu.\n\n\t|| Username: ");
            String username = userInput.next();
            // boolean userContinue = "";

            if (username.equalsIgnoreCase("help"))
            {
              System.out.print("\n\t   --------------------\n\t   | Profile Recovery |\n\t   --------------------\n\tWhat is your first name? ");
              username = userInput.next();
              if (!FindNameInDatabase(username, UserCollection))
              {
                // Terminates program is no matching first name is ever found.
                System.out.print("\n\nSorry, there are no accounts with the name " + username + ".\nPlease either confirm your spelling or create a new account.\n");
                userContinue = continueProgram(userInput);
              }
              else
              {
                System.out.print("\tHow old are you? ");
                boolean validAgeParse = false;
                int userAgeRecovery = 0;
                do
                {
                  try
                  {
                    validAgeParse = false;
                    userAgeRecovery = Integer.parseInt(userInput.next());
                    validAgeParse = true;
                  }
                  catch (NumberFormatException nfe)
                  {
                    System.out.print("\t\tInvalid input. Try again\n\tHow old are you? ");
                  }

                } while (!validAgeParse);
                if (!FindAgeInDatabase(username, userAgeRecovery, UserCollection))
                {
                  // If the user isn't within three years of age, recovery is impossible.
                  System.out.print("\nSorry, there are no profiles named " + username + " who are reasonably close to " + userAgeRecovery + " years old.\n"
                    + "Please either contact the developer or create a new profile to get started.\n");
                  userContinue = continueProgram(userInput);
                }
                else
                {
                  System.out.print("\tWhat is your height?\n\t\tFeet: ");
                  boolean validUserHeightRecovery = false;
                  int userHeightFeetRecovery = 0;
                  int userHeightInchesRecovery = 0;
                  do
                  {
                    try
                    {
                      userHeightFeetRecovery = Integer.parseInt(userInput.next());
                      validUserHeightRecovery = true;
                    }
                    catch (NumberFormatException nfe)
                    {
                      System.out.print("\t\t\tInvalid input. Please try again.\n\t\tYour height in feet? ");
                    }
                  } while (!validUserHeightRecovery);

                  validUserHeightRecovery = false;
                  System.out.print("\t\tInches: ");
                  do
                  {
                    try
                    {
                      userHeightInchesRecovery = Integer.parseInt(userInput.next());
                      validUserHeightRecovery = true;
                    }
                    catch (NumberFormatException nfe)
                    {
                      System.out.print("\t\t\tInvalid input. Please try again.\n\t\tInches above " + userHeightFeetRecovery + " feet? ");
                    }
                  } while (!validUserHeightRecovery);
                  System.out.print("\n");
                  userHeightInchesRecovery = (userHeightFeetRecovery * 12) + userHeightInchesRecovery;
                  int userArrayListIndex = FindHeightInDatabase(username, userHeightInchesRecovery, UserCollection);
                  if (userArrayListIndex >= 0)
                  {
                    System.out.print("\t-----------------------------\n\t|| Your Username is: " + UserCollection.get(userArrayListIndex).getUserID()
                      + "\n\t|| Your Password is: " + UserCollection.get(userArrayListIndex).getPassword() + "\n\t-----------------------------\n");
                    userContinue = continueProgram(userInput);
                  }
                  else
                  {
                    System.out.print("\nSorry, there is no profile that matches that description.\nPlease either contact the developer or create a new account.\n");
                    userContinue = continueProgram(userInput);
                  }
                }   // End FindAgeInDatabase()
              }     // End FindNameInDatabase()
            }       // End If user typed HELP
            else
            {
              int userProfileIndex = -1;
              for (int i = 0; i < UserCollection.size(); ++i)
              {
                if (UserCollection.get(i).getUserID().equalsIgnoreCase(username))
                  userProfileIndex = i;
              }
              if (userProfileIndex < 0)
              {
                System.out.print("\nSorry, there is no profile with the username " + username + ".\nPlease confirm your spelling or type HELP to access Profile Recovery.\n\n");
                userContinue = continueProgram(userInput);
              }
              else
              {
                int incorrectPasswordCounter = 4;
                boolean validPassword = false;
                do
                {
                  System.out.print("\t|| Password: ");
                  String password = userInput.next();
                  if (!password.equals(UserCollection.get(userProfileIndex).getPassword()))
                  {
                    --incorrectPasswordCounter;
                    System.out.print("\t\tInvalid password. Please check your spelling and try again.\n\t\tAttempts remaining: " + incorrectPasswordCounter + "\n");
                  }
                  else
                    validPassword = true;
                } while ((!validPassword) && (incorrectPasswordCounter > 0));
                if (incorrectPasswordCounter <= 0)
                {
                  System.out.print("\t-------------------------------------------------------------------\n\nYou have repeatedly "
                    + "provided the incorrect password.\nPlease use the recovery menu to recover your account.\n");
                  userContinue = continueProgram(userInput);
                }
                else // Password was valid
                {
                  /////////////////////////////// Profile Login System Complete ////////////////////////////////
                  System.out.print("\n\nYeah I see what you're doing, scrolling up and shiz.\n\n");
                  clearScreen();

                  // Collect last login before its overwritten, and create object to direct to Profile menu.
                  long lastLogin = UserCollection.get(userProfileIndex).getLastLogin();
                  Profile currentUser = new Profile(UserCollection.get(userProfileIndex), lastLogin, userDatabase, userProfileIndex);

                  // Reset last login, save to database, and present special "first time" Profile menu.
                  UserCollection.get(userProfileIndex).setLastLogin(System.currentTimeMillis());
                  userDatabase.updateProfileDatabase();
                  System.out.print(currentUser.initialWelcomeMenu());

                  // Begin logged in profile menu
                  if (currentUser.start())
                    userContinue = true;
                  else
                  userContinue = false; // End program


                  System.out.print("\n");
                }                  // End PROGRAM
              }                    // End userProfileIndex = -1 (Where no username matched input)
            }                      // End Userinput in username field
          }                        // End if Account was Verified
        }                          // End if the user wants to quit the application
      } while (userContinue);      // End do-while loop back to Main Menu
    }                              // End if Database exists
  }                                // End main()

  public static boolean hasAccountValidInput(char _hasAccount)
  {
    if (!(_hasAccount == 'y' || _hasAccount == 'Y' || _hasAccount == 'n' || _hasAccount == 'N' || _hasAccount == '0'))
      return true;
    return false;
  } // End hasAccountValidInput()
  public static boolean hasAccountVerified(char _hasAccount)
  {
    if (_hasAccount == 'Y' || _hasAccount == 'y')
      return true;
    return false;
  } // End hasAccountVerified()

  public static boolean FindNameInDatabase(String _userFirstName, ArrayList<UserAccount> _UserCollection)
  {
    for (int i = 0; i < _UserCollection.size(); ++i)
    {
      if (_UserCollection.get(i).getName().equalsIgnoreCase(_userFirstName))
        return true;
    }
    return false;
  } // End bolean FindNameInDatabase()

  public static boolean FindAgeInDatabase(String _userFirstName, int _userAge, ArrayList<UserAccount> _UserCollection)
  {
    for (int i = 0; i < _UserCollection.size(); ++i)
    {
      // Account is only recoverable for up to three years. Unrecoverable after three. Also: Nobody ages backwards.
      if (_UserCollection.get(i).getAge() <= _userAge && _UserCollection.get(i).getAge() >= (_userAge - 3)
        && _UserCollection.get(i).getName().equalsIgnoreCase(_userFirstName))
        return true;
    }
    return false;
  } // End boolean FindAgeInDatabase()

  public static int FindHeightInDatabase(String _userFirstName, int _userHeightInchesRecovery, ArrayList<UserAccount> _UserCollection)
  {
    for (int i = 0; i < _UserCollection.size(); ++i)
    {
      if (_UserCollection.get(i).getName().equalsIgnoreCase(_userFirstName) && (_UserCollection.get(i).getHeightInches() == _userHeightInchesRecovery))
        return i;
    }
    return -1;
  } // end int FindHeightInDatabase()

  public static void clearScreen()
  {
    for (int i = 0; i < 75; ++i)
      System.out.println();
  } // End void clearScreen()

  public static boolean continueProgram(Scanner _userInput)
  {
    String userChoice = "";
    System.out.print("\n\t-------------------------------------------\n\tWould you like to go back to the main menu?\n\tType Y to go back or N to quit. Y/n: ");
    userChoice = _userInput.next();
    System.out.print("\t-------------------------------------------");
    if (userChoice.charAt(0) == 'Y' || userChoice.charAt(0) == 'y')
    {
      System.out.print("\n\n-------------------------------------------\n\tBack to the Main Menu");
      timedClearScreen();
      clearScreen();
      return true;
    }
    System.out.print("\n\nThank you for using The Fitness Assistant!\nWritten by Eric S McDaniel, July-August 2018.\n\n");
    return false;
  } // End boolean continueProgram()

  public static void timedClearScreen()
  {
    try
    {
      for (int i = 0; i < 5; ++i)
      {
        System.out.print(".");
        Thread.sleep(500);
      }
    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
  } // End void timedClearScreen()
} // End class FitnessAssistant
