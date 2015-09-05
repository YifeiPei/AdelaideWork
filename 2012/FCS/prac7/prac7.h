/*
 *@author Yifei Pei
 */

#ifndef PRAC6_H
#define PRAC6_H

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include <cassert>

using namespace std;

class matrix
{
 public:
  matrix ();
  matrix (const matrix& f);
  ~matrix ();
  matrix& operator = (const matrix& rhs);
  string setFile (string dataname);
  unsigned getCount ();
  unsigned getRow ();
  unsigned getColumn ();
  void getData ();
  void output ();
  void printColumn ();
  void printCount ();
  void printRow ();
  matrix operator + (const matrix& r);
  matrix operator * (const matrix& rs);
 

 protected:
  float *data;
  string filename;
  unsigned int count;
  unsigned int row;
  unsigned int column;
};

#endif
