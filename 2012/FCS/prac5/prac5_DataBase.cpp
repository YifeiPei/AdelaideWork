/*
 *@author Yifei Pei
 *
 *the members of class DataBase
 */

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include "prac5_DataBase.h"
#include "prac5_StudentRecord.h"

using namespace std;

DataBase :: DataBase (){}

DataBase :: DataBase (unsigned int size)
{
  data_size = size;
  student_record = new StudentRecord [size];
}

DataBase :: ~DataBase (){}

void DataBase :: populate (string infoname)
{
  ifstream inputinfo;
  inputinfo.open ( (char*)infoname.c_str ());
  if (inputinfo.fail())
    {
      cout << "Input file opening failed\n";
      exit (1);
    }
  for (int i=0;i<data_size;i++)
    {
      inputinfo >> strtmp >> inttmp1 >> inttmp2;
      /*
       *cout << strtmp << endl;
       *cout << inttmp1 << endl;
       *cout << inttmp2 << endl;
       */
      student_record[i].setName (strtmp);
      student_record[i].setAge (inttmp1);
      student_record[i].setScore (inttmp2);
    }
  inputinfo.close();
}

void DataBase :: output ()
{
  for (int i=0; i<data_size; i++)
    {
      cout << "The student name is \n";
      cout << student_record[i].getName () << endl;
      cout << "The student age is \n";
      cout << student_record[i].getAge () << endl;
      cout << "The student score is \n";
      cout << student_record[i].getScore () << endl;
    }
}
