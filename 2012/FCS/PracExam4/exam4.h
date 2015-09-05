/*
 *@author Yifei Pei
 */

#ifndef EXAM4_H
#define EXAM4_H

#include <iostream>
#include <cstring>

using namespace std;

class TString
{
 public:
  TString ();
  TString (const char *str);
  TString (const TString& another);
  ~TString ();
  TString& operator = (const TString& rhs);
  int length ();
  char* reverse ();
  char* concat (const TString &str);
  
  void output ();
 private:
  char *data_;
  int max_length;
};

#endif
