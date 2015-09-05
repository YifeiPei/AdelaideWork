/*
 *prac5 objects both part 1 and part 2
 *
 *@author Yifei Pei
 */

#include <iostream>
#include <string>
#include "prac5_StudentRecord.h"
#include "prac5_DataBase.h"

using namespace std;

/*
 *main function of prac5
 *populate the database and print out them
 */

int main ()
{
  int size = 15;
  DataBase student (size);
  cout << "Now populating the data.\n";
  student.populate ("prac5_info.txt");
  cout << "Now printing out the data.\n";
  student.output ();
  return 0;
}
