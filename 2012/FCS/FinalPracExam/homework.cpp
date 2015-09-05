/*
 *@author Yifei Pei a1611648; The University of Adelaide
 * FCS Final Prac Exam; homework.cpp
 */

#include <iostream>
#include <vector>
#include <cstring>
#include <cstdlib>

#include "homework.h"

using namespace std;

Homework :: Homework ()
{
  name_ = new char [128];
}

Homework :: Homework (const char new_name[])
{
  name_ = new char [128];
  strcpy (name_, new_name);
}

Homework :: Homework (const Homework& pv)
{
  score_list_ = pv.score_list_;
  if (pv.name_)
    {
      name_ = new char [128];
      strcpy (name_, pv.name_);
    }
  else
    {
      name_ = 0;
    }
}

Homework :: ~Homework ()
{
  delete [] name_;
  score_list_.clear();
}

Homework& Homework :: operator = (const Homework& rs)
{
  if (this == &rs)
    return *this;
  delete [] name_;
  score_list_ = rs.score_list_;
  if (rs.name_)
    {
      name_ = new char [128];
      strcpy (name_, rs.name_);
    }
  else
    name_ = 0;
  return *this;
}

void Homework :: add (int score)
{
  if (score_list_.size() > 20)
    {
      cout << "The score list reached its capacity.";
      cout << "The program is going to abort." << endl;
      exit (1);
    }
  score_list_.push_back (score);
  cout << "score list stores " << score_list_.size() << " scores.\n";
}

char* Homework :: get_name () const
{
  if (name_)
    return name_;
  else
    return NULL;
}

int Homework :: get_average ()
{
  int sum;
  if (score_list_.size() > 1)
    {
      for (int i=0;i<(score_list_.size());i++)
	sum += score_list_ [i];
      sum = sum - get_lowest ();
      return sum/(score_list_.size()-1);
    }
  else
    return 0;
}

int Homework :: get_lowest ()
{
  int temp;
  temp = score_list_ [0];
  for (int i = 0;i<(score_list_.size());i++)
    {
      if (score_list_[i]<temp)
	temp = score_list_ [i];
    }
  return temp;
}
