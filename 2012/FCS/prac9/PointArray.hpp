/*
 *@author Yifei Pei
 */

#ifndef POINTARRAY_H
#define POINTARRAY_H

#include <iostream>

#include "Point.hpp"

using namespace std;

class PointArray
{
public:
  PointArray ();
  PointArray (const Point* , const int );
  PointArray (const PointArray& );
  ~PointArray ();
  PointArray& operator = (const PointArray& );
  void push_back (const Point& );
  void insert (const int , const Point& );
  void remove (const int );
  const int getSize () const;
  void clear ();
  Point* get (const int );
  const Point* get (const int ) const;
  void output ();

private:
  int size;
  Point *points;
  void resize (int );
};

#endif
