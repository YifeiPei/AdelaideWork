/*
 *@author Yifei Pei
 */

#include <iostream>
#include <cstdlib>

#include "ctime.h"

using namespace std;

int main ()
{
  CTime time1;
  time1.set_mins (23);
  time1.set_secs (32);
  time1.set_id ("First Time");
  cout << "The first time \n";
  time1.output ();
  CTime time2 (25, 29, "Second Time");
  cout << "The second time \n";
  time2.output ();
  CTime time3;
  time3 = time1 + time2;
  time3.set_id ("Third Time");
  cout << "The third time \n";
  time3.output ();
  CTime time4;
  time4 = time2 - time1;
  time4.set_id ("Fourth Time");
  cout << "The fourth time \n";
  time4.output ();
  return 0;
}
