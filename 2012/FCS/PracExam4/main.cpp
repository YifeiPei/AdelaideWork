/*
 *@author Yifei Pei
 */

#include <iostream>
#include <cstring>
#include "exam4.h"

using namespace std;

int main ()
{
  TString str1;
  TString str2 ("hello");
  str1 = str2;
  TString str3 ("world");
  TString str4;
  str4 = str1.concat (str3);
  
  TString str6;
  str6 = str2.reverse ();
  str1.output();
  str2.output();
  str3.output();
  str4.output();
  
  str6.output();
  return 0;
}
