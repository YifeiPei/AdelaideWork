/*
 * prac4 about the pointer both part 1 and 2
 *
 * @author Yifei Pei
 */

# include <iostream>
# include <vector>
using namespace std;

/*
 * part 1 the function to reverse the array
 *@param the array is going to be reversed
 *
 *@return return the reversed array
 */

int* reverse (const vector<int> copyMe)
{
  int * newarray;
  int size = copyMe.size();
  newarray = new int[size];
  for (int i=0; i<size; i++)
   newarray [i] = copyMe [size-1-i];
  return newarray;
}

/*
 * the part 2 of prac4
 * the main to print the reversed array
 */

int main ()
{
  cout << "Please enter the size of the numbers so that ";
  cout << "the program can generate it for you.\n";
  int size;
  cin >> size;
  vector<int> copyMe (size);
  for (int i=0; i<size; i++)
    copyMe [i] = i;
  int * p= reverse (copyMe);
  for (int x=0; x<size; x++)
    cout << p[x] << " ";
  cout << endl;
  delete [] p;
  return 0;
}

