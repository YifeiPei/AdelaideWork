/*
 *@author Yifei Pei
 */

#include <iostream>
#include <cstdlib>

#include "ctime.h"

using namespace std;

CTime ::CTime ()
{
  mins_ = 0;
  secs_ = 0;
  id_ = "default-time";
}

CTime :: CTime (int initMins, int initSecs, char * initId)
{
  check_ (initMins);
  mins_ = initMins;
  check_ (initSecs);
  secs_ = initSecs;
  id_ = initId;
}

CTime :: ~CTime ()
{

}

CTime :: CTime (const CTime& f)
{
  mins_ = f.mins_;
  secs_ = f.secs_;
  id_ = f.id_;
}

int CTime :: get_mins () const
{
  return mins_;
}

int CTime :: get_secs () const
{
  return secs_;
}

int CTime :: get_totaltime () const
{
  return (mins_ * 60 + secs_);
}

char * CTime :: get_id () const
{
  return id_;
}

void CTime :: set_mins (int mins)
{
  mins_ = mins;
}

void CTime :: set_secs (int secs)
{
  secs_ = secs;
}

void CTime :: set_id (char * id)
{
  id_ = id;
}

bool CTime :: equal (CTime SecondTime) const
{
  if ((mins_ == SecondTime.mins_) && (secs_ == SecondTime.secs_))
    return true;
  else
    return false;
}

bool CTime :: greater_than (CTime SecondTime) const
{
  if (mins_ > SecondTime.mins_)
    return true;
  else if ((mins_ == SecondTime.mins_) && (secs_ > SecondTime.secs_))
    return true;
  else
    return false;
}

bool CTime :: less_than (CTime SecondTime) const
{
  if (mins_ < SecondTime.mins_)
    return true;
  else if ((mins_ == SecondTime.mins_) && (secs_ < SecondTime.secs_))
    return true;
  else
    return false;
}

CTime CTime :: operator + (const CTime& SecondTime)
{
  CTime temp (*this);
  int exceed = 0;
  temp.secs_ = temp.secs_ + SecondTime.secs_;
  if (temp.secs_ >59)
    {
      exceed = (temp.secs_)/60;
      temp.secs_ = (temp.secs_)%60;
      temp.mins_ = temp.mins_ + SecondTime.mins_ + exceed;
      check_ (temp.mins_);
    }
  else
    {
      temp.mins_ = temp.mins_ + SecondTime.mins_;
      check_ (temp.mins_);
    }
  return (temp);
}

CTime CTime :: operator - (const CTime& SecondTime)
{
  CTime temp (*this);
  if ((temp.greater_than(SecondTime)) == true)
    {
      if (((temp.mins_) > (SecondTime.mins_)) && ((temp.secs_) > (SecondTime.secs_)))
        {
          temp.mins_ = temp.mins_ - SecondTime.mins_;
          temp.secs_ = temp.secs_ - SecondTime.secs_;
        }
      else if (((temp.mins_) > (SecondTime.mins_)) && ((temp.secs_) < (SecondTime.secs_)))
        {
          temp.mins_ = temp.mins_ - 1 - SecondTime.mins_;
          temp.secs_ = temp.secs_ + 60 - SecondTime.secs_;
        }
    }
  else if (((temp.equal (SecondTime)) == true))
    {
      temp.mins_ = 0;
      temp.secs_ = 0;
    }
  else
    {
      cout << "The two time cannot be operated by subtract.\n";
      cout << "The program is going to abort.\n";
      exit (1);
    }
  return (temp);
}

void CTime :: check_ (int time)
{
  if ((time < 0) || (time > 59))
    {
      cout << "Illegal data, Aborting program.\n";
      exit (1);
    }
}

void CTime :: output ()
{
  cout << "The time is \n";
  cout << get_mins() << ":" << get_secs() << endl;
  cout << "With the ID of \n";
  cout << get_id() << endl;
  cout << "And the total time is \n";
  cout << get_totaltime () << " seconds.\n";
}
