/*
 *@author Yifei Pei
 */

#ifndef POINT_H
#define POINT_H

#include <iostream>

using namespace std;

class Point
{
public:
  Point ();
  Point (int , int );
  ~Point ();
  Point (const Point& );
  Point& operator = (const Point& );
  int getX () const;
  int getY () const;
  void setX (const int );
  void setY (const int );
  Point operator + (const Point& );
  Point operator - (const Point& );
  void output ();

private:
  int x;
  int y;
};

#endif
