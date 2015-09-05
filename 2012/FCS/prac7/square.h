/*
 *@author Yifei Pei
 */

#ifndef SQUARE_H
#define SQUARE_H

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include <cassert>

#include "prac7.h"

#define MAX 100000

using namespace std;

class square : public matrix
{
 public:

  square ():matrix ()
    {
     
    }

  bool check ()
    {
      if ( row == column)
	return true;
      else
	{
	  cout << "This is not a square matrix. The program is going to abort.\n";
	  exit (1);
	  return false;
	}
    }

  float getTrace ()
    {
      float trace;
      if (check () == true)
	{	  
	  for (unsigned i=0;i<row; i++)
	    trace += data [i*column+i];
	  return trace;
	}
      else
	{
	  trace = 0;
	  return trace;
	}
    }

  void printTrace ()
    {
      cout << "The trace of the square matrix is \n";
      cout << getTrace ();
      cout << endl;
    }

 private:
  
};

#endif
