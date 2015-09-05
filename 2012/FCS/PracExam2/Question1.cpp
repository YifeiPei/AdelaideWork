/*
 *Question1.cpp
 *
 *@author Yifei Pei
 */

# include <iostream>
using namespace std;

/* I prefer to ask the user to define the length of the array first
 * then when we operate the function we can run the exact number
 * In the main fuction it should ask the length of the array first
 * and then ask the user to type in the array
 */

/*
 * This fuction returns the duplicated times in the array numbers
 * @ param the parameters are the array and the array length
 */


int duplicate (unsigned array [], int length)
{
  int m;
  unsigned int * p;
  int n;
  for (n=0; n< length; n++)
    {
      int m=0;
      p = array;
      if ( *p == *p++)
	m=m+1;
    }
  return m;
}

