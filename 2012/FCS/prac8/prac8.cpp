/*
 *@author Yifei Pei
 *prac8 phonebook
 *the implication of the class
 */

#include <iostream>
#include <string>
#include <fstream>
#include <cstdlib>
#include <cstring>

#include "prac8.h"

#define MAX 100000

using namespace std;

PhoneBook :: PhoneBook ()
{
  num_entries_ = 0;
  name_ = new string [MAX];
  phone_number_ = new string [MAX];
}

PhoneBook :: PhoneBook (unsigned size)
{
  num_entries_ = size;
  name_ = new string [num_entries_];
  phone_number_ = new string [num_entries_];
}

PhoneBook :: PhoneBook (string name, string number)
{
  num_entries_ = 1;
  name_ = new string [MAX];
  phone_number_ = new string [MAX];
  name_ [0] = name;
  phone_number_ [0] = number;
}

PhoneBook :: ~PhoneBook ()
{
  if (name_)
    {
      delete [] name_;
      name_ = 0;
    }
  if (phone_number_)
    {
      delete [] phone_number_;
      phone_number_ = 0;
    }
}

PhoneBook :: PhoneBook (const PhoneBook& f)
{
  num_entries_ = f.num_entries_;
  for (unsigned int i=0;i<num_entries_;i++)
    name_[i] = f.name_[i];
  for (unsigned int n=0;n<num_entries_;n++)
    phone_number_[n] = f.phone_number_[n];
}

void PhoneBook :: getNum (string filename)
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
  string line;  
  if (inputdata.is_open ())
    {
      while (inputdata.good ())
	{
	  getline (inputdata,line);
	  num_entries_++;
        }
    }
  else
    {
      cout << "The file cannot be found.\n";
      exit (1);
    }
  inputdata.close ();
  num_entries_--;
  // cout << num_entries_ << endl;
}

void PhoneBook :: getData (string filename)
{
  ifstream inputdata;
  inputdata.open ((char*)filename.c_str());
  string tmpname;
  string tmpnumber;
  if (inputdata.is_open ())
    {
      for (unsigned int i=0;i<num_entries_;i++)
	{
	  inputdata >> tmpname >> tmpnumber;
	  name_[i] = tmpname;
	  // cout << name_[i] << endl;
	  phone_number_[i] = tmpnumber;
	  // cout << phone_number_[i] << endl;
	} 
    }
  else
    {
      cout << "The file cannot be found.\n";
      exit (1);
    }
  inputdata.close();
}

void PhoneBook :: find_number (string name)
{
  bool check = false;
  for (unsigned int i=0;i<num_entries_;i++)
    {
      if (name_[i] == name)
	{
	  cout << "The phone number of " << name << " is \n";
	  cout << phone_number_[i] << endl;
	  break;
	}
      else
	check = true;
    }
  if (check == true)
    cout << "Not Found.\n";
}
