/*
 *@author Yifei Pei a1611648; The University of Adelaide
 * FCS Final Prac Exam; homework.h
 */

/*
 * modified
 * changed the 'int' to 'float'
 * make 'const' signatures to make the friend fuction compilable
 * changed the friend fuction from 'main.cpp' to 'homework.cpp'
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
  void add (float );
  char * get_name () const;
  float get_average () const;
  friend bool operator < (const Homework& , const Homework& );
  float get_lowest () const;

 private:
  char * name_;
  vector <float> score_list_;

};

#endif
