/*
 *@author Yifei pei
 */

#ifndef CTIME_H
#define CTIME_H

#include <iostream>
#include <cstdlib>

using namespace std;

class CTime
{
 public:
  CTime ();
  CTime (int ,int ,char * );
  ~CTime ();
  CTime (const CTime& f);
  int get_mins () const;
  int get_secs () const;
  int get_totaltime () const;
  char * get_id () const;
  void set_mins (int );
  void set_secs (int );
  void set_id (char * );
  bool equal (CTime ) const;
  bool greater_than (CTime ) const;
  bool less_than (CTime ) const;
  CTime operator + (const CTime& );
  CTime operator - (const CTime& );
  void output ();
 
 private:
  int mins_;
  int secs_;
  char * id_;
  void check_ (int );
};

#endif
