/*
 *@author Yifei Pei
 *
 *the members of the class student record
 */

#include <iostream>
#include <string>
#include <cstdlib>
#include "prac5_StudentRecord.h"

using namespace std;

StudentRecord :: StudentRecord (){}

StudentRecord :: ~StudentRecord (){}

void StudentRecord :: setName (string name)
{
  student_name = name;
}

void StudentRecord :: setAge (int age)
{
  student_age = age;
  check_data ();
}

void StudentRecord :: setScore (int score)
{
  student_score = score;
  check_data ();
}

void StudentRecord :: check_data ()
{
  if ((student_age<18)||(student_age>60)||(student_score<0)||(student_score>100))
    {
      cout << "Illegal data. Aborting program.\n";
      exit (1);
    }
}

string StudentRecord :: getName ()
{
  return student_name;
}

int StudentRecord :: getAge ()
{
  return student_age;
}

int StudentRecord :: getScore ()
{
  return student_score;
}
