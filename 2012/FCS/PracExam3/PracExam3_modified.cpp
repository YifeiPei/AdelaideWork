#include <iostream>
#include <vector>
#include <cstdlib>
using namespace std;

bool isaMUstring (const char *sequence, int size)
{
  for (int i=0; i<size; i++)
    {
      if ((sequence [i] == 'A')||(sequence [i] == 'B')||(sequence [i]== 'C')||(sequence [i]== 'D')||(sequence [i]== 'E')||(sequence [i]== 'F')||(sequence [i]== 'G')||(sequence [i]== 'H')||(sequence [i]== 'J')||(sequence [i]== 'K')||(sequence [i]== 'L')||(sequence [i]== 'N')||(sequence [i]== 'O')||(sequence [i]== 'P')||(sequence [i]== 'Q')||(sequence [i]== 'R')||(sequence [i]== 'S')||(sequence [i]== 'T')||(sequence [i]== 'V')||(sequence [i]== 'W')||(sequence [i]== 'X')||(sequence [i]== 'Y')||(sequence [i]== 'Z'))
	return false;	
    }
  return true;
}

bool canApplyRule1 (const char *sequence, int size)
{
  if (sequence [size-1]== 'I')
    return true;
  else
    return false;
}

void applyRule1 (char *sequence, int size)
{
  char *updatedsequence;
  updatedsequence = new char[(size+1)];
  for (int n=0; n<size; n++)
    updatedsequence[n] = sequence [n];
  updatedsequence [size] = 'U';
  cout << "The updated sequence is \n";
  for (int i = 0; i<(size+1); i++)
    cout << updatedsequence [i] << " ";
  cout << endl;
  delete [] updatedsequence;
}

int main ()
{
  int size;
  cout << "Please type in the size of the sequence you want.\n";
  cin >> size;
  vector <char> sequence (size);
  cout << "Please enter the MU character sequence.\n";
  cout << "Only the character 'M', 'I', and 'U' can be";
  cout << "inside the MU character sequence.\n";
  cout << "Remember that the length of the sequence cannot";
  cout << "exceed the size you set.\n";
  for (int i = 0; i<size; i++)
    cin >> sequence [i];
  char *newsequence;
  newsequence = new char [size];
  for (int n = 0; n<size; n++)
    newsequence [n] = sequence [n];
  if (isaMUstring (newsequence, size)== false)
    {
      cout << "You typed an invalid MU character sequence.\n";
      cout << "The program is going to shut down.\n";
      exit (1);
    }
  if ((isaMUstring (newsequence, size) == true) && (canApplyRule1 (newsequence, size) == false))
    {
      cout << "The sequence cannot be applied by Rule 1.\n";
      cout << "The final sequence is \n";
      for (int m = 0; m<size; m++)
        cout << newsequence [m] << " ";
      cout << endl;
      cout << "The program is going to shut down.\n";
      cout << "See you next time.\n";
      exit (1);
    }
  if ((isaMUstring (newsequence, size) == true) && (canApplyRule1 (newsequence, size) == true))
    {
      cout << "The sequence can be applied by Rule 1.\n";
      cout << "The sequence is going to be updated.\n";
      applyRule1 (newsequence, size);
    }
  delete [] newsequence;
  return 0;
}
