/*
 *@author Yifei Pei
 *
 *class of student record
 */

#ifndef STUDENTRECORD_H
#define STUDENTRECORD_H

#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;

class StudentRecord
{
 public:
  StudentRecord ();
  ~StudentRecord ();
  void setName (string name);
  void setAge (int age);
  void setScore (int score);
  string getName ();
  int getAge ();
  int getScore ();
 private:
  string student_name;
  int student_age;
  int student_score;
  void check_data ();
};

#endif
