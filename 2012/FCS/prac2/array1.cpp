
/**
 * @filename array1.cpp
 *
 * author Yifei Pei
 **/

# include <iostream>
using namespace std;

/**
 * This function get the arrays reversed
 * @param the array that is going to be reversed
 **/

void reverse (int array [10])
{
  for (int n=9; n>=0; n--)
    cout << array [n] << " ";
  cout << "\n";
}

int main ()
{
  int array [10];

  cout << "Please enter TEN numbers to be reversed. ";
  cout << "Type one number, press enter button, ";
  cout << "and then type in another number. ";
  cout << "The system will remember what you typed. ";
  cout << "Please remember that for this program ";
  cout << "you can only type in integers. ";
  cout << " Non-integer numbers will cause the system collapse.\n";
  for (int n=0; n<10; n++)
    cin >> array [n];
  cout << "The numbers you entered are: \n";
  for (int a=0; a<10; a++)
    cout << array [a] << " ";
  cout << "\n";
  cout << "The revised numbers are \n";
  reverse ( array );
  cout << "Thank you. \n";
  return 0;
}
