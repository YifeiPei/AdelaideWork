/*
 *@author Yifei Pei
 *prac8 phonebook
 *the declaration of the class
 */

#ifndef PRAC8_H
#define PRAC8_H

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include <cstring>

using namespace std;

class PhoneBook
{
 public:
  PhoneBook ();
  PhoneBook (unsigned );
  PhoneBook (string , string );
  ~PhoneBook ();
  PhoneBook (const PhoneBook& f);
  void getNum (string );
  void getData (string );
  void find_number (string );

 private:
  unsigned int num_entries_;
  string * name_;
  string * phone_number_;
};

#endif
