/*
 *@author Yifei Pei a1611648; The University of Adelaide
 * FCS Final Prac Exam; homework.h
 */

#ifndef HOMEWORK_H
#define HOMEWORK_H

#include <iostream>
#include <vector>
#include <cstring>
#include <cstdlib>

using namespace std;

class Homework
{
 public:
  Homework ();
  Homework (const char* );
  Homework (const Homework& );
  ~Homework ();
  Homework& operator = (const Homework& );
  void add (int );
  char * get_name () const;
  int get_average ();
  friend bool operator < (const Homework& , const Homework& );
  int get_lowest ();

 private:
  char * name_;
  vector <int> score_list_;

};

#endif
