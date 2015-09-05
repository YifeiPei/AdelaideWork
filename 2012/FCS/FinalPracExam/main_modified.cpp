/*
 *@author Yifei Pei a1611648; The University of Adelaide
 * FCS Final Prac Exam; main.cpp
 */

/*
 * modified
 * changed the 'int' to 'float'
 * make 'const' signatures to make the friend fuction compilable
 * changed the friend fuction from 'main.cpp' to 'homework.cpp'
 */

#include <iostream>

#include "homework.h"

using namespace std;

int main (int argc, const char *argv[])
{
  Homework TomHW ( "Tom Baggins");
  TomHW.add (15);
  TomHW.add (14);
  TomHW.add (3);

  cout << "The average after dropping the lowest score for "
       << TomHW.get_name() << " is "
       << TomHW.get_average() << endl;

  /* Note Tom's average is 14.5, since the 3 is not counted. */

  Homework GandalfsHW ( "Gandalf Gray");
  GandalfsHW.add (18);
  GandalfsHW.add (20);

  if (TomHW < GandalfsHW)
    cout << "Tom has a lower homework average." << endl;

  Homework JerryHW (TomHW);
  cout << "The name of JerryHW is now changed to: "
       << JerryHW.get_name() << endl;
  cout << "Jerry's average is " << JerryHW.get_average() << endl;

  return 0;
}


