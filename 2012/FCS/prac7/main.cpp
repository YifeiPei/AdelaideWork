/*
 *@author Yifei Pei
 */

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include "prac7.h"
#include "square.h"

using namespace std;

int main ()
{
  matrix a;
  a.setFile ("prac7file.txt");
  a.getCount();
  a.getRow ();
  a.getColumn ();
  a.getData ();
  cout << "The details of matrix a are as follows: \n";
  cout << "The whole data.\n";
  a.output ();
  cout << "The number of row.\n";
  a.printRow ();
  cout << "The number of columns.\n";
  a.printColumn ();
  cout << "The number of individual elements.\n";
  a.printCount ();
  matrix b;
  b.setFile ("prac7file1.txt");
  b.getCount ();
  b.getRow ();
  b.getColumn();
  b.getData();
  matrix c;
  c.setFile ("prac7file2.txt");
  c.getCount();
  c.getRow();
  c.getColumn();
  c.getData ();
  matrix d;
  d = a + b;
  cout << "The details of the martix b that is going to be added with a.\n";
  cout << "The whole data, rows, columns, and the amount of elements.\n";
  b.output ();
  b.printRow ();
  b.printColumn ();
  b.printCount ();
  cout << "And then the result of 'a+b' is \n";
  d.output ();
  matrix e;
  e = a * c;
  cout << "The details of the martix c that is going to be multiplied with a.\n";
  cout << "The whole data, rows, columns, and the amount of elements.\n";
  c.output ();
  c.printRow ();
  c.printColumn ();
  c.printCount ();
  cout << "And then the result of 'a*c' is \n";
  e.output ();
  square f;
  f.setFile ("prac7file3.txt");
  f.getCount ();
  f.getRow ();
  f.getColumn ();
  f.getData ();
  f.check ();
  f.getTrace ();
  f.printTrace ();
  cout << "Thank you. All the fuctions have been shown. Bye.\n";
  return 0;
}
