/*
 *@author Yifei Pei
 *prac 8 phonebook
 *the main function of the prac
 */

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include <cstring>

#include "prac8.h"

using namespace std;

int main ()
{
  PhoneBook a;
  a.getNum ("prac8file.txt");
  a.getData ("prac8file.txt");
  a.find_number ("Yifei");
  a.find_number ("Xin");
  return 0;
}
