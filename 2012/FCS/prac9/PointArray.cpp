/*
 *@author Yifei Pei
 */

#include <iostream>
#include <cassert>

#include "Point.hpp"
#include "PointArray.hpp"

using namespace std;

PointArray :: PointArray ()
{
  size = 0;
  points = 0;
}

PointArray :: PointArray (const Point new_points[], const int new_size)
{
  size = new_size;
  points = new Point [size];
  for (int i=0;i<size;i++)
    points[i] =  new_points[i];
}

PointArray :: PointArray (const PointArray& pv)
{
  size = pv.size;
  if (pv.points)
    {
      points = new Point [size];
      for (int i=0;i<size;i++)
	points[i] = pv.points[i];
    }
  else
    points = 0;
}

PointArray :: ~PointArray ()
{
  delete [] points;
  size = 0;
}

PointArray& PointArray :: operator = (const PointArray& f)
{
  if (this == &f)
    return *this;
  delete [] points;
  size = f.size;
  if (f.points)
    {
      points = new Point [size];
      for (int i=0;i<size;i++)
	points[i] = f.points[i];
    }
  else
    points = 0;
  return *this;
}

void PointArray :: push_back (const Point &p)
{
  resize (size+1);
  points [size-1] = p;
}

void PointArray :: insert (const int position, const Point &p)
{
  assert (-1<position<size);
  Point *temp = new Point [size];
  for (int n=0;n<size;n++)
    temp [n] = points [n];
  resize (size+1);
  points [position-1] = p;
  for (int i=position;i<size;i++)
    points [i] = temp [i-1];
  delete [] temp;
}

void PointArray :: remove (const int pos)
{
  assert (-1<pos<size);
  Point *temp = new Point [size];
  for (int n=0;n<size;n++)
    temp [n] = points [n];
  resize (size-1);
  for (int i=(pos-1);i<size;i++)
    points [i] = temp [i+1];
  delete [] temp;
}

const int PointArray :: getSize () const
{
  return size;
}

void PointArray :: clear ()
{
  points = 0;
  size = 0;
}

Point *PointArray :: get (const int position)
{
  if (-1<position<size)
    return &points[position-1];
  else
    return NULL;
}

const Point *PointArray :: get (const int position) const
{
  if (-1<position<size)
    return &points[position-1];
  else
    return NULL;
}

void PointArray :: resize (int n)
{
  Point *temp = new Point [n];
  if (n<=size)
    {
      for (int i=0;i<n;i++)
        temp [i] = points [i];
    }
  else
    {
      for (int m=0;m<size;m++)
	temp [m] = points [m];
    }
  delete [] points;
  points = new Point [n];
  for (int j=0;j<n;j++)
    points [j] = temp [j];
  delete [] temp;
  size = n;
}

void PointArray :: output ()
{
  for (int i=0;i<size;i++)
    {
      points[i].output ();
      cout << " ";
    }
  cout << endl;
}
