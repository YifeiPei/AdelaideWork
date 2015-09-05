/*
 *@author Yifei Pei
 */

#include <iostream>

#include "Point.hpp"

using namespace std;

Point :: Point ()
{
  x = 0;
  y = 0;
}

Point :: Point (int m, int n)
{
  x = m;
  y = n;
}

Point :: ~Point ()
{

}

Point :: Point (const Point& f)
{
  if (f.x)
    x = f.x;
  else
    x = 0;
  if (f.y)
    y = f.y;
  else
    y = 0;
}

Point& Point :: operator = (const Point& rhs)
{
  if (this == &rhs)
    return *this;
  if (rhs.x)
    x = rhs.x;
  else
    x = 0;
  if (rhs.y)
    y = rhs.y;
  else
    y = 0;
  return *this;
}

int Point :: getX () const
{
  return x;
}

int Point :: getY () const
{
  return y;
}

void Point :: setX (const int new_x)
{
  x = new_x;
}

void Point :: setY (const int new_y)
{
  y = new_y;
}

Point Point :: operator + (const Point& rs)
{
  Point temp (*this);
  temp.x = temp.x + rs.x;
  temp.y = temp.y + rs.y;
  return (temp);
}

Point Point :: operator - (const Point& rs)
{
  Point temp (*this);
  temp.x = temp.x - rs.x;
  temp.y = temp.y - rs.y;
  return (temp);
}

void Point :: output ()
{
  cout << "(" << getX() << "," << getY() << ")";
}
