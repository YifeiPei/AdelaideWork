/*
 *@author Yifei Pei
 */

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include <cassert>
#include "prac7.h"

#define MAX 100000

using namespace std;

matrix :: matrix () 
{
  data = new float [MAX];
  filename = '\0';
  count = 0;
  column = 0;
  row = 0;
}

matrix :: ~matrix ()
{
  if (data)
    {
      delete [] data;
      data = 0;
    }
}

matrix :: matrix (const matrix& f)
{
  data = new float [MAX];
  count = f.count;
  row = f.row;
  column = f.column;
  filename = f.filename;
  for (unsigned i=0;i<count;i++)
    data [i] = f.data[i];
}

matrix&  matrix :: operator = (const matrix& rhs)
{
  if (this == &rhs)
    return *this;
  delete [] data;
  count = rhs.count;
  row = rhs.row;
  column = rhs.column;
  filename = rhs.filename;
  if (rhs.data)
    {
      data = new float [MAX];
      for (unsigned i=0;i<count;i++)
        data[i] = rhs.data[i];
    }
  else
    data = 0;
  return *this;
}

string matrix :: setFile (string dataname)
{
  filename = dataname;
  return filename;
}

unsigned matrix :: getCount ()
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
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
  else
    {
      cout << "The file cannot be found.\n";
      exit (1);
    }   
  char * tmp = new char [MAX];
  strcpy (tmp,buffer);
  char * aTmp;
  while ((aTmp = strsep (&tmp, " ")))
    {
      if (strlen (aTmp)>0)
	count++;
    }
  // cout << "In total, there are " << count << " numbers" << endl;
  delete [] tmp;
  delete [] buffer;
  inputdata.close ();
  return count;
}

unsigned matrix :: getRow ()
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
  string line;
  if (inputdata.is_open())
    {
      while (inputdata.good())
	{
	getline (inputdata,line);
	row++;
	}
    }
  else
    {
      cout << "The file cannot be found.\n";
      exit (1);
    }   
  inputdata.close();
  row = row -1;
  return row;
}

unsigned matrix :: getColumn ()
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
  string line;
  if (inputdata.is_open())
    getline (inputdata,line);
  char * tmp = new char [MAX];
  strcpy (tmp, " ");
  strcpy (tmp,line.c_str());
  char * aTmp;
  while ((aTmp = strsep (&tmp, " ")))
    {
      if (strlen (aTmp)>0)
	column++;
    }
  delete [] tmp;
  inputdata.close();
  return column;
}

void matrix :: getData ()
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
  string line;
  char * buffer = new char [MAX];
  strcpy(buffer, " ");
  if (inputdata.is_open())
    {
      while (inputdata.good())
	{
	  getline (inputdata,line);
	  strcat (buffer,line.c_str());
	}
    }
  else
    {
      cout << "The file cannot be found.\n";
      exit (1);
    }
  char * aPtr;
  int i =0;
  while ((aPtr = strsep (&buffer, " ")))
    {
      if (strlen (aPtr)>0)
	data [i++] = atof (aPtr);
    }
  delete [] buffer;
  inputdata.close();
}



void matrix :: output ()
{
  for (unsigned i=0;i<count;i++)
    cout << data[i] << " ";
  cout << endl;
}



void matrix :: printRow ()
{
  cout << row << endl;
}

void matrix :: printColumn ()
{
  cout << column << endl;
}

void matrix :: printCount ()
{
  cout << count << endl;
}

matrix matrix :: operator + (const matrix& r)
{
  matrix temp(*this);
  if ((temp.row == r.row) && (temp.column == r.column))
    {
      for (unsigned i=0; i<count;i++)
        temp.data[i] = temp.data [i] + r.data[i];
    }
  else
    {
      cout << "The two objects cannot be operated by '+'.\n";
      exit(1);
    }
  return (temp);
}

matrix matrix :: operator * (const matrix& rs)
{
  assert (column == rs.row);
  matrix temp;
  temp.row = row;
  temp.column = rs.column;
  temp.count = temp.row * temp.column;
  for (unsigned r=0;r<temp.row;r++)
    for (unsigned c=0;c<temp.column;c++)
      {
	temp.data [r*(temp.column)+c] = 0;
	for (unsigned i=0;i<rs.row;i++)
	  temp.data [r*(temp.column)+c] += data [r*column+i] * rs.data [i*(rs.column)+c];
      }
  return temp;
}
