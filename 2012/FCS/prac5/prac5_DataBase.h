/*
 *@author Yifei Pei
 *
 *the class of student database
 */

#ifndef DATABASE_H
#define DATABASE_H

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include "prac5_StudentRecord.h"

using namespace std;

class DataBase
{
 public:
  DataBase ();
  DataBase (unsigned int size);
  ~DataBase ();
  void populate (string infoname);
  void output ();
 private:
  unsigned int data_size;
  StudentRecord * student_record;
  string strtmp;
  int inttmp1;
  int inttmp2;
};

#endif
