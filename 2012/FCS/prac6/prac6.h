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

using namespace std;

class matrix
{
 public:
  matrix ();
  matrix (const matrix& f);
  ~matrix ();
  matrix& operator = (const matrix& rhs);
  void getData (string dataname);
  void output ();
 private:
  float *data;
  int count;
};

#endif
