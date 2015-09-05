#include <iostream>
#include <vector>
#include <cstdlib>
using namespace std;

bool isaMUstring (const vector <char> sequence, int size)
{
  for (int i=0; i<size; i++)
    {
      if ((sequence [i] == 'M')||(sequence [i] == 'I')||(sequence [i]== 'U'))
        return true;
    }
}

bool canApplyRule1 (const vector <char> sequence, int size)
{
  if (sequence [size-1]== 'I')
    return true;
  else
    return false;
}

void applyRule1 (vector <char> sequence, int size)
{
  char * newsequence;
  newsequence = new char [size+1];
  for (int i=0; i<size; i++)
    newsequence[i] = sequence [i];
  newsequence [size+1] = 'U';
  cout <<"The updated sequence is \n";
  for (int n=0; n<(size+1); n++)
    cout << newsequence [n] << " ";
  cout << 'U';
  cout << endl;
  delete [] newsequence;
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
  if (isaMUstring (sequence, size)== false)
    {
      cout << "You typed an invalid MU character sequence.\n";
      cout << "The program is going to shut down.\n";
      exit (1);
    }
  if ((isaMUstring (sequence, size) == true) && (canApplyRule1 (sequence, size) == false))
    {
      cout << "The sequence cannot be applied by Rule 1.\n";
      cout << "The final sequence is \n";
      for (int n = 0; n<size; n++)
        cout << sequence [n] << " ";
      cout << endl;
      cout << "The program si going to shut down.\n";
      cout << "See you next time.\n";
      exit (1);
    }
  if ((isaMUstring (sequence, size) == true) && (canApplyRule1 (sequence, size) == true))
    {
      cout << "The sequence can be applied by Rule 1.\n";
      cout << "The sequence is going to be updated.\n";
      applyRule1 (sequence, size);
    }
  return 0;
}
