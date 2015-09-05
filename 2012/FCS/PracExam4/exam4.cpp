/*
 *@author Yifei Pei
 */

#include <iostream>
#include <cstring>
#include "exam4.h"

using namespace std;

TString :: TString () {}

TString :: TString (const char *str)
{
  max_length = strlen (str);
  data_ = new char [max_length+1];
  strcpy (data_ , str);
}

TString :: TString (const TString& another)
{
  max_length = strlen (another.data_);
  data_ = new char [max_length+1];
  strcpy (data_ , another.data_);
}

TString :: ~TString ()
{
  delete [] data_;
}

TString& TString :: operator = (const TString& rhs)
{
  if (this == &rhs)
    return *this;
  delete [] data_;
  max_length = strlen (rhs.data_);
  if (rhs.data_)
    {
      data_ = new char [max_length+1];
      for (int i=0;i<(max_length+1);i++)
	data_[i] = rhs.data_[i];
    }
  else
    data_ = NULL;
  return *this;
}

int TString :: length ()
{
  return strlen (data_);
}

char* TString :: reverse ()
{
  max_length = strlen (data_);
  char *newdata = new char [max_length+1];
  for (int i=0;i<(max_length+1);i++)
    newdata [i] = data_ [max_length - i];
  return newdata;
}

char* TString :: concat (const TString &str)
{
  max_length = strlen (data_);
  int size = ((max_length) + (strlen(str.data_)));
  char* newdata = new char [size+1];
  for (int i=0;i<(max_length);i++)
    newdata[i] = data_ [i];
  for (int n=0;n<(strlen(str.data_));n++)
    newdata[(max_length+n)]= str.data_[n];
  return newdata;
}



void TString :: output ()
{
  cout <<"The string is \n";
  for (int i=0;i<(max_length+1);i++)
    cout << data_[i];
  cout << endl;
}
