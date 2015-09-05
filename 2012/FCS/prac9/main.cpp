/*
 *@author Yifei Pei
 */

#include <iostream>

#include "Point.hpp"
#include "PointArray.hpp"

using namespace std;

int main ()
{
  Point *a;
  a = new Point [5];
  for (int i=0;i<5;i++)
    {
      a[i].setX(i+1);
      a[i].setY(i+1);
    }
  PointArray b (a, 5);
  cout << "the initial b.\n";
  b.output ();
  PointArray c (b);
  cout << "the initial c.\n";
  c.output ();
  Point d;
  d.setX (6);
  d.setY (6);
  b.push_back (d);
  cout << "the new b.\n";
  b.output ();
  c.insert (3, d);
  cout << "the new c.\n";
  c.output ();
  cout << "the size of c.\n";
  cout << c.getSize () << endl;
  c.remove (3);
  cout << "the new c again.\n";
  c.output ();
  cout << "this size of c again.\n";
  cout << c.getSize () << endl;
  cout << "then clear c.\n";
  c.clear ();
  cout << "clear finished.\n";
  cout << "Is c empty?\n";
  c.output ();  
  // Point e;
  // e = b.get (3);
  cout << "The third one of b is\n";
  // e.output ();
  b.get(3)->output();
  cout << endl;
}
