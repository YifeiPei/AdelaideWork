
/**
 * @filename array2.cpp
 *
 *author Yifei Pei
 **/

# include <iostream>
using namespace std;

/**
 * This function gets the array reversed
 * @param the array that is going to be reversed and the length of the array
 *
 **/

void reverse (int array [], int n)
{
  for (int a=n; a>=0; a--)
    cout << array [a] << " ";
  cout << "\n";
}

int main ()
{
  int n;
  int array [20];
  cout << "How many numbers do you want to enter? ";
  cout << "The length must be not greater than 20. \n";
  cin >> n;
  if ((n<1) || (n>20))
    {
      cout << "Sorry, you typed in the wrong data. ";
      cout << "The program is going to shut down.\n";
    }
  else
    {
  cout << "The length will be " << n << " numbers long.\n";
  cout << "Please enter the numbers you want to be reversed."; 
  cout << "Type one number, press enter button, ";
  cout << "and then type in another number. ";
  cout << "Please remember that you can only type in integers. \n";
  for (int a=0; a<n; a++)
    cin >> array [a];
  cout << "The numbers you entered are: \n";
  for (int a=0; a<n; a++)
    cout << array[a] << " ";
  cout << "\n";
  cout << "The revised numbers are \n";
  reverse ((array), (n-1));
  cout << "Thank you. \n";
    }
  return 0;
}
