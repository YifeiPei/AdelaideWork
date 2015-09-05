/*
 *@author Yifei Pei
 */

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include "prac6.h"

#define MAX 100000

using namespace std;

matrix :: matrix ()
{
  data = new float [MAX];
  count = 0;
}

matrix :: ~matrix ()
{
  delete [] data;
}

matrix :: matrix (const matrix& f)
{
  data = new float [MAX];
  count = f.count;
  for (int i=0;i<count;i++)
    data [i] = f.data[i];
}

matrix&  matrix :: operator = (const matrix& rhs)
{
  if (this == &rhs)
    return *this;
  delete [] data;
  count = rhs.count;
  if (rhs.data)
    {
      data = new float [MAX];
      for (int i=0;i<count;i++)
        data[i] = rhs.data[i];
    }
  else
    data = 0;
  return *this;
}

void matrix :: getData (string dataname)
{
 
  ifstream inputdata;
  inputdata.open ((char*)dataname.c_str());
  string line;
  char * buffer = new char [MAX];
  strcpy(buffer," ");
  if (inputdata.is_open())
    {
      while (inputdata.good())
	{
	getline (inputdata,line);
	strcat (buffer,line.c_str());
	}
    }    
  char * tmp = new char [MAX];
  strcpy (tmp,buffer);
  char * aTmp;
  while ((aTmp = strsep (&tmp, " ")))
    {
      if (strlen (aTmp)>0)
	count++;
    }
  cout << "In total, there are " << count << " numbers" << endl;
  delete [] tmp;
  char * aPtr;
  int i = 0;
  while ((aPtr = strsep (&buffer, " ")))
    {
      if (strlen (aPtr) > 0)
	data [i++] = atof(aPtr);
    }
  delete [] buffer;
  inputdata.close();
 }
void matrix :: output ()
{
  cout << "The output is stated as follows.\n";
  for (int i=0;i<count;i++)
    cout << data[i] << " ";
  cout << endl;
}
