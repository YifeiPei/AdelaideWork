/*
 *@author Yifei Pei
 */

#include <iostream>
#include <string>
#include <cstring>
#include <fstream>
#include <cstdlib>
#include "prac6.h"

using namespace std;

int main ()
{
  cout << "This program is to show the 4 special funstions in class.\n";
  cout << "First, the constructor.\n";
  matrix first;
  first.output ();
  cout << "Second, the copy constructor.\n";
  matrix second_copy;
  second_copy.getData ("prac6file.txt");
  cout << "The object needs to be copy is \n";
  second_copy.output ();
  matrix second (second_copy);
  cout << "The new object is \n";
  second.output();
  cout << "Third, the destructor.\n";
  cout << "Please look into the source code.\n";
  cout << "I'm sure I don't know how to show destructor on screen.\n";
  cout << "Forth, the operator = .\n";
  matrix forth, forth_copy;
  forth_copy.getData ("prac6file.txt");
  cout << "The object needs to be copy is \n";
  forth_copy.output ();
  forth = forth_copy;
  cout << "The new object is \n";
  forth.output ();
  cout << "Thank you. All the fuctions have been shown. Bye.\n";
  return 0;
}
