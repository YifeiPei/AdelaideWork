
/**
 * @filename ATM.cpp
 *
 * author Yifei Pei
 **/

# include <iostream>
using namespace std;

/**
 * This function set a boolean 
 * to see if the amount can be given by 20s notes and 50s notes
 * @param the amount the user wants to withdraw
 *
 * @return true or false for the result
 **/

bool check (int money)
{
  while (money < 50)
    {
      if (money%20 == 0)
      {
	  return true;
      }
      else
	{
	  return false;
	}
  }
  
  if (((money%50)%20 == 0) || ((money%20)%50 == 0) || ((money-50)%20 ==0))
	{
	  return true;
	}
      else
	{
	  return false;
	}
}

/**
 * This function get the numbers of 20s notes
 * @param the amount the user wants to withdraw
 *
 * @return return the amount of 20s notes
 **/

int amount20 (int money)
{
  while (money < 50)
    {
      if (money%20 == 0)
	{
	  return (money/20);
	}
      else
	{
	  return 0;
	}
    }
       if ((money%50)%20 == 0)
	{
	  return ((money%50)/20);
	}
      else if ((money%20)%50 == 0)
	{
	  return (money/20);
	}
      else if ((money-50)%20 == 0)
	{
	  return ((money-50)/20);
	    }
      else
	{
	  return 0;
  }
}

/**
 * This function get the amount of 50s notes
 * @param the amount the user wants to withdraw
 *
 * @return return the amount of 50s notes
 **/

int amount50 (int money)
{
  while (money >= 50)
    {
      if ((money%50)%20 == 0)
	{
	  return (money/50);
	}
      else if ((money%20)%50 == 0)
	{
	  return ((money%20)/50);
	}
      else if ((money-50)%20 == 0)
	{
	  return 1;
	}
      else
	{
	  return 0;
	}
    }
  return 0;
}

int main ()
{
  int money1;

  cout << "Please enter the money you want to withdraw \n";
  cin >> money1;
  cout << "The money you want to withdraw is " << money1 << " \n";
  bool result1 = check (money1);
  int result2 = amount20 (money1);
  int result3 = amount50 (money1);
  if (result1 == true)
    {
      cout << "Congratulations! You can have your money immediately! \n";
      cout << "You can have " << result2 << " $20 notes and " << result3 << " $50 notes. \n";
    }
  else
    {
      cout << "Sorry. We only have $20 and $50 notes. You cannot withdraw the money you want. \n";
    }
  return 0;
}
